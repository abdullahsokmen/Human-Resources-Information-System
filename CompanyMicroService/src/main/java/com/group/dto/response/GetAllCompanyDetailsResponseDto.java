package com.group.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCompanyDetailsResponseDto {
    private String id;
    private String companyName;
    private String title;
    private String mersisNo;
    private String taxNo;
    private String taxOffice;
    private String logoUrl;
    private String phone;
    private String address;
    private String email;
    private int numberOfEmployees;
    private String foundationYear;
    private Date contractStartDate;
    private Date contractEndDate;
    private String position;
}
