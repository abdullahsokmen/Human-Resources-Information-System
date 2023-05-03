package com.group.controller;

import com.group.dto.Dayoffdto.request.DayOffSaveRequestElasticDto;
import com.group.dto.Dayoffdto.request.DayOffUpdateRequestElasticDto;
import com.group.service.DayOffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.group.constants.EndPoints.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(DAYOFF)
public class DayOffController {
    private final DayOffService dayOffService;
    @PostMapping(SAVE)
    public ResponseEntity<Void> requestDayOff(@RequestBody DayOffSaveRequestElasticDto dto){
        dayOffService.requestDayOff(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(DELETE)
    public ResponseEntity<Void> deleteDayOff(@RequestParam Long id){
        dayOffService.deleteDayOff(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(UPDATE)
    public ResponseEntity<Void> updateDayOff(@RequestBody DayOffUpdateRequestElasticDto dto){
        dayOffService.updateDayOff(dto);
        return ResponseEntity.ok().build();
    }
}
