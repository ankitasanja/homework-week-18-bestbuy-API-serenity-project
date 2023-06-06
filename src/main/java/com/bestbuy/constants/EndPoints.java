package com.bestbuy.constants;

public class EndPoints {

    //This is Endpoints of products
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String CREATE_PRODUCTS = "/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/products/{productID}";
    public static final String UPDATE_SINGLE_PRODUCT_BY_ID = "/products/{productID}";
    public static final String DELETE_SINGLE_PRODUCT_BY_ID = "/products/{productID}";


    //This is Endpoints of stores
    public static final String GET_ALL_STORES = "/stores";
    public static final String CREATE_STORES = "/stores";
    public static final String GET_SINGLE_STORES_BY_ID = "/stores/{storeID}";
    public static final String UPDATE_SINGLE_STORES_BY_ID = "/stores/{storeID}";
    public static final String DELETE_SINGLE_STORES_BY_ID = "/stores/{storeID}";

}
