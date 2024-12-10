package com.bickyraj.demo.repository.mongo;

import com.bickyraj.demo.model.RestaurantModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoRestaurantRepository extends MongoRepository<RestaurantModel, String> {
    Optional<RestaurantModel> findByAddress(String address);
}
