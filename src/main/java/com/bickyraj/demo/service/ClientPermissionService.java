package com.bickyraj.demo.service;

import com.bickyraj.demo.entity.ClientPermission;
import com.bickyraj.demo.repository.ClientPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientPermissionService {
    private final ClientPermissionRepository clientPermissionRepository;

    public List<ClientPermission> findAll() {
        return clientPermissionRepository.findAll();
    }

    public Boolean upsert(String username, List<String> urls) {
        ClientPermission clientPermission = clientPermissionRepository.findByUsername(username);
        if (clientPermission == null) {
            ClientPermission newClientPermission = new ClientPermission();
            newClientPermission.setUsername(username);
            newClientPermission.setUrls(urls);
            clientPermissionRepository.upsert(newClientPermission);
            return true;
        }

        Set<String> uniqueEndpoints = new HashSet<>(clientPermission.getUrls());
        uniqueEndpoints.addAll(urls);
        List<String> updatedEndpoints = new ArrayList<>(uniqueEndpoints);
        clientPermission.setUrls(updatedEndpoints);
        clientPermissionRepository.delete(clientPermission);
        clientPermissionRepository.upsert(clientPermission);
        return true;
    }
}
