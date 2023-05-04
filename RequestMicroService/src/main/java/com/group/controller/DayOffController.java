package com.group.controller;

import com.group.dto.request.DayOffSaveRequestDto;
import com.group.dto.request.DayOffUpdateRequestDto;
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
    public ResponseEntity<Boolean> requestDayOff(@RequestBody DayOffSaveRequestDto dto){
        return ResponseEntity.ok(dayOffService.requestDayOff(dto));
    }

    @PostMapping(DELETE)
    public ResponseEntity<Boolean> deleteDayOff(@RequestParam Long id){
        return ResponseEntity.ok(dayOffService.deleteDayOff(id));
    }

    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> updateDayOff(@RequestBody DayOffUpdateRequestDto dto){
        return ResponseEntity.ok(dayOffService.updateDayOff(dto));
    }

    @PostMapping(ACCEPT)
    public ResponseEntity<Boolean> acceptDayOffRequest(@RequestParam Long id){
        return ResponseEntity.ok(dayOffService.acceptDayOffRequest(id));
    }
    @PostMapping(DECLINE)
    public ResponseEntity<Boolean> declineDayOffRequest(@RequestParam Long id){
        return ResponseEntity.ok(dayOffService.declineDayOffRequest(id));
    }


}
