package com.bickyraj.demo.application.clientpermission;

import com.bickyraj.demo.application.UseCase;
import com.bickyraj.demo.service.ClientPermissionService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateClientPermissionUseCase extends UseCase<CreateClientPermissionUseCase.Request, CreateClientPermissionUseCase.Response> {

    @AllArgsConstructor(staticName = "of")
    public static class Request {
        private final String username;
        private final List<String> urls;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class Response {
        private final boolean success;
    }

    private final ClientPermissionService clientPermissionService;

    @Override
    public Response execute(Request request) {
        return new Response(clientPermissionService.upsert(request.username, request.urls));
    }
}
