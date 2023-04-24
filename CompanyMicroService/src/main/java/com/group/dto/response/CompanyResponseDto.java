package com.group.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseDto implements Serializable {
    private String companyName;
    private String title;
    private String phone;
    private String address;
    private String email;
}
