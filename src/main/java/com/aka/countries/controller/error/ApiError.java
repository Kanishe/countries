package com.aka.countries.controller.error;

import lombok.Getter;

import java.util.List;
import java.util.Map;

//{
//    "apiVersion": "2.0",
//        "error": {
//    "code": 404,
//            "message": "File Not Found",
//            "errors": [{
//        "domain": "Calendar",
//                "reason": "ResourceNotFoundException",
//                "message": "File Not Found"
//    }]
//}
//}

@Getter
public class ApiError {

    private final String apiVersion;
    private final Error error;

    public ApiError(String apiVersion, Error error) {
        this.apiVersion = apiVersion;
        this.error = error;
    }

    public ApiError(String apiVersion,
                    String code,
                    String message,
                    String domain,
                    String reason) {
        this.apiVersion = apiVersion;
        this.error = new Error(code, message, List.of(
                new ErrorItem(domain, reason, message)));
    }

    public static ApiError fromAttributesMap(String apiVersion, Map<String, Object> attributes) {
        return new ApiError(
                apiVersion,
                ((Integer) attributes.get("status")).toString(),
                ((String) attributes.getOrDefault("error", "no message found")),
                ((String) attributes.getOrDefault("path", "no path found")),
                ((String) attributes.getOrDefault("error", "no error found")));
    }

    public Map<String, Object> toAttributesMap() {
        return Map.of(
                "apiVersion", apiVersion,
                "error", error
        );
    }

    private record Error(
            String code,
            String message,
            List<ErrorItem> errors
    ) {
    }

    private record ErrorItem(
            String domain,
            String reason,
            String message
    ) {
    }
}
