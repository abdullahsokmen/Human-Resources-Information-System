package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAdminUpdateRequestDto {
    private Long id;
    private String photoUrl;
    private String name;
    private String secondName;
    private String lastname;
    private Date birthDate;
    private String birthPlace;
    private Date dateOfStart;
    private String major;
    private String department;
    private String email;
    private String phone;
    private String companyName;
}
