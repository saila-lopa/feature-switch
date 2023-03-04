package com.moneylion.assigment.featureswitch.services;

import com.moneylion.assigment.featureswitch.infrastructure.repositories.FeatureRepository;
import com.moneylion.assigment.featureswitch.models.Feature;
import com.moneylion.assigment.featureswitch.models.FeatureFactory;
import com.moneylion.assigment.featureswitch.request.FeatureAccessCheckRequest;
import com.moneylion.assigment.featureswitch.request.UpdateFeatureAccessRequest;
import com.moneylion.assigment.featureswitch.responses.FeatureAccessCheckResponse;
import org.springframework.stereotype.Component;

@Component
public class FeatureAccessServiceImpl implements FeatureAccessService{

    final
    FeatureRepository featureRepository;
    FeatureFactory featureFactory;

    public FeatureAccessServiceImpl(FeatureRepository featureRepository, FeatureFactory featureFactory) {
        this.featureRepository = featureRepository;
        this.featureFactory = featureFactory;
    }

    @Override
    public FeatureAccessCheckResponse hasAccess(FeatureAccessCheckRequest request) {
        Feature feature = featureRepository.findByEmailAndFeatureName(request.getEmail(), request.getFeatureName());
        if(feature!=null)
            return new FeatureAccessCheckResponse(feature.isHasAccess());
        else
            return new FeatureAccessCheckResponse(false);
    }

    @Override
    public boolean addFeatureAccess(UpdateFeatureAccessRequest request) {
        Feature existingFeature = featureRepository.findByEmailAndFeatureName(request.getEmail(), request.getFeatureName());
        Feature requestFeature = featureFactory.getFeature(request);
        if(existingFeature !=null) {
            if(request.isEnable() != existingFeature.isHasAccess()) {
                featureRepository.save(requestFeature);
                return true;
            } else {
                return false;
            }
        }
        featureRepository.save(requestFeature);
        return true;
    }
}
