package org.skypro.skyshop.model.service;

public class ShopError {
    private final String code;
    private final String message;

    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String toString(){
        return code + ": " + message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
