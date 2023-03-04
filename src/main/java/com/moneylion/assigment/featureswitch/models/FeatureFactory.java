package com.moneylion.assigment.featureswitch.models;

import com.moneylion.assigment.featureswitch.request.UpdateFeatureAccessRequest;
import org.springframework.stereotype.Component;

@Component
public class FeatureFactory {
    public Feature getFeature(UpdateFeatureAccessRequest request) {
        Feature feature = new Feature();
        feature.setFeatureName(request.getFeatureName());
        feature.setEmail(request.getEmail());
        feature.setHasAccess(request.isEnable());
        return feature;
    }
}
