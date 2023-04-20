package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @NotBlank(message = "Name cannot be blank!")
    @Size(min = 3,max = 20)
    private String name;
    @NotBlank(message = "Surname can not be blank")
    @Size(min = 2,max = 20)
    private String surname;
    @Email
    private String email;
    @NotBlank(message = "Password cannot be blank!")
    @Pattern(message = "Password must be at least 8 characters, with at least one capital letter and a special character!",
            regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=*!])(?=\\S+$).{8,}$")
    private String password;
    private String role;
}
