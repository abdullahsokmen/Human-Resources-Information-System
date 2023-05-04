package com.group.controller;

import com.group.dto.Advancepaymentdto.request.CreateAdvancePaymentRequestElasticDto;
import com.group.dto.Advancepaymentdto.request.UpdateAdvancePaymentRequestElasticDto;
import com.group.repository.entity.AdvancePayment;
import com.group.repository.entity.Expenditure;
import com.group.service.AdvancePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.group.constants.EndPoints.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(ADVANCEPAYMENT)
public class AdvancePaymentController {
    private final AdvancePaymentService advancePaymentService;


    @PostMapping(SAVE)
    public ResponseEntity<Void> requestAdvancePayment(@RequestBody CreateAdvancePaymentRequestElasticDto dto){
        advancePaymentService.requestAdvancePayment(dto);
        return ResponseEntity.ok().build();
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Void>updateAdvancePayment(@RequestBody UpdateAdvancePaymentRequestElasticDto dto){
        advancePaymentService.updateAdvancePayment(dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<Void>deleteAdvancePayment(@RequestParam Long id){
        advancePaymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping()
    public ResponseEntity<Iterable<AdvancePayment>>testMethod(){
        return ResponseEntity.ok(advancePaymentService.findAll());
    }
}
