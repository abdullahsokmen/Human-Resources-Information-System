package com.group.dto;

import com.group.repository.entity.Adress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequestDto {
    private Long id;
    private String phone;
    private String photoUrl;
    private Adress adress;

}
