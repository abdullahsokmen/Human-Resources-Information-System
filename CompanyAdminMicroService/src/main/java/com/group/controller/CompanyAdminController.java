package com.group.controller;

import com.group.dto.request.CompanyAdminRegisterRequestDto;
import com.group.dto.request.CompanyAdminUpdatePasswordRequestDto;
import com.group.dto.request.CompanyAdminUpdateRequestDto;
import com.group.dto.request.ResetPasswordRequestDto;
import com.group.dto.response.CompanyAdminResponseDto;
import com.group.dto.response.GetAllCompanyAdminDetailsResponseDto;
import com.group.exception.CompanyAdminException;
import com.group.exception.EErrorType;
import com.group.service.CompanyAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> register(@RequestBody @Valid CompanyAdminRegisterRequestDto dto){
        return ResponseEntity.ok(companyAdminService.register(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean>update(@RequestBody @Valid CompanyAdminUpdateRequestDto dto){
        return ResponseEntity.ok(companyAdminService.updateAdmin(dto));
    }
    @DeleteMapping(DELETE)
//    @PreAuthorize("hasAuthority('ADMIN')")
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
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> hardDeleteById(@RequestParam Long id){
        return ResponseEntity.ok(companyAdminService.hardDeleteById(id));
    }
    @PostMapping(UPDATE+PASSWORD)
    public ResponseEntity<Boolean>updatePassword(@RequestBody @Valid CompanyAdminUpdatePasswordRequestDto dto){
        if (!dto.getPassword().equals(dto.getRePassword()))
            throw new CompanyAdminException(EErrorType.REGISTER_ERROR_PASSWORD_UNMATCH);
        return ResponseEntity.ok(companyAdminService.updateCompanyAdminPassword(dto));
    }

    @PostMapping(FORGOT)
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordRequestDto dto){
        companyAdminService.resetPassword(dto);
        return ResponseEntity.ok().build();
    }

}
