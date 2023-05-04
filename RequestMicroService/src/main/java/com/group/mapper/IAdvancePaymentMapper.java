package com.group.mapper;

import com.group.dto.Advancepaymentdto.request.CreateAdvancePaymentRequestDto;
import com.group.dto.Advancepaymentdto.request.CreateAdvancePaymentRequestElasticDto;
import com.group.dto.Advancepaymentdto.request.UpdateAdvancePaymentRequestElasticDto;
import com.group.dto.Advancepaymentdto.response.AdvancePaymentResponseDto;
import com.group.repository.entity.AdvancePayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAdvancePaymentMapper {
    IAdvancePaymentMapper INSTANCE= Mappers.getMapper(IAdvancePaymentMapper.class);

    AdvancePayment toAdvancePayment(final CreateAdvancePaymentRequestDto dto);
    AdvancePaymentResponseDto fromAdvancePayment(final AdvancePayment advancePayment);
    @Mapping(source = "id",target = "paymentRequestId")
    CreateAdvancePaymentRequestElasticDto fromAdvancePaymentElastic(final AdvancePayment advancePayment);
    @Mapping(source = "id",target = "paymentRequestId")
    UpdateAdvancePaymentRequestElasticDto fromAdvancePaymentElasticUpdate(final AdvancePayment advancePayment);
}
