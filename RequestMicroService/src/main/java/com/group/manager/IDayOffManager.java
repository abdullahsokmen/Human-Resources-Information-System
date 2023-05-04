package com.group.manager;

import com.group.dto.request.DayOffSaveRequestElasticDto;
import com.group.dto.request.DayOffUpdateRequestElasticDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static com.group.constants.EndPoints.*;

@FeignClient(url = "http://localhost:9100/api/v1/dayoff", decode404 = true, name = "request-dayoff")
public interface IDayOffManager {
    @PostMapping(SAVE)
    public ResponseEntity<Void> requestDayOff(@RequestBody DayOffSaveRequestElasticDto dto);

    @PostMapping(DELETE)
    public ResponseEntity<Void> deleteDayOff(@RequestParam Long id);

    @PostMapping(UPDATE)
    public ResponseEntity<Void> updateDayOff(@RequestBody DayOffUpdateRequestElasticDto dto);
}
