package com.group.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivateRequestDto {

    private Long id;
    private String activationCode;
}
