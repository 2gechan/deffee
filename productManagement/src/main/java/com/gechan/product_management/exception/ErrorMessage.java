package com.gechan.product_management.exception;

import java.util.List;

public class ErrorMessage {
    private List<String> errors;

    public ErrorMessage(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}