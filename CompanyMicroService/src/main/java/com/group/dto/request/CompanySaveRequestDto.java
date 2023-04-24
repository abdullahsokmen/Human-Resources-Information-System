package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanySaveRequestDto {

    private String companyName;
    private String title;
    private String mersisNo;
    private String taxNo;
    private String taxOffice;
    private String logoUrl;
    private String phone;
    private String address;
    private String email;
    private String foundationYear;
}
