package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanySaveRequestDto {
    @NotBlank(message = "Company Name can not be blank")
    private String companyName;
    @NotBlank(message = "Title can not be blank")
    private String title;
    @NotBlank(message = "Mersis No can not be blank")
    private String mersisNo;
    @NotBlank(message = "Tax No can not be blank")
    private String taxNo;
    @NotBlank(message = "Tax Office name can not be blank")
    private String taxOffice;
    @NotBlank(message = "Logo Url can not be blank")
    private String logoUrl;
    @NotBlank(message = "Phone can not be blank")
    @Pattern(regexp ="/^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$/")
    private String phone;
    @NotBlank(message = "Address can not be blank")
    private String address;
    @NotBlank(message = "Email can not be blank")
    @Email
    private String email;
    @NotBlank(message = "Foundation Year can not be blank")
    private String foundationYear;
}
