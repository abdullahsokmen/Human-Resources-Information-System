package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalUpdateRequestDto {

    private Long id;
    private String photoUrl;
    @NotBlank(message = "Last name can not blank")
    private String name;
    private String secondName;
    @NotBlank(message = "Last name can not blank")
    private String lastname;

    private Date birthDate;
    @NotBlank(message = "Birth Place can not blank")
    private String birthPlace;
    @NotBlank(message = "Identity can not blank")
    private String identity;
    @NotBlank(message = "Major can not blank")
    private String major;
    @NotBlank(message = "Department can not blank")
    private String department;
    @NotBlank(message = "Email can not blank")
    @Email
    private String email;
    @NotBlank(message = "Phone can not blank")
    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$")
    private String phone;
    private AddressCreateRequestDto address;
}
