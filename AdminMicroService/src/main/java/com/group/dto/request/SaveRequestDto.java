package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveRequestDto {
    @NotBlank(message = "Name cannot be blank!")
    @Size(min = 3,max = 20)
    private String name;
    @NotBlank(message = "Surname can not be blank")
    @Size(min = 2,max = 20)
    private String surname;
    @Email(message = "Email must be correct")
    private String email;
}
