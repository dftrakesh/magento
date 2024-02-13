package io.github.dftrakesh;

import io.github.dftrakesh.model.authenticationapi.AccessCredential;
import io.github.dftrakesh.model.productsapi.ProductRequestWrapper;
import io.github.dftrakesh.model.productsapi.ProductResponse;
import io.github.dftrakesh.model.productsapi.Products;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.HashMap;

import static io.github.dftrakesh.constants.ConstantCodes.FORWARD_SLASH;
import static io.github.dftrakesh.constants.ConstantCodes.PRODUCTS_ENDPOINT;
import static io.github.dftrakesh.constants.ConstantCodes.SEARCH_CRITERIA_CURRENT_PAGE;
import static io.github.dftrakesh.constants.ConstantCodes.SEARCH_CRITERIA_CURRENT_PAGE_VALUE;
import static io.github.dftrakesh.constants.ConstantCodes.SEARCH_CRITERIA_PAGE_SIZE;
import static io.github.dftrakesh.constants.ConstantCodes.SEARCH_CRITERIA_PAGE_SIZE_VALUE;

public class ProductsApi extends MagentoSdk {

    public ProductsApi(AccessCredential accessCredential) {
        super(accessCredential);
    }

    @SneakyThrows
    public ProductResponse getAllProducts(Integer limitPerPage, Integer pageNumber) {
        URI uri = baseUrl(PRODUCTS_ENDPOINT);

        HashMap<String, String> params = new HashMap<>();
        params.put(SEARCH_CRITERIA_PAGE_SIZE, limitPerPage.toString());
        params.put(SEARCH_CRITERIA_CURRENT_PAGE, pageNumber.toString());
        uri = addParameters(uri, params);
        HttpRequest request = get(uri);

        return getRequestWrapped(request, ProductResponse.class);
    }

    @SneakyThrows
    public ProductResponse getAllProducts(HashMap<String, String> params) {
        URI uri = baseUrl(PRODUCTS_ENDPOINT);
        uri = addParameters(uri, params);
        HttpRequest request = get(uri);

        return getRequestWrapped(request, ProductResponse.class);
    }

    @SneakyThrows
    public ProductResponse getAllProducts() {
        URI uri = baseUrl(PRODUCTS_ENDPOINT);

        HashMap<String, String> params = new HashMap<>();
        params.put(SEARCH_CRITERIA_PAGE_SIZE, SEARCH_CRITERIA_PAGE_SIZE_VALUE);
        params.put(SEARCH_CRITERIA_CURRENT_PAGE, SEARCH_CRITERIA_CURRENT_PAGE_VALUE);
        uri = addParameters(uri, params);
        HttpRequest request = get(uri);

        return getRequestWrapped(request, ProductResponse.class);
    }

    @SneakyThrows
    public Products getProductBySku(String sku) {
        sku = encodeSku(sku);
        String endpoint = PRODUCTS_ENDPOINT.concat(FORWARD_SLASH).concat((sku));
        URI uri = baseUrl(endpoint);
        HttpRequest request = get(uri);

        return getRequestWrapped(request, Products.class);
    }

    @SneakyThrows
    public Products createProduct(ProductRequestWrapper productRequestWrapper) {
        URI uri = baseUrl(PRODUCTS_ENDPOINT);
        HttpRequest request = post(uri, productRequestWrapper);
        return getRequestWrapped(request, Products.class);
    }

    @SneakyThrows
    public Products updateProduct(String sku, ProductRequestWrapper productRequestWrapper) {
        sku = encodeSku(sku);
        String endpoint = PRODUCTS_ENDPOINT.concat(FORWARD_SLASH).concat(sku);
        URI uri = baseUrl(endpoint);
        HttpRequest request = put(uri, productRequestWrapper);

        return getRequestWrapped(request, Products.class);
    }

    @SneakyThrows
    public String deleteProduct(String sku) {
        sku = encodeSku(sku);
        String endpoint = PRODUCTS_ENDPOINT.concat(FORWARD_SLASH).concat(sku);
        URI uri = baseUrl(endpoint);
        HttpRequest request = delete(uri);

        return getRequestWrapped(request, String.class);
    }
}