package com.moneylion.assigment.featureswitch.responses;

public class FeatureAccessCheckResponse {
    private boolean canAccess;
    public FeatureAccessCheckResponse(boolean canAccess) {
        this.canAccess = canAccess;
    }

    public boolean isCanAccess() {
        return canAccess;
    }
}
