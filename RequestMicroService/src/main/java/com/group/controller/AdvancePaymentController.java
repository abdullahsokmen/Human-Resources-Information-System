package com.group.controller;

import com.group.dto.request.CreateAdvancePaymentRequestDto;
import com.group.dto.request.UpdateAdvancePaymentRequestDto;
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
    public ResponseEntity<Boolean>requestAdvancePayment(@RequestBody CreateAdvancePaymentRequestDto dto){
        return ResponseEntity.ok(advancePaymentService.requestAdvancePayment(dto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean>updateAdvancePayment(@RequestBody UpdateAdvancePaymentRequestDto dto){
        return ResponseEntity.ok(advancePaymentService.updateAdvancePayment(dto));
    }
    @PostMapping(CONFIRM)
    public ResponseEntity<Boolean>confirmAdvancePayment(@RequestParam Long id){
        return ResponseEntity.ok(advancePaymentService.confirmAdvancePayment(id));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Boolean>deleteAdvancePayment(@RequestParam Long id){
        return ResponseEntity.ok(advancePaymentService.deletePayment(id));
    }
    @PostMapping(DECLINE)
    public ResponseEntity<Boolean>declineAdvancePayment(@RequestParam Long id){
        return ResponseEntity.ok(advancePaymentService.declineAdvancePayment(id));
    }

}
