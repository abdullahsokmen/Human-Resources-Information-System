package com.group.manager;


import com.group.dto.Advancepaymentdto.request.CreateAdvancePaymentRequestElasticDto;
import com.group.dto.Advancepaymentdto.request.UpdateAdvancePaymentRequestElasticDto;
import com.group.dto.Dayoffdto.request.DayOffSaveRequestDto;
import com.group.dto.Dayoffdto.request.DayOffSaveRequestElasticDto;
import com.group.dto.Dayoffdto.request.DayOffUpdateRequestElasticDto;
import com.group.dto.Expendituredto.request.CreateExpenditureRequestElasticDto;
import com.group.dto.Expendituredto.request.UpdateExpenditureRequestElasticDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.group.constants.EndPoints.*;

@FeignClient(url = "http://localhost:9100/api/v1/elastic", decode404 = true, name = "request-elastic")
public interface IElasticManager {

    @PostMapping(ADVANCEPAYMENT + SAVE)
    public ResponseEntity<Void> requestAdvancePayment(@RequestBody CreateAdvancePaymentRequestElasticDto dto);

    @PutMapping(ADVANCEPAYMENT + UPDATE)
    public ResponseEntity<Void> updateAdvancePayment(@RequestBody UpdateAdvancePaymentRequestElasticDto dto);

    @DeleteMapping(ADVANCEPAYMENT + DELETE)
    public ResponseEntity<Void> deleteAdvancePayment(@RequestParam Long id);

    @PostMapping(DAYOFF + SAVE)
    public ResponseEntity<Void> requestDayOff(@RequestBody DayOffSaveRequestElasticDto dto);

    @PostMapping(DAYOFF + DELETE)
    public ResponseEntity<Void> deleteDayOff(@RequestParam Long id);

    @PostMapping(DAYOFF + UPDATE)
    public ResponseEntity<Void> updateDayOff(@RequestBody DayOffUpdateRequestElasticDto dto);

    @PostMapping(EXPENDITURE + SAVE)
    public ResponseEntity<Void> saveExpenditure(@RequestBody CreateExpenditureRequestElasticDto dto);

    @DeleteMapping(EXPENDITURE + DELETE)
    public ResponseEntity<Void> deleteExpenditure(@RequestParam Long id);

    @PostMapping(EXPENDITURE + UPDATE)
    public ResponseEntity<Void> updateExpenditure(@RequestBody UpdateExpenditureRequestElasticDto dto);
}

