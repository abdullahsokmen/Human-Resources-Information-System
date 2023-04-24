package com.group.dto.response;

import com.group.repository.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllResponseDto {
    private Long id;
    private String photoUrl;
    private String name;
    private String secondName;
    private String surname;
    private Date birthDate;
    private String birthPlace;
    private String identity;
    private String email;
    private Address address;
    private String phone;
    private Date createAt;

}
