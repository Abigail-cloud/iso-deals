package com.deals.isodeals.exception;

import org.springframework.util.StringUtils;

public class CustomApiException extends RuntimeException {

    private String localeKey;
    private Object[] arguments;

    public CustomApiException(String message) {
        super(message);
    }

    public CustomApiException(String localeKey, Object... arguments) {
        super("error.custom");
        this.localeKey = localeKey;
        this.arguments = arguments;
    }

    public boolean isLocaleException() {
        return StringUtils.hasText(localeKey);
    }

    public String getLocaleKey() {
        return localeKey;
    }

    public Object[] getArguments() {
        return arguments;
    }
}