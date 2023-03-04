package com.moneylion.assigment.featureswitch.infrastructure.repositories;

import com.moneylion.assigment.featureswitch.models.Feature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, Integer> {
    Feature findByEmailAndFeatureName(String email, String featureName);
}
