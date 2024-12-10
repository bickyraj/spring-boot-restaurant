package com.bickyraj.demo.model;

import com.bickyraj.demo.entity.Restaurant;
import jakarta.persistence.Id;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurants")
@Setter
public class RestaurantModel {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String phone;
    @NonNull
    @Indexed(unique = true)
    private String email;

    public Restaurant toEntity() {
        return Restaurant.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .phone(this.phone)
                .email(this.email)
                .build();
    }
}
