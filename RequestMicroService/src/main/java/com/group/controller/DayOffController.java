package com.group.controller;

import com.group.dto.Dayoffdto.DayOffSaveRequestDto;
import com.group.dto.Dayoffdto.DayOffUpdateRequestDto;
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


    @PostMapping(SAVE+DAYOFF)
    public ResponseEntity<Boolean> requestDayOff(@RequestBody DayOffSaveRequestDto dto){
        return dayOffService.requestDayOff(dto);
    }

    @PostMapping(DELETE+DAYOFF)
    public ResponseEntity<Boolean> deleteDayOff(@RequestParam Long id){
        return dayOffService.deleteDayOff(id);
    }

    @PostMapping(UPDATE+DAYOFF)
    public ResponseEntity<Boolean> updateDayOff(@RequestBody DayOffUpdateRequestDto dto){
        return dayOffService.updateDayOff(dto);
    }
}
