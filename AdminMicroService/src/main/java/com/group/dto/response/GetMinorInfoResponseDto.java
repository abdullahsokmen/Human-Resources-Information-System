package com.group.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMinorInfoResponseDto {
    private String photoUrl;
    private String name;
    private String surname;
    private String mail;
    private String password;
}
