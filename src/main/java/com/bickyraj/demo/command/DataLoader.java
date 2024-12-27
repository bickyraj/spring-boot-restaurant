package com.bickyraj.demo.command;

import com.bickyraj.demo.service.ClientPermissionRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ClientPermissionRedisService clientPermissionRedisService;

    @Override
    public void run(String... args) throws Exception {
        clientPermissionRedisService.loadClientPermissionsToRedis();
    }
}
