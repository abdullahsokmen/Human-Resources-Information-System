package com.group.dto.response;

import com.group.repository.entity.Address;
import lombok.*;
import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetAllDetailsResponseDto {
    private Long id;
    private String photoUrl;
    private String name;
    private String secondName;
    private String lastname;
    private Date birthDate;
    private String birthPlace;
    private String identity;
    private Date dateOfStart;
    private String major;
    private String department;
    private String email;
    private String phone;
    private String companyId;
    private Address address;
}
