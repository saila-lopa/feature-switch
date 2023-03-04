package com.moneylion.assigment.featureswitch.infrastructure.repositories;

import com.moneylion.assigment.featureswitch.models.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class FeatureRepositoryTest {

    @Autowired
    private FeatureRepository featureRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(featureRepository).isNotNull();
    }

    @Test
    void whenRightValueAdded_FutureAccess_ShouldAddRequestedAccess() {
        Feature mockFeature = new Feature();
        mockFeature.setFeatureName("jjj");
        mockFeature.setEmail("test@email.com");
        mockFeature.setHasAccess(true);
        Feature createdFeature = featureRepository.save(mockFeature);
        assertThat(createdFeature.getFeatureName().equals(mockFeature.getFeatureName()));
        assertThat(createdFeature.getEmail().equals(mockFeature.getEmail()));
        assertThat(createdFeature.isHasAccess() == mockFeature.isHasAccess());
        assertThat(createdFeature.getId()>0);
    }
    @Test
    void forExistingFutureAccess_findByEmailAndFeatureName_ShouldReturnEntity() {

        Feature mockFeature = new Feature();
        mockFeature.setFeatureName("jjj");
        mockFeature.setEmail("test@email.com");
        mockFeature.setHasAccess(true);
        Feature createdFeature = featureRepository.save(mockFeature);

        Feature queryFeature = featureRepository.findByEmailAndFeatureName(mockFeature.getEmail(), mockFeature.getFeatureName());
        assertThat(queryFeature.getFeatureName().equals(mockFeature.getFeatureName()));
        assertThat(queryFeature.getEmail().equals(mockFeature.getEmail()));
    }

}