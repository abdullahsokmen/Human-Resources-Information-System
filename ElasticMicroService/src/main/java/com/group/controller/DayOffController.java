package com.group.controller;

import com.group.dto.request.DayOffSaveRequestElasticDto;
import com.group.dto.request.DayOffUpdateRequestElasticDto;
import com.group.dto.response.DayOffResponseDto;
import com.group.service.DayOffService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping(GETONE)
    public ResponseEntity<DayOffResponseDto> getOneDayOff(@RequestParam Long dayOffRequestId){
        return ResponseEntity.ok(dayOffService.getOneDayOff(dayOffRequestId));
    }
    @GetMapping(GETALL)
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Page<DayOffResponseDto>> getAllDayOff(@RequestParam Integer currentPage){
        return ResponseEntity.ok(dayOffService.getAllDayOff(currentPage));
    }
    @GetMapping(GETALL+BYPERSONALID)
    public ResponseEntity<Page<DayOffResponseDto>> getAllByPersonalId(@PathVariable Long personalId,
                                                                      @RequestParam Integer currentPage){
        return ResponseEntity.ok(dayOffService.getAllByPersonalId(personalId,currentPage));
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
