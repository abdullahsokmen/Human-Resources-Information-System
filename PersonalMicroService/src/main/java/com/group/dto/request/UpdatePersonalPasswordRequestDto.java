package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePersonalPasswordRequestDto {

    private Long id;
    @NotBlank(message = "Current cannot be blank!")
    private String currentPassword;
    @Size(min = 8,max = 64,message = "Password must be min 8,max 64 character")
    @NotBlank(message = "Password can not be blank")
    @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "The password must be at least 8 characters, contain at least one uppercase and one lowercase letter, numbers and digits")
    private String password;
    private String repassword;
}
