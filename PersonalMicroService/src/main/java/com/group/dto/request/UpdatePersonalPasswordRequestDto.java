package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePersonalPasswordRequestDto {

    private Long id;
    @NotBlank(message = "Current cannot be blank!")
    private String currentPassword;
    @NotBlank(message = "Password cannot be blank!")
    private String password;
    private String repassword;
}
