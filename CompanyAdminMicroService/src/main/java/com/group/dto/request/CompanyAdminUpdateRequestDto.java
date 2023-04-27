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
    @NotNull(message = "Birth Date can not null")
    private Date birthDate;
    @NotNull(message = "Birth Place can not null")
    private String birthPlace;
    private String identity;
    @NotNull(message = "Major not null")
    private String major;
    @NotNull(message = "Department can not null")
    private String department;
    @NotNull(message = "Email can not null")
    @Email
    private String email;
    @NotNull(message = "Phone can not null")
    @Pattern(regexp ="/^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$/")
    private String phone;
    private AddressCreateRequestDto address;

}
