package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditProfileRequestDto {
    private Long id;
    @Pattern(regexp = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$")
    private Date birthDate;
    private String birthPlace;
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$")
    @NotBlank(message = "Identify can not be blank")
    private String identity;
    @Pattern(regexp ="/^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$/")
    @NotNull(message = "Phone can not be blank")
    private String phone;
}
