package com.bickyraj.demo.application.book;

import com.bickyraj.demo.application.EmptyRequest;
import com.bickyraj.demo.application.UseCase;
import com.bickyraj.demo.entity.Account;
import com.bickyraj.demo.entity.ClientPermission;
import com.bickyraj.demo.repository.AccountRepository;
import com.bickyraj.demo.repository.ClientPermissionRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllClientPermissionUseCase extends UseCase<EmptyRequest, GetAllClientPermissionUseCase.Response> {

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class Response {
        private final List<ClientPermission> clientPermissions;
    }

    private final ClientPermissionRepository clientPermissionRepository;

    @Override
    public Response execute() {
        return Response.of(clientPermissionRepository.findAll());
    }
}
