package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequestDto {
    private Long id;
    @NotBlank(message = "Password cannot be blank!")
    @Pattern(message = "Password must be at least 8 characters, with at least one capital letter and a special character!",
            regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=*!])(?=\\S+$).{8,}$")
    private String password;
    private String repassword;
}
