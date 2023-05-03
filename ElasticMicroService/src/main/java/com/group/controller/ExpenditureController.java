package com.group.controller;

import com.group.dto.Expendituredto.request.CreateExpenditureRequestElasticDto;
import com.group.dto.Expendituredto.request.UpdateExpenditureRequestElasticDto;
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
    public ResponseEntity<Void> saveExpenditure(@RequestBody CreateExpenditureRequestElasticDto dto){
        expenditureService.createExpenditure(dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<Void>deleteExpenditure(@RequestParam Long id){
        expenditureService.deleteExpenditure(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping(UPDATE)
    public ResponseEntity<Void>updateExpenditure(@RequestBody UpdateExpenditureRequestElasticDto dto){
        expenditureService.updateExpenditure(dto);
        return ResponseEntity.ok().build();
    }
}
