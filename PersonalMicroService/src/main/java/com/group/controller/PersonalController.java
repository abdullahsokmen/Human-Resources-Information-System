package com.group.controller;


import com.group.dto.response.PersonalMinorDetailsResponseDto;
import com.group.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group.dto.request.PersonalSaveRequestDto;
import com.group.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.group.constants.EndPoints.*;

@RestController
@RequestMapping(PERSONAL)
@RequiredArgsConstructor
public class PersonalController {
    private final PersonalService personalService;



    @GetMapping(GETMINORDETAILS)
    public ResponseEntity<PersonalMinorDetailsResponseDto> getMinorDetails(@RequestParam Long id) {
        return ResponseEntity.ok(personalService.getMinorDetails(id));
    }
    @PostMapping(SAVE)
    public ResponseEntity<Boolean> createPersonal(@RequestBody PersonalSaveRequestDto dto){
        return ResponseEntity.ok(personalService.createPersonal(dto));

    }
    @GetMapping(GETALLPERSONAL)
    public ResponseEntity<List<PersonalMinorDetailsResponseDto>> getPersonalList(){
        return ResponseEntity.ok(personalService.getPersonalList());
    }
}
