package com.moneylion.assigment.featureswitch.services;

import com.moneylion.assigment.featureswitch.request.FeatureAccessCheckRequest;
import com.moneylion.assigment.featureswitch.request.UpdateFeatureAccessRequest;
import com.moneylion.assigment.featureswitch.responses.FeatureAccessCheckResponse;
import org.springframework.stereotype.Component;

@Component
public interface FeatureAccessService {
    FeatureAccessCheckResponse hasAccess(FeatureAccessCheckRequest request);
    boolean addFeatureAccess(UpdateFeatureAccessRequest request);
}
