package com.group.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivateRequestDto {
    private Long id;
    private String activationCode;
}
