package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditProfileRequestDto {
    private Long id;
    private Date birthDate;
    private String birthPlace;
    private String identity;
    private String phone;
}
