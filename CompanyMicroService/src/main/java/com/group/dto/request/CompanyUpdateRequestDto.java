package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUpdateRequestDto {
    private String Id;
    private String companyName;
    private String title;
    private String taxOffice;
    private String logoUrl;
    private String phone;
    private String address;
    private String email;
    private Date contractEndDate;
}
