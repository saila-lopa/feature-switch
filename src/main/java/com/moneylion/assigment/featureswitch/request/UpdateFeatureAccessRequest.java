package com.moneylion.assigment.featureswitch.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class UpdateFeatureAccessRequest {
    @NotEmpty(message = "Email can't be empty or null")
    @Email(regexp = ".+[@].+[\\.].+", message = "Email format is invalid")
    private String email;

    @NotEmpty(message = "Feature name can't be empty or null")
    @Length(min = 1, max = 100, message = "Feature name length should be between 1 and 100 character")
    private String featureName;

    private boolean enable;

    public UpdateFeatureAccessRequest(String email, String featureName, boolean enable) {
        this.email = email;
        this.featureName = featureName;
        this.enable = enable;
    }

    public String getEmail() {
        return email;
    }

    public String getFeatureName() {
        return featureName;
    }

    public boolean isEnable() {
        return enable;
    }
}
