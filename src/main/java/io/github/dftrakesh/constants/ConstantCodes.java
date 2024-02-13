package io.github.dftrakesh.constants;

public interface ConstantCodes {
    String PRODUCTS_ENDPOINT = "/products";
    String FORWARD_SLASH = "/";
    String ORDERS_ENDPOINT = "/orders";
    String API_VERSION_V1_ENDPOINT = "/V1";
    String AUTHORIZATION = "Authorization";
    String HTTP_REQUEST_PROPERTY_CONTENT_TYPE = "Content-Type";
    String HTTP_REQUEST_CONTENT_TYPE_JSON = "application/json";
    String BEARER = "Bearer ";
    String SEARCH_CRITERIA_PAGE_SIZE = "searchCriteria[pageSize]";
    String SEARCH_CRITERIA_CURRENT_PAGE = "searchCriteria[currentPage]";
    String SEARCH_CRITERIA_PAGE_SIZE_VALUE = "100000";
    String SEARCH_CRITERIA_CURRENT_PAGE_VALUE = "1";
    String LOCAL_DATE_TIME_FORMAT ="yyyy-MM-dd HH:mm:ss";

    Integer MAX_ATTEMPTS = 50;
    Integer TIME_OUT_DURATION = 3000;
    Integer TOO_MANY_REQUEST_EXCEPTION_CODE = 429;
}