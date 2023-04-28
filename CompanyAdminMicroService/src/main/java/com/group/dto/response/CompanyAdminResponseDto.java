package com.group.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAdminResponseDto implements Serializable {
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String companyId;
}
