package io.github.dftrakesh;

import io.github.dftrakesh.model.authenticationapi.AccessCredential;
import io.github.dftrakesh.model.orderapi.OrderResponse;
import io.github.dftrakesh.model.orderapi.OrderResponseWrapper;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.HashMap;

import static io.github.dftrakesh.constants.ConstantCodes.FORWARD_SLASH;
import static io.github.dftrakesh.constants.ConstantCodes.ORDERS_ENDPOINT;
import static io.github.dftrakesh.constants.ConstantCodes.SEARCH_CRITERIA_PAGE_SIZE;
import static io.github.dftrakesh.constants.ConstantCodes.SEARCH_CRITERIA_PAGE_SIZE_VALUE;

public class OrdersApi extends MagentoSdk {

    public OrdersApi(AccessCredential accessCredential) {
        super(accessCredential);
    }

    @SneakyThrows
    public OrderResponseWrapper getAllOrders(Integer limitPerPage) {
        URI uri = baseUrl(ORDERS_ENDPOINT);

        HashMap<String, String> params = new HashMap<>();
        params.put(SEARCH_CRITERIA_PAGE_SIZE, limitPerPage.toString());
        uri = addParameters(uri, params);
        HttpRequest request = get(uri);

        return getRequestWrapped(request, OrderResponseWrapper.class);
    }

    @SneakyThrows
    public OrderResponseWrapper getAllOrders(HashMap<String, String> params) {
        URI uri = baseUrl(ORDERS_ENDPOINT);
        uri = addParameters(uri, params);
        HttpRequest request = get(uri);

        return getRequestWrapped(request, OrderResponseWrapper.class);
    }

    @SneakyThrows
    public OrderResponseWrapper getAllOrders() {
        URI uri = baseUrl(ORDERS_ENDPOINT);

        HashMap<String, String> params = new HashMap<>();
        params.put(SEARCH_CRITERIA_PAGE_SIZE, SEARCH_CRITERIA_PAGE_SIZE_VALUE);
        uri = addParameters(uri, params);
        HttpRequest request = get(uri);

        return getRequestWrapped(request, OrderResponseWrapper.class);
    }

    @SneakyThrows
    public OrderResponse getOrderById(Integer id) {
        String endpoint = ORDERS_ENDPOINT.concat(FORWARD_SLASH).concat(id.toString());
        URI uri = baseUrl(endpoint);
        HttpRequest request = get(uri);

        return getRequestWrapped(request, OrderResponse.class);
    }
}