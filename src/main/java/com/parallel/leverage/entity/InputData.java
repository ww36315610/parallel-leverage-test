package com.parallel.leverage.entity;

public class InputData {
    private String env;
    private String method;
    private String collectionId;
    private String address;
    private String tokenId;
    private String collectionSlug;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getCollectionSlug() {
        return collectionSlug;
    }

    public void setCollectionSlug(String collectionSlug) {
        this.collectionSlug = collectionSlug;
    }
}
