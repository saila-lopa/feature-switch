package com.moneylion.assigment.featureswitch.request;

import org.springframework.stereotype.Component;

@Component
public class FeatureAccessCheckRequestFactory {
    public FeatureAccessCheckRequest getFeatureAccessCheckRequest(String email, String featureName) {
        return new FeatureAccessCheckRequest(email, featureName);
    }
}
