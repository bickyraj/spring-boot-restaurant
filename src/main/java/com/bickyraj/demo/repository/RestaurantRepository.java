package com.bickyraj.demo.repository;

import com.bickyraj.demo.entity.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findById(String id);
    List<Restaurant> findAll();
    Restaurant save(Restaurant restaurant);
    Optional<Restaurant> findByAddress(String address);
}
