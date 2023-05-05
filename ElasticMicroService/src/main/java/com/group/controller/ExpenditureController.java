package com.group.controller;

import com.group.dto.request.CreateExpenditureRequestElasticDto;
import com.group.dto.request.UpdateExpenditureRequestElasticDto;
import com.group.dto.response.ExpenditureResponseDto;
import com.group.repository.entity.Expenditure;
import com.group.service.ExpenditureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @GetMapping(GETONE)
    public ResponseEntity<ExpenditureResponseDto> getOneExpenditure(@RequestParam Long expenditureRequestId){
        return ResponseEntity.ok(expenditureService.getOneExpenditure(expenditureRequestId));
    }
    @GetMapping(GETALL)
    /*@PreAuthorize("hasAuthority('COMPANYADMIN')")*/
    public ResponseEntity<Page<ExpenditureResponseDto>> getAllExpenditure(@RequestParam Integer currentPage){
        return ResponseEntity.ok(expenditureService.getAllExpenditure(currentPage));
    }
    @GetMapping(GETALL+BYPERSONALID)
    public ResponseEntity<Page<ExpenditureResponseDto>> getAllByPersonalId(@PathVariable Long personalId,
                                                                           @RequestParam Integer currentPage){
        return ResponseEntity.ok(expenditureService.getAllByPersonalId(personalId,currentPage));
    }
}
