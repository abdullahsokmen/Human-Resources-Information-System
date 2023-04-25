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
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUpdateRequestDto {

    private String Id;
    @NotNull(message = "Company name can not blank")
    private String companyName;
    @NotNull(message = "Title can not blank")
    private String title;
    @NotNull(message = "Tax Office can not blank")
    private String taxOffice;
    @NotNull(message = "Logo Url can not blank")
    private String logoUrl;
    @NotNull(message = "Phone can not blank")
    @Pattern(regexp ="/^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$/")
    private String phone;
    @NotNull(message = "Address can not blank")
    private String address;
    @NotNull(message = "Email can not blank")
    @Email
    private String email;
    @NotNull(message = "Contract End Date can not blank")
    @Pattern(regexp = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$")
    private Date contractEndDate;
}
