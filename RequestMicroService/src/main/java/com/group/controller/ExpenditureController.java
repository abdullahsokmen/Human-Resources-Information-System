package com.group.controller;


import com.group.dto.request.CreateExpenditureRequestDto;
import com.group.dto.request.UpdateExpenditureRequestDto;
import com.group.service.ExpenditureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.group.constants.EndPoints.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(EXPENDITURE)
public class ExpenditureController {
    private final ExpenditureService expenditureService;

    @PostMapping(SAVE)
    /*@PreAuthorize("hasAuthority('PERSONAL')")*/
    public ResponseEntity<Boolean>saveExpenditure(@RequestBody CreateExpenditureRequestDto dto){
        return ResponseEntity.ok(expenditureService.createExpenditure(dto));
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<Boolean>deleteExpenditure(@RequestParam Long id){
        return ResponseEntity.ok(expenditureService.deleteExpenditure(id));
    }
    @PostMapping(UPDATE)
    /*@PreAuthorize("hasAuthority('PERSONAL')")*/
    public ResponseEntity<Boolean>updateExpenditure(@RequestBody UpdateExpenditureRequestDto dto){
        return ResponseEntity.ok(expenditureService.updateExpenditure(dto));
    }
    @PostMapping(CONFIRM)
    /*@PreAuthorize("hasAuthority('COMPANYADMIN')")*/
    public ResponseEntity<Boolean>confirmExpenditure(@RequestParam Long id){
        return ResponseEntity.ok(expenditureService.confirmExpenditure(id));
    }
    @PostMapping(DECLINE)
    /*@PreAuthorize("hasAuthority('COMPANYADMIN')")*/
    public ResponseEntity<Boolean>declineExpenditure(@RequestParam Long id){
        return ResponseEntity.ok(expenditureService.declineExpenditure(id));
    }

}
