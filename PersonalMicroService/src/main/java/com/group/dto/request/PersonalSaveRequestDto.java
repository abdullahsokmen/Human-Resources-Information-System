package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalSaveRequestDto {
    private String photoUrl;
    private String name;
    private String secondName;
    private String lastname;
    private Date birthDate;
    private String birthPlace;
    private String identity;
    private Date dateOfStart;
    private Date dateOfEnd;
    private String major;
    private String department;
    private String email;
    private String phone;
    private String companyId;
    private AddressCreateRequestDto address;
}
