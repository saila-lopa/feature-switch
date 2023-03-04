package com.moneylion.assigment.featureswitch.controllers;

import com.moneylion.assigment.featureswitch.request.FeatureAccessCheckRequest;
import com.moneylion.assigment.featureswitch.request.FeatureAccessCheckRequestFactory;
import com.moneylion.assigment.featureswitch.request.UpdateFeatureAccessRequest;
import com.moneylion.assigment.featureswitch.responses.FeatureAccessCheckResponse;
import com.moneylion.assigment.featureswitch.services.FeatureAccessService;
import com.moneylion.assigment.featureswitch.validation.ValidationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/")
public class FeatureSwitchController {
    private static final Logger log = LoggerFactory.getLogger(FeatureSwitchController.class);

    final Validator validator;
    final FeatureAccessService featureAccessService;
    final FeatureAccessCheckRequestFactory factory;

    public FeatureSwitchController(FeatureAccessService featureAccessService, Validator validator,
                                   FeatureAccessCheckRequestFactory factory) {
        this.featureAccessService = featureAccessService;
        this.validator = validator;
        this.factory = factory;
    }

    @Operation(summary = "Check if the user with given email has access to the feature name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return access status result",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FeatureAccessCheckResponse.class)) }),
            @ApiResponse(responseCode = "422", description = "Invalid data has been passed in parameter",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/feature")
    ResponseEntity<?> checkFeatureAccess(@RequestParam String email, @RequestParam String featureName) throws Exception {
        FeatureAccessCheckRequest request = factory.getFeatureAccessCheckRequest(email, featureName);
        Set<ConstraintViolation<FeatureAccessCheckRequest>> violations = validator.validate(request);
        if(!violations.isEmpty()) {
            log.error("Validation failed for request: " + request);
            log.error(violations.toString());
            return new ResponseEntity<>(new ValidationResult(violations), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        FeatureAccessCheckResponse response = featureAccessService.hasAccess(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Add or update user's access to feature by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Access added/updated successfully"),
            @ApiResponse(responseCode = "304", description = "Not modified"),
            @ApiResponse(responseCode = "422", description = "Invalid data has been passed in parameter",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping("/feature")
    ResponseEntity<?> updateFeatureAccess(@RequestBody UpdateFeatureAccessRequest request) {
        Set<ConstraintViolation<UpdateFeatureAccessRequest>> violations = validator.validate(request);
        log.debug("Feature add or update request for: " + request);
        if(!violations.isEmpty()) {
            log.error("Validation failed with following problems: ");
            log.error(violations.toString());
            return new ResponseEntity<>(new ValidationResult(violations), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        boolean result = featureAccessService.addFeatureAccess(request);
        if(result)
            return new ResponseEntity<>(HttpStatus.valueOf(200));
        else
            return new ResponseEntity<>(HttpStatus.valueOf(304));
    }

}
