package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAdminRegisterRequestDto {
    @NotBlank(message = "Name can not be blank")
    private String name;
    @NotBlank(message = "Lastname can not be blank")
    private String lastname;
    @NotBlank(message = "Email can not be blank")
    @Email
    private String email;
    @NotBlank(message = "Phone can not be blank")

    private String phone;
    private String photoUrl;
    private Date birthDate;
    private String birthPlace;
    private String identity;
    private Date dateOfStart;
    private String companyId;
    private String major;
    private String department;
    private AddressCreateRequestDto address;
}
