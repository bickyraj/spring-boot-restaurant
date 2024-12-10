package com.bickyraj.demo.controller;

import com.bickyraj.demo.application.restaurant.CreateRestaurantUseCase;
import com.bickyraj.demo.dto.restaurant.CreateRestaurantRequestBody;
import com.bickyraj.demo.entity.Restaurant;
import com.bickyraj.demo.exception.NotFoundException;
import com.bickyraj.demo.repository.RestaurantRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
@Validated
public class RestaurantController {
    private final CreateRestaurantUseCase createRestaurantUseCase;
    private final RestaurantRepository restaurantRepository;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CreateRestaurantRequestBody requestBody) {
        Map<String, String> response = new HashMap<>();
        response.put("success", createRestaurantUseCase.execute(CreateRestaurantUseCase.Request.of(
                requestBody.getName(),
                requestBody.getAddress(),
                requestBody.getPhone(),
                requestBody.getEmail()
        )).getSuccess().toString());
        response.put("message", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{address}")
    public ResponseEntity<Restaurant> get(@PathVariable String address) {
        Restaurant restaurant = restaurantRepository.findByAddress(address)
                .orElseThrow(() -> new NotFoundException("Could not find restaurant"));
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

}
