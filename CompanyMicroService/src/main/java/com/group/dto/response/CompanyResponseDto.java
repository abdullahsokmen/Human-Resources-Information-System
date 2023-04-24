package com.group.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseDto {
    private String companyName;
    private String title;
    private String phone;
    private String address;
    private String email;
}
