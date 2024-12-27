package com.bickyraj.demo.service;

import com.bickyraj.demo.repository.ClientPermissionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientPermissionRedisService {
    private static final String CLIENT_PERMISSION_KEY = "client_permissions";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, List<String>> hashOperations;
    private final ClientPermissionService clientPermissionService;

    @PostConstruct
    private void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void saveClientPermission(String username, List<String> urls) {
        hashOperations.put(CLIENT_PERMISSION_KEY, username, urls);
    }

    public List<String> getClientPermissions(String username) {
        return hashOperations.get(CLIENT_PERMISSION_KEY, username);
    }

    public void loadClientPermissionsToRedis() {
        clientPermissionService.findAll()
                .forEach(permission -> this.saveClientPermission(permission.getUsername(), permission.getUrls()));
        System.out.println("Client permissions loaded to Redis successfully.");
    }
}
