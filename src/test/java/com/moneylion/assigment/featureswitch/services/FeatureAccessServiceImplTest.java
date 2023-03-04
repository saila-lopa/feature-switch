package com.moneylion.assigment.featureswitch.services;

import com.moneylion.assigment.featureswitch.infrastructure.repositories.FeatureRepository;
import com.moneylion.assigment.featureswitch.models.Feature;
import com.moneylion.assigment.featureswitch.models.FeatureFactory;
import com.moneylion.assigment.featureswitch.request.FeatureAccessCheckRequest;
import com.moneylion.assigment.featureswitch.request.UpdateFeatureAccessRequest;
import com.moneylion.assigment.featureswitch.responses.FeatureAccessCheckResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeatureAccessServiceImplTest {

    @InjectMocks
    FeatureAccessServiceImpl featureAccessService;

    @Mock
    FeatureRepository featureRepository;

    @Mock
    FeatureFactory featureFactory;

    @Test
    void hasAccess_shouldReturnAccessInfo_forExistingFeatureMap() {

        String mockEmail = "teset@email.com";
        String mockFeatureName = "chat";
        FeatureAccessCheckRequest request = new FeatureAccessCheckRequest(mockEmail, mockFeatureName);

        Feature mockFeature = new Feature();
        mockFeature.setEmail(mockEmail);
        mockFeature.setFeatureName(mockFeatureName);
        mockFeature.setHasAccess(true);

        when(featureRepository.findByEmailAndFeatureName(mockEmail, mockFeatureName)).thenReturn(mockFeature);

        FeatureAccessCheckResponse response = featureAccessService.hasAccess(request);
        assertThat(response.isCanAccess()).isTrue();

    }

    @Test
    void hasAccess_shouldReturnAccessInfo_forNonExistingFeatureMap() {

        String mockEmail = "teset@email.com";
        String mockFeatureName = "chat";
        FeatureAccessCheckRequest request = new FeatureAccessCheckRequest(mockEmail, mockFeatureName);

        when(featureRepository.findByEmailAndFeatureName(mockEmail, mockFeatureName)).thenReturn(null);

        FeatureAccessCheckResponse response = featureAccessService.hasAccess(request);
        assertThat(response.isCanAccess()).isFalse();

    }

    @Test
    void addFeatureAccess_ShouldReturnFalse_ForExistingFeatureMap() {
        String mockEmail = "teset@email.com";
        String mockFeatureName = "chat";
        FeatureAccessCheckRequest request = new FeatureAccessCheckRequest(mockEmail, mockFeatureName);

        Feature mockFeature = new Feature();
        mockFeature.setEmail(mockEmail);
        mockFeature.setFeatureName(mockFeatureName);
        mockFeature.setHasAccess(true);

        UpdateFeatureAccessRequest updateRequest = new UpdateFeatureAccessRequest(mockEmail, mockFeatureName, true);

        when(featureRepository.findByEmailAndFeatureName(mockEmail, mockFeatureName)).thenReturn(mockFeature);
        when(featureFactory.getFeature(updateRequest)).thenReturn(mockFeature);

        boolean result = featureAccessService.addFeatureAccess(updateRequest);
        assertThat(result).isFalse();
    }

    @Test
    void addFeatureAccess_ShouldReturnTrue_ForUpdateMapAdd() {
        String mockEmail = "teset@email.com";
        String mockFeatureName = "chat";
        FeatureAccessCheckRequest request = new FeatureAccessCheckRequest(mockEmail, mockFeatureName);

        Feature mockFeature = new Feature();
        mockFeature.setEmail(mockEmail);
        mockFeature.setFeatureName(mockFeatureName);
        mockFeature.setHasAccess(false);

        UpdateFeatureAccessRequest updateRequest = new UpdateFeatureAccessRequest(mockEmail, mockFeatureName, true);

        when(featureRepository.findByEmailAndFeatureName(mockEmail, mockFeatureName)).thenReturn(mockFeature);
        when(featureFactory.getFeature(updateRequest)).thenReturn(mockFeature);

        boolean result = featureAccessService.addFeatureAccess(updateRequest);
        assertThat(result).isTrue();
    }
    @Test
    void addFeatureAccess_ShouldReturnTrue_ForNewMapAdd() {
        String mockEmail = "teset@email.com";
        String mockFeatureName = "chat";

        Feature mockFeature = new Feature();
        mockFeature.setEmail(mockEmail);
        mockFeature.setFeatureName(mockFeatureName);
        mockFeature.setHasAccess(false);

        UpdateFeatureAccessRequest updateRequest = new UpdateFeatureAccessRequest(mockEmail, mockFeatureName, true);

        when(featureRepository.findByEmailAndFeatureName(mockEmail, mockFeatureName)).thenReturn(null);
        when(featureFactory.getFeature(updateRequest)).thenReturn(mockFeature);

        boolean result = featureAccessService.addFeatureAccess(updateRequest);
        assertThat(result).isTrue();
    }


}