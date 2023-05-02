package com.group.mapper;

import com.group.dto.Advancepaymentdto.CreateAdvancePaymentRequestDto;
import com.group.repository.entity.AdvancePayment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAdvancePaymentMapper {
    IAdvancePaymentMapper INSTANCE= Mappers.getMapper(IAdvancePaymentMapper.class);

    AdvancePayment toAdvancePayment(final CreateAdvancePaymentRequestDto dto);
}
