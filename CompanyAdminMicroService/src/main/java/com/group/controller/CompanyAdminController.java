package com.group.controller;

import com.group.dto.request.CompanyAdminRegisterRequestDto;
import com.group.dto.request.CompanyAdminUpdateRequestDto;
import com.group.dto.request.UpdateCompanyAdminPasswordRequestDto;
import com.group.dto.request.UpdatePasswordRequestDto;
import com.group.dto.response.CompanyAdminResponseDto;
import com.group.dto.response.GetAllCompanyAdminDetailsResponseDto;
import com.group.exception.CompanyAdminException;
import com.group.exception.EErrorType;
import com.group.service.CompanyAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.group.constants.EndPoints.*;
@RestController
@RequestMapping(COMPANYADMIN)
@RequiredArgsConstructor
public class CompanyAdminController {
    private final CompanyAdminService companyAdminService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> register(@RequestBody @Valid CompanyAdminRegisterRequestDto dto){
        return ResponseEntity.ok(companyAdminService.register(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean>update(@RequestBody @Valid CompanyAdminUpdateRequestDto dto){
        return ResponseEntity.ok(companyAdminService.updateAdmin(dto));
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<Boolean> deleteCompany(@RequestParam Long id){
        return ResponseEntity.ok(companyAdminService.deleteAdmin(id));
    }
    @GetMapping(GETMINORDETAILS)
    public ResponseEntity<CompanyAdminResponseDto> findCompanyById(@RequestParam Long id){
        return ResponseEntity.ok(companyAdminService.findCompanyAdminById(id));
    }
    @GetMapping(GETALLDETAILS)
    public ResponseEntity<GetAllCompanyAdminDetailsResponseDto> getAllDetails(@RequestParam Long id){
        return ResponseEntity.ok(companyAdminService.getAllDetails(id));
    }
    @GetMapping(FINDALL)
    public ResponseEntity<List<CompanyAdminResponseDto>> getAllCompanies(){
        return ResponseEntity.ok(companyAdminService.getAllCompanyAdmins());
    }

    @PostMapping(DEACTIVATE+BYID)
    public ResponseEntity<Boolean> deActivateById(@PathVariable Long id){
        return ResponseEntity.ok(companyAdminService.deActivateById(id));
    }
    @DeleteMapping(HARDDELETE)
    public ResponseEntity<Boolean> hardDeleteById(@RequestParam Long id){
        return ResponseEntity.ok(companyAdminService.hardDeleteById(id));
    }
    @PostMapping(UPDATEPASSWORD)
    public ResponseEntity<Boolean> updatePassword(@RequestBody UpdateCompanyAdminPasswordRequestDto dto){
        if(! dto.getPassword().equals(dto.getRepassword()))
            throw new CompanyAdminException(EErrorType.INVALID_PARAMETER);
        return ResponseEntity.ok(companyAdminService.updatePassword(dto));
    }


}
