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
    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$")
    private String phone;
    @NotBlank(message = "Photo can not be blank")
    private String photoUrl;
    private Date birthDate;
    @NotBlank(message = "BirthPlace can not be blank")
    private String birthPlace;
    @NotBlank(message = "Identity can not be blank")
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$",message = "Please enter a valid ID number")
    private String identity;

    @NotBlank(message = "Company Id can not be blank")
    private String companyId;
    @NotBlank(message = "Major can not be blank")
    private String major;
    @NotBlank(message = "Department can not be blank")
    private String department;
    private AddressCreateRequestDto address;
}
