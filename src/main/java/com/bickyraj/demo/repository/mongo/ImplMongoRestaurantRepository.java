package com.bickyraj.demo.repository.mongo;

import com.bickyraj.demo.entity.Restaurant;
import com.bickyraj.demo.exception.NotFoundException;
import com.bickyraj.demo.model.RestaurantModel;
import com.bickyraj.demo.repository.RestaurantRepository;
import jakarta.validation.ValidationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ImplMongoRestaurantRepository implements RestaurantRepository {
    private final MongoRestaurantRepository mongoRestaurantRepository;

    public ImplMongoRestaurantRepository(MongoRestaurantRepository mongoRestaurantRepository) {
        this.mongoRestaurantRepository = mongoRestaurantRepository;
    }

    @Override
    public Optional<Restaurant> findById(String id) {
        return mongoRestaurantRepository.findById(id).map(RestaurantModel::toEntity);
    }

    @Override
    public List<Restaurant> findAll() {
        return mongoRestaurantRepository.findAll().stream().map(RestaurantModel::toEntity).toList();
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setName(restaurant.getName());
        restaurantModel.setAddress(restaurant.getAddress());
        restaurantModel.setPhone(restaurant.getPhone());
        restaurantModel.setEmail(restaurant.getEmail());
        try {
            return mongoRestaurantRepository.save(restaurantModel).toEntity();
        } catch (DuplicateKeyException e) {
            throw new ValidationException("Email already exists");
        }
    }

    @Override
    public Optional<Restaurant> findByAddress(String address) {
        return mongoRestaurantRepository.findByAddress(address).map(RestaurantModel::toEntity).stream().findFirst();
    }
}
