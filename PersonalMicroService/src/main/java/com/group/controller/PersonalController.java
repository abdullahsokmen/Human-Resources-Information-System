package com.group.controller;


import com.group.dto.request.PersonalUpdateRequestDto;
import com.group.dto.request.ResetPasswordRequestDto;
import com.group.dto.request.UpdatePersonalPasswordRequestDto;
import com.group.dto.response.GetAllDetailsResponseDto;
import com.group.dto.response.PersonalInfoResponseDto;
import com.group.dto.response.PersonalMinorDetailsResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.PersonalException;
import com.group.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.group.dto.request.PersonalSaveRequestDto;


import javax.validation.Valid;
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
    /*@PreAuthorize("hasAuthority('COMPANYADMIN')")*/
    public ResponseEntity<Boolean> createPersonal(@RequestBody @Valid PersonalSaveRequestDto dto){
        return ResponseEntity.ok(personalService.createPersonal(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updatePersonal(@RequestBody @Valid PersonalUpdateRequestDto dto){
        return ResponseEntity.ok(personalService.updatePersonal(dto));
    }
    @PostMapping(DEACTIVATE+BYID)
 /*   @PreAuthorize("hasAuthority('COMPANYADMIN')")*/
    public ResponseEntity<Boolean> deActivateById(@PathVariable Long id){
        return ResponseEntity.ok(personalService.deActivateById(id));
    }
    @PutMapping(DELETE+BYID)
/*    @PreAuthorize("hasAuthority('COMPANYADMIN')")*/
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(personalService.deletePersonalById(id));
    }
    @DeleteMapping(DELETE+BYID)
   /* @PreAuthorize("hasAuthority('COMPANYADMIN')")*/
    public ResponseEntity<Boolean> hardDeleteById(@PathVariable Long id){
        return ResponseEntity.ok(personalService.hardDeleteById(id));
    }
    @GetMapping(GETALLPERSONAL)
  /*  @PreAuthorize("hasAuthority('COMPANYADMIN')")*/
    public ResponseEntity<List<PersonalMinorDetailsResponseDto>> getPersonalList(){
        return ResponseEntity.ok(personalService.getAllPersonals());
    }
    @PostMapping(UPDATE+PASSWORD)
    public ResponseEntity<Boolean> updatePassword(@RequestBody @Valid UpdatePersonalPasswordRequestDto dto){
        if (!dto.getPassword().equals(dto.getRepassword()))
            throw new PersonalException(EErrorType.REGISTER_ERROR_PASSWORD_UNMATCH);//*
        return ResponseEntity.ok(personalService.updatePassword(dto));
    }
    @GetMapping(GETALLDETAILS)
    public ResponseEntity<GetAllDetailsResponseDto>getAllDetails(@RequestParam Long id){
        return ResponseEntity.ok(personalService.getAllDetails(id));
    }
    @PostMapping(FORGOT)
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordRequestDto dto){
        personalService.resetPassword(dto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(PERSONALINFO)
    public ResponseEntity<PersonalInfoResponseDto>getPersonalInfo(@RequestParam Long id){
        return ResponseEntity.ok(personalService.personalInfo(id));
    }

}
