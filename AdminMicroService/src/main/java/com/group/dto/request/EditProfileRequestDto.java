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
    private Date birthDate;
    private String birthPlace;
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$")
    @NotBlank(message = "Identify can not be blank")
    private String identity;
    @NotNull(message = "Phone can not be blank")
    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$")
    private String phone;
}
