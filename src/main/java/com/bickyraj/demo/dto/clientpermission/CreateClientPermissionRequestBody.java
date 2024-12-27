package com.bickyraj.demo.dto.clientpermission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientPermissionRequestBody {
    @NotBlank
    private String username;
    @NotEmpty
    private List<String> urls;
}
