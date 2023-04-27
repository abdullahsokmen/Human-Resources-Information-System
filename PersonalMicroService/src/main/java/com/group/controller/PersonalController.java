package com.group.controller;


import com.group.dto.request.PersonalUpdateRequestDto;
import com.group.dto.request.UpdatePersonalPasswordRequestDto;
import com.group.dto.response.PersonalMinorDetailsResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.PersonalException;
import com.group.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group.dto.request.PersonalSaveRequestDto;



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

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updatePersonal(@RequestBody PersonalUpdateRequestDto dto){
        return ResponseEntity.ok(personalService.updatePersonal(dto));
    }
    @PostMapping(DEACTIVATE+BYID)
    public ResponseEntity<Boolean> deActivateById(@PathVariable Long id){
        return ResponseEntity.ok(personalService.deActivateById(id));
    }
    @PutMapping(DELETE+BYID)
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(personalService.deletePersonalById(id));
    }
    @DeleteMapping(DELETE+BYID)
    public ResponseEntity<Boolean> hardDeleteById(@PathVariable Long id){
        return ResponseEntity.ok(personalService.hardDeleteById(id));
    }
    @GetMapping(GETALLPERSONAL)
    public ResponseEntity<List<PersonalMinorDetailsResponseDto>> getPersonalList(){
        return ResponseEntity.ok(personalService.getAllPersonals());
    }
    @PostMapping(UPDATE+PASSWORD)
    public ResponseEntity<Boolean> updatePassword(@RequestBody UpdatePersonalPasswordRequestDto dto){
        if (!dto.getPassword().equals(dto.getRepassword()))
            throw new PersonalException(EErrorType.INVALID_PARAMETER);//*
        return ResponseEntity.ok(personalService.updatePassword(dto));
    }
}
