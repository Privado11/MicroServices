package com.ecommerce.product.exception;

public class NotFoundExceptionEntity extends RuntimeException{
    public NotFoundExceptionEntity() {
    }

    public NotFoundExceptionEntity(String message) {
        super(message);
    }

    public NotFoundExceptionEntity(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundExceptionEntity(Throwable cause) {
        super(cause);
    }

    public NotFoundExceptionEntity(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
