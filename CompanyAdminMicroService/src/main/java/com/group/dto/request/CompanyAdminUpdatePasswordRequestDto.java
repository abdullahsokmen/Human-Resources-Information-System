package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAdminUpdatePasswordRequestDto {
    private Long id;
    private String currentPassword;
    private String password;
    private String rePassword;

}
