package com.group.controller;

import com.group.dto.request.PersonalSaveRequestDto;
import com.group.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.group.constants.EndPoints.*;

@RestController
@RequestMapping(PERSONAL)
@RequiredArgsConstructor
public class PersonalController {
    private final PersonalService personalService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> createPersonal(@RequestBody PersonalSaveRequestDto dto){
        return ResponseEntity.ok(personalService.createPersonal(dto));
    }
}
