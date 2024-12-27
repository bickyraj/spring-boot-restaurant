package com.bickyraj.demo.model;

import com.bickyraj.demo.entity.ClientPermission;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;

@Data
@Entity
@Table(name = "client_permissions")
public class ClientPermissionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(columnDefinition = "jsonb")
    private String urls;

    public List<String> getUrls() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(urls, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }

    public void setUrls(List<String> urls) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.urls = mapper.writeValueAsString(urls);
        } catch (Exception e) {
            throw new RuntimeException("Error converting list to JSON", e);
        }
    }


    public static ClientPermissionModel fromEntity(ClientPermission permission) {
        ClientPermissionModel model = new ClientPermissionModel();
        model.setId(permission.getId());
        model.setUsername(permission.getUsername());
        model.setUrls(permission.getUrls());
        return model;
    }

    public static ClientPermission toEntity(ClientPermissionModel model) {
        ClientPermission permission = new ClientPermission();
        permission.setId(model.getId());
        permission.setUsername(model.getUsername());
        permission.setUrls(model.getUrls());
        return permission;
    }
}
