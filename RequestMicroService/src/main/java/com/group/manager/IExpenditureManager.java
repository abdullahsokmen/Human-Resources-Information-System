package com.group.manager;

import com.group.dto.Expendituredto.request.CreateExpenditureRequestElasticDto;
import com.group.dto.Expendituredto.request.UpdateExpenditureRequestElasticDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static com.group.constants.EndPoints.*;

@FeignClient(url = "http://localhost:9100/api/v1/expenditure", decode404 = true, name = "request-expenditure")
public interface IExpenditureManager {
    @PostMapping(SAVE)
    public ResponseEntity<Void> saveExpenditure(@RequestBody CreateExpenditureRequestElasticDto dto);

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteExpenditure(@RequestParam Long id);

    @PostMapping(UPDATE)
    public ResponseEntity<Void> updateExpenditure(@RequestBody UpdateExpenditureRequestElasticDto dto);
}
