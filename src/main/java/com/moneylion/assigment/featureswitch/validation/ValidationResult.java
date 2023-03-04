package com.moneylion.assigment.featureswitch.validation;

import com.moneylion.assigment.featureswitch.request.FeatureAccessCheckRequest;
import jakarta.validation.ConstraintViolation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ValidationResult {
    HashMap<String, java.util.ArrayList<String>> errorMessages;
    Set<ConstraintViolation<?>> violations;
    public <T> ValidationResult(Set<ConstraintViolation<T>> violations) {
        HashMap<String, java.util.ArrayList<String>> messageList  = new HashMap<String, ArrayList<String>>();
        violations.forEach(violation -> {
            if (violation != null) {
                var errors = new ArrayList<String>();
                if(messageList.containsKey(violation.getPropertyPath().toString()))
                    errors = messageList.get(violation.getPropertyPath().toString());
                    if( errors != null) {
                        errors.add(violation.getMessage());
                        messageList.put(violation.getPropertyPath().toString(), errors);
                }
            }
        });
        this.errorMessages = messageList;
    }

    public HashMap<String, ArrayList<String>> getErrorMessages() {
        return errorMessages;
    }
}
