package com.bickyraj.demo.controller;

import com.bickyraj.demo.application.book.GetAllClientPermissionUseCase;
import com.bickyraj.demo.application.clientpermission.CreateClientPermissionUseCase;
import com.bickyraj.demo.dto.clientpermission.CreateClientPermissionRequestBody;
import com.bickyraj.demo.entity.ClientPermission;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/client-permission/")
@RequiredArgsConstructor
@Validated
public class ClientPermissionController {

    private final CreateClientPermissionUseCase createClientPermissionUseCase;
    private final GetAllClientPermissionUseCase getAllClientPermissionUseCase;

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@RequestBody @Valid CreateClientPermissionRequestBody permission) {
        CreateClientPermissionUseCase.Response response = createClientPermissionUseCase.execute(
                CreateClientPermissionUseCase.Request.of(
                        permission.getUsername(),
                        permission.getUrls()
                )
        );
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", response.isSuccess());
        return ResponseEntity.ok(responseMap);
    }

    @GetMapping("/")
    public ResponseEntity<List<ClientPermission>> getAll() {
        GetAllClientPermissionUseCase.Response response = getAllClientPermissionUseCase.execute();
        return ResponseEntity.ok(response.getClientPermissions());
    }
}
