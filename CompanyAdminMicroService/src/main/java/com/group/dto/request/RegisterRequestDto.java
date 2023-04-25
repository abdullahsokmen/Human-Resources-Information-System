package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @NotBlank(message = "Name can not be blank")
    private String name;
    @NotBlank(message = "Lastname can not be blank")
    private String lastname;
    @NotBlank(message = "Email can not be blank")
    @Email
    private String email;
    @NotBlank(message = "Phone can not be blank")
    @Pattern(regexp ="/^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$/")
    private String phone;
}
