package com.bickyraj.demo.filter;

import com.bickyraj.demo.service.ClientPermissionRedisService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientPermissionFilter extends OncePerRequestFilter {
    private final ClientPermissionRedisService clientPermissionRedisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        if (username == null || username.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Forbidden: Missing username header");
            return;
        }

        List<String> forbiddenEndpoints = clientPermissionRedisService.getClientPermissions(username);
        if (forbiddenEndpoints != null) {
            String requestURI = request.getRequestURI();
            if (forbiddenEndpoints.contains(requestURI)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Forbidden: User does not have permission for this endpoint");
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}