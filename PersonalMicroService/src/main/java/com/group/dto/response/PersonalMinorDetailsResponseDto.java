package com.group.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalMinorDetailsResponseDto {
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String companyId;

}
