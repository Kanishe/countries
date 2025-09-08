package com.aka.countries.exception;

import com.aka.countries.controller.error.ApiError;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class CountryDefaultErrorAttributes extends DefaultErrorAttributes {
    private final String apiVersion;


    public CountryDefaultErrorAttributes(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        ApiError apiError = ApiError.fromAttributesMap(apiVersion, errorAttributes);
        return apiError.toAttributesMap();
    }
}
