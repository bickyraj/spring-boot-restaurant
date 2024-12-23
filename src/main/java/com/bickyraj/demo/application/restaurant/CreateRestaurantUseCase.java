package com.bickyraj.demo.application.restaurant;

import com.bickyraj.demo.application.UseCase;
import com.bickyraj.demo.entity.Restaurant;
import com.bickyraj.demo.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateRestaurantUseCase extends UseCase<CreateRestaurantUseCase.Request, CreateRestaurantUseCase.Response> {
    @AllArgsConstructor(staticName = "of")
    public static class Request {
        private final String name;
        private final String address;
        private final String phone;
        private final String email;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class Response {
        private final Boolean success;
    }

    private final RestaurantRepository restaurantRepository;

    @Override
    public Response execute(Request request) {
        Restaurant restaurant = Restaurant.builder()
                .name(request.name)
                .address(request.address)
                .phone(request.phone)
                .email(request.email)
                .build();
        restaurantRepository.save(restaurant);
        return new Response(Boolean.TRUE);
    }
}
