package com.moneylion.assigment.featureswitch.controllers;

import com.moneylion.assigment.featureswitch.request.FeatureAccessCheckRequest;
import com.moneylion.assigment.featureswitch.request.FeatureAccessCheckRequestFactory;
import com.moneylion.assigment.featureswitch.request.UpdateFeatureAccessRequest;
import com.moneylion.assigment.featureswitch.responses.FeatureAccessCheckResponse;
import com.moneylion.assigment.featureswitch.services.FeatureAccessService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
//@AutoConfigureMockMvc
class FeatureSwitchControllerTest {

    @Autowired
    FeatureSwitchController featureSwitchController;

    @MockBean
    private FeatureAccessService mockFeatureAccessService;

    @MockBean
    private FeatureAccessCheckRequestFactory mockFactory;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getFeatureAccessStatus_WithValidData_thenCorrectResponse() throws Exception {
        FeatureAccessCheckRequest mockRequest = new FeatureAccessCheckRequest("test@email.com", "ssss");

        when(mockFactory.getFeatureAccessCheckRequest("test@email.com", "ssss")).thenReturn(mockRequest);
        when(mockFeatureAccessService.hasAccess(mockRequest)).thenReturn(new FeatureAccessCheckResponse(true));

        ResponseEntity<?> responseEntity = featureSwitchController.checkFeatureAccess("test@email.com", "ssss");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        FeatureAccessCheckResponse response = (FeatureAccessCheckResponse) responseEntity.getBody();
        assertThat(response.isCanAccess()).isTrue();
    }

    @Test
    public void getFeatureAccessStatus_WithInvalidData_thenThrowValidationError() throws Exception {
        FeatureAccessCheckRequest mockRequest = new FeatureAccessCheckRequest("test@e", "ssss");

        when(mockFactory.getFeatureAccessCheckRequest("test@e", "ssss")).thenReturn(mockRequest);
        when(mockFeatureAccessService.hasAccess(mockRequest)).thenReturn(new FeatureAccessCheckResponse(true));

        ResponseEntity<?> responseEntity = featureSwitchController.checkFeatureAccess("test@e", "ssss");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @Test
    public void updateFeatureAccess_withinvalidDat_ShouldReturnUnprocessableEntity() throws Exception {

        UpdateFeatureAccessRequest mockRequest = new UpdateFeatureAccessRequest("test@", "", true);
        when(mockFeatureAccessService.addFeatureAccess(mockRequest)).thenReturn(true);

        ResponseEntity<?> responseEntity = featureSwitchController.updateFeatureAccess(mockRequest);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

    }
    @Test
    public void updateFeatureAccess_withValidData_ShouldGetCorrectResponse() throws Exception {

        UpdateFeatureAccessRequest mockRequest = new UpdateFeatureAccessRequest("test@email.com", "ssss", true);
        when(mockFeatureAccessService.addFeatureAccess(mockRequest)).thenReturn(true);

        ResponseEntity<?> responseEntity = featureSwitchController.updateFeatureAccess(mockRequest);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    @Test
    public void updateFeatureAccess_repeatingData_ShouldRespondNotModified() throws Exception {
        UpdateFeatureAccessRequest mockRequest = new UpdateFeatureAccessRequest("test@email.com", "ssss", true);
        when(mockFeatureAccessService.addFeatureAccess(mockRequest)).thenReturn(false);

        ResponseEntity<?> responseEntity = featureSwitchController.updateFeatureAccess(mockRequest);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_MODIFIED);

    }
}