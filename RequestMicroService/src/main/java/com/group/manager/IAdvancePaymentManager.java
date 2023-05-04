package com.group.manager;

import com.group.dto.Advancepaymentdto.request.CreateAdvancePaymentRequestElasticDto;
import com.group.dto.Advancepaymentdto.request.UpdateAdvancePaymentRequestElasticDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.group.constants.EndPoints.*;

@FeignClient(url = "http://localhost:9100/api/v1/advancepayment", decode404 = true, name = "request-advancepayment")
public interface IAdvancePaymentManager {
    @PostMapping(SAVE)
    public ResponseEntity<Void> requestAdvancePayment(@RequestBody CreateAdvancePaymentRequestElasticDto dto);

    @PutMapping(UPDATE)
    public ResponseEntity<Void> updateAdvancePayment(@RequestBody UpdateAdvancePaymentRequestElasticDto dto);

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteAdvancePayment(@RequestParam Long id);
}
