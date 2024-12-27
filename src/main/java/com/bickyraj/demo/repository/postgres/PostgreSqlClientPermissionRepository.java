package com.bickyraj.demo.repository.postgres;

import com.bickyraj.demo.entity.ClientPermission;
import com.bickyraj.demo.model.ClientPermissionModel;
import com.bickyraj.demo.repository.ClientPermissionRepository;
import com.bickyraj.demo.repository.jpa.JpaClientPermissionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostgreSqlClientPermissionRepository implements ClientPermissionRepository {
    private final JpaClientPermissionRepository jpaRepository;

    @Override
    public Boolean upsert(ClientPermission clientPermission) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonUrls = mapper.writeValueAsString(clientPermission.getUrls());
            jpaRepository.upsert(clientPermission.getUsername(), jsonUrls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Boolean delete(ClientPermission clientPermission) {
        jpaRepository.delete(ClientPermissionModel.fromEntity(clientPermission));
        return true;
    }

    @Override
    public List<ClientPermission> findAll() {
        return jpaRepository.findAll().stream().map(ClientPermissionModel::toEntity).toList();
    }

    @Override
    public ClientPermission findByUsername(String username) {
        return jpaRepository
                .findByUsername(username)
                .map(ClientPermissionModel::toEntity).orElse(null);
    }
}
