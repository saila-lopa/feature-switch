package com.moneylion.assigment.featureswitch.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "features", uniqueConstraints={
        @UniqueConstraint(columnNames = {"email", "featureName"})
})
public class Feature {

    @Id
    @GeneratedValue
    private Long id;

    @Email(regexp = ".+[@].+[\\.].+")
    @NotEmpty
    private String email;

    @BooleanFlag
    private boolean hasAccess;

    @NotEmpty
    @Length(min = 1, max = 100)
    private String featureName;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHasAccess() {
        return hasAccess;
    }

    public void setHasAccess(boolean hasAccess) {
        this.hasAccess = hasAccess;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }
}
