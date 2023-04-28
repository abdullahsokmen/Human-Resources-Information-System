package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAdminUpdateRequestDto {
    @NotNull(message = "Id can not null")
    private Long id;
    @NotNull(message = "Photo url can not null")
    private String photoUrl;
    @NotNull(message = "Name not can null")
    private String name;
    @NotNull(message = "Second Name can not null")
    private String secondName;
    @NotNull(message = "Lastname can not null")
    private String lastname;
    private Date birthDate;
    @NotNull(message = "Birth Place can not null")
    private String birthPlace;
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$",message = "Please enter a valid ID number")
    private String identity;
    @NotNull(message = "Major not null")
    private String major;
    @NotNull(message = "Department can not null")
    private String department;
    @NotNull(message = "Email can not null")
    @Email
    private String email;
    @NotNull(message = "Phone can not null")
    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$")
    private String phone;
    private AddressCreateRequestDto address;

}
