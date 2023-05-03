package com.group.controller;

import com.group.dto.Advancepaymentdto.request.CreateAdvancePaymentRequestDto;
import com.group.dto.Advancepaymentdto.request.UpdateAdvancePaymentRequestDto;
import com.group.dto.Advancepaymentdto.response.AdvancePaymentResponseDto;
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

    @PostMapping(SAVEADVANCEPAYMENT)
    public ResponseEntity<Boolean>requestAdvancePayment(@RequestBody CreateAdvancePaymentRequestDto dto){
        return ResponseEntity.ok(advancePaymentService.requestAdvancePayment(dto));
    }
    @PutMapping(UPDATEADVANCEPAYMENT)
    public ResponseEntity<Boolean>updateAdvancePayment(@RequestBody UpdateAdvancePaymentRequestDto dto){
        return ResponseEntity.ok(advancePaymentService.updateAdvancePayment(dto));
    }
    @PostMapping(CONFIRMADVANCEPAYMENT)
    public ResponseEntity<Boolean>confirmAdvancePayment(@RequestParam Long id){
        return ResponseEntity.ok(advancePaymentService.confirmAdvancePayment(id));
    }
    @DeleteMapping(DELETEADVANCEPAYMENT)
    public ResponseEntity<Boolean>deleteAdvancePayment(@RequestParam Long id){
        return ResponseEntity.ok(advancePaymentService.deletePayment(id));
    }
    @PostMapping(DECLINEADVANCEPAYMENT)
    public ResponseEntity<Boolean>declineAdvancePayment(@RequestParam Long id){
        return ResponseEntity.ok(advancePaymentService.declineAdvancePayment(id));
    }
    @GetMapping(GETALLDETAILS+ADVANCEPAYMENT)
    public ResponseEntity<AdvancePaymentResponseDto>getDetailsOfAdvancePaymentDto(@RequestParam Long id){
        return ResponseEntity.ok(advancePaymentService.getDetails(id));
    }


}
