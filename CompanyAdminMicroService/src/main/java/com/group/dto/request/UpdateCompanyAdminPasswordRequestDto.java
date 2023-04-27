package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyAdminPasswordRequestDto {

    private Long id;
    private String currentPassword;
    private String password;
    private String repassword;
}
