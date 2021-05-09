package com.webtech.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExceptionHandler implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public ApiException handleError(WebRequest webRequest) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.BINDING_ERRORS));
        String message = (String) attributes.get("message");
        String path = (String) attributes.get("path");
        int status = (Integer) attributes.get("status");
        ApiException error = new ApiException(message, status, path);
        if (attributes.containsKey("errors")) {
            @SuppressWarnings("unchecked")
            List<FieldError> fieldErrorList = (List<FieldError>) attributes.get("errors");
            Map<String, String> map = new HashMap<>();
            for (FieldError fError : fieldErrorList) {
                map.put(fError.getField(), fError.getDefaultMessage());
            }
            error.setValidationErrors(map);
        }
        return error;
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
