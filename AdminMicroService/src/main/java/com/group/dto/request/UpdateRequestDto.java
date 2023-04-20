package com.group.dto.request;

import com.group.repository.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequestDto {
    @NotNull(message = "Id can not be blank")
    private Long id;
    @Pattern(regexp ="^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
    @NotNull(message = "Phone can not be blank")
    private String phone;
    private String photoUrl;
    @NotNull(message = "Adress can not be blank")
    private AddressUpdateRequestDto address;

}
