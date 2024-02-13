package io.github.dftrakesh;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dftrakesh.model.authenticationapi.AccessCredential;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static io.github.dftrakesh.constants.ConstantCodes.API_VERSION_V1_ENDPOINT;
import static io.github.dftrakesh.constants.ConstantCodes.AUTHORIZATION;
import static io.github.dftrakesh.constants.ConstantCodes.HTTP_REQUEST_CONTENT_TYPE_JSON;
import static io.github.dftrakesh.constants.ConstantCodes.HTTP_REQUEST_PROPERTY_CONTENT_TYPE;
import static io.github.dftrakesh.constants.ConstantCodes.BEARER;
import static io.github.dftrakesh.constants.ConstantCodes.MAX_ATTEMPTS;
import static io.github.dftrakesh.constants.ConstantCodes.TIME_OUT_DURATION;
import static io.github.dftrakesh.constants.ConstantCodes.TOO_MANY_REQUEST_EXCEPTION_CODE;

@AllArgsConstructor
public class MagentoSdk {

    protected HttpClient client;
    protected ObjectMapper objectMapper;
    protected AccessCredential accessCredential;

    @SneakyThrows
    public MagentoSdk(AccessCredential accessCredential) {
        client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.accessCredential = accessCredential;
    }

    @SneakyThrows
    protected URI baseUrl(String endpoint) {
        return URI.create(accessCredential.getStoreDomain() +
                API_VERSION_V1_ENDPOINT +
                endpoint);
    }

    @SneakyThrows
    protected HttpRequest get(URI uri) {
        return HttpRequest.newBuilder(uri)
                .GET()
                .header(AUTHORIZATION, BEARER + accessCredential.getAccessToken())
                .header(HTTP_REQUEST_PROPERTY_CONTENT_TYPE, HTTP_REQUEST_CONTENT_TYPE_JSON)
                .build();
    }

    @SneakyThrows
    protected HttpRequest post(URI uri, Object body) {
        return HttpRequest.newBuilder(uri)
                .POST(HttpRequest.BodyPublishers.ofString(getString(body)))
                .header(AUTHORIZATION, BEARER + accessCredential.getAccessToken())
                .header(HTTP_REQUEST_PROPERTY_CONTENT_TYPE, HTTP_REQUEST_CONTENT_TYPE_JSON)
                .build();
    }

    @SneakyThrows
    protected HttpRequest put(URI uri, Object body) {
        return HttpRequest.newBuilder(uri)
                .PUT(HttpRequest.BodyPublishers.ofString(getString(body)))
                .header(AUTHORIZATION, BEARER + accessCredential.getAccessToken())
                .header(HTTP_REQUEST_PROPERTY_CONTENT_TYPE, HTTP_REQUEST_CONTENT_TYPE_JSON)
                .build();
    }

    @SneakyThrows
    protected HttpRequest delete(URI uri) {
        return HttpRequest.newBuilder(uri)
                .DELETE()
                .header(AUTHORIZATION, BEARER + accessCredential.getAccessToken())
                .build();
    }

    @SneakyThrows
    protected <T> T getRequestWrapped(HttpRequest request, Class<T> tClass) {

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenComposeAsync(response -> tryResend(client, request, HttpResponse.BodyHandlers.ofString(), response, 1))
                .thenApplyAsync(HttpResponse::body)
                .thenApplyAsync(responseBody -> convertBody(responseBody, tClass))
                .get();
    }

    @SneakyThrows
    protected String getString(Object body) {
        return objectMapper.writeValueAsString(body);
    }

    @SneakyThrows
    private <T> T convertBody(String body, Class<T> tClass) {
        return objectMapper.readValue(body, tClass);
    }

    @SneakyThrows
    protected URI addParameters(URI uri, HashMap<String, String> params) {
        String query = uri.getQuery();
        StringBuilder builder = new StringBuilder();

        if (query != null)
            builder.append(query);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!builder.toString().isEmpty())
                builder.append("&");
            builder.append(key.concat("=").concat(value));
        }

        return new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), builder.toString(), uri.getFragment());
    }

    @SneakyThrows
    protected String encodeSku(String sku) {
        return URLEncoder.encode(sku, StandardCharsets.UTF_8.toString());
    }

    @SneakyThrows
    public <T> CompletableFuture<HttpResponse<T>> tryResend(HttpClient client,
                                                            HttpRequest request,
                                                            HttpResponse.BodyHandler<T> handler,
                                                            HttpResponse<T> response, int count) {
        if (response.statusCode() == TOO_MANY_REQUEST_EXCEPTION_CODE && count < MAX_ATTEMPTS) {
            Thread.sleep(TIME_OUT_DURATION);
            return client.sendAsync(request, handler)
                    .thenComposeAsync(resp -> tryResend(client, request, handler, resp, count + 1));
        }
        return CompletableFuture.completedFuture(response);
    }
}