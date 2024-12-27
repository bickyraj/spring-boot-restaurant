package com.bickyraj.demo.repository;

import com.bickyraj.demo.entity.ClientPermission;

import java.util.List;

public interface ClientPermissionRepository {
    Boolean upsert(ClientPermission clientPermission);
    Boolean delete(ClientPermission clientPermission);
    List<ClientPermission> findAll();
    ClientPermission findByUsername(String username);
}
