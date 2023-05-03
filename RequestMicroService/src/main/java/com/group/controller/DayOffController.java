package com.group.controller;

import com.group.dto.Dayoffdto.request.DayOffSaveRequestDto;
import com.group.dto.Dayoffdto.request.DayOffUpdateRequestDto;
import com.group.dto.Dayoffdto.response.DayOffResponseDto;
import com.group.service.DayOffService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping(GETALLDETAILS)
    public ResponseEntity<DayOffResponseDto> getDayOffDetails (@RequestParam Long id){
        return ResponseEntity.ok(dayOffService.getDayOffDetails(id));
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
