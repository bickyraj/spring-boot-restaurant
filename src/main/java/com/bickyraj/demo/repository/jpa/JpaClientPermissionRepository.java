package com.bickyraj.demo.repository.jpa;

import com.bickyraj.demo.model.ClientPermissionModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaClientPermissionRepository extends JpaRepository<ClientPermissionModel, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO client_permissions (username, urls) " +
            "VALUES (:username, CAST(:urls AS jsonb))", nativeQuery = true)
    void upsert(String username,  String urls);
    Optional<ClientPermissionModel> findByUsername(String username);
}
