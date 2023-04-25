package com.group.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAdminResponseDto {
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String companyName;
}
