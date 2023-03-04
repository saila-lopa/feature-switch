package com.moneylion.assigment.featureswitch.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class FeatureAccessCheckRequest {
    @NotEmpty(message = "Email can't be empty or null")
    @Email(regexp = ".+[@].+[\\.].+", message = "Email format is invalid")
    private String email;

    @NotEmpty(message = "Feature name can't be empty or null")
    @Length(min = 1, max = 100, message = "Feature name length should be between 1 and 100 character")
    private String featureName;

    public FeatureAccessCheckRequest(String email, String featureName) {
        this.email = email;
        this.featureName = featureName;
    }

    public String getEmail() {
        return email;
    }

    public String getFeatureName() {
        return featureName;
    }
}
