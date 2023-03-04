package com.moneylion.assigment.featureswitch.errors;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.boot.context.properties.bind.Bindable.mapOf;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, List<String>> handleInvalidArgumentExceptions(
//            MethodArgumentNotValidException ex) {
//        log.error("Error occurred: ", ex.getBody().getDetail());
//        List errors = ex.getBindingResult()
//                .getAllErrors().stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.toList());
//        Map<String, List<String>> errorMaps = new HashMap<>();
//        errorMaps.put("error", errors);
//        return errorMaps;
//    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleGenericExceptions(
            Exception ex) {
        log.error("Error occurred: ", ex);
        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", "Some internal error occurred");
        return errors;
    }

}
