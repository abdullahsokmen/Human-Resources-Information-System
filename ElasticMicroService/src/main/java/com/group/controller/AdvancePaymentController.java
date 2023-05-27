package com.group.controller;

import com.group.dto.request.CreateAdvancePaymentRequestElasticDto;
import com.group.dto.request.UpdateAdvancePaymentRequestElasticDto;
import com.group.dto.response.AdvancePaymentResponseDto;
import com.group.repository.entity.AdvancePayment;
import com.group.service.AdvancePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<Void> updateAdvancePayment(@RequestBody UpdateAdvancePaymentRequestElasticDto dto){
        advancePaymentService.updateAdvancePayment(dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteAdvancePayment(@RequestParam Long id){
        advancePaymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping(GETONE)
    public ResponseEntity<AdvancePaymentResponseDto> getOneAdvancePayment(@RequestParam Long paymentRequestId){
        return ResponseEntity.ok(advancePaymentService.getOneAdvancePayment(paymentRequestId));
    }
    @GetMapping(GETALL)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Page<AdvancePaymentResponseDto>> getAllAdvancePayment(@RequestParam Integer currentPage){
        return ResponseEntity.ok(advancePaymentService.getAllAdvancePayment(currentPage));
    }
    @GetMapping(GETALL+BYPERSONALID)
    public ResponseEntity<Page<AdvancePaymentResponseDto>> getAllByPersonalId(@PathVariable Long personalId,
                                                                              @RequestParam Integer currentPage){
        return ResponseEntity.ok(advancePaymentService.getAllByPersonalId(personalId,currentPage));
    }
}
