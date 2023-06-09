package com.group.controller;

import com.group.dto.request.CompanySaveRequestDto;
import com.group.dto.request.CompanyUpdateRequestDto;
import com.group.dto.response.CompanyResponseDto;
import com.group.dto.response.GetAllCompanyDetailsResponseDto;
import com.group.service.CompanyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.group.constants.EndPoints.*;
@RequiredArgsConstructor
@RequestMapping(COMPANY)
@RestController
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping(SAVE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> saveCompany(@RequestBody @Valid CompanySaveRequestDto dto){
        return ResponseEntity.ok(companyService.saveCompany(dto));
    }
    @PutMapping(UPDATE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> updateCompany(@RequestBody @Valid CompanyUpdateRequestDto dto){
        return ResponseEntity.ok(companyService.updateCompany(dto));
    }
    @DeleteMapping(DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> deleteCompany(@RequestParam String id){
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<CompanyResponseDto> findCompanyById(@RequestParam String id){
        return ResponseEntity.ok(companyService.findCompanyById(id));
    }

    @PutMapping(DEACTIVATE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> deactivateCompany(@RequestParam String id){
        return ResponseEntity.ok(companyService.deactivateCompany(id));
    }

    @GetMapping(GETALLDETAILS)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GetAllCompanyDetailsResponseDto> getAllDetails(@RequestParam String id){
        return ResponseEntity.ok(companyService.getAllDetails(id));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping(EXIST+BYID)
    public ResponseEntity<Boolean> exitsById(@PathVariable String id){
        return ResponseEntity.ok(companyService.exitsById(id));
    }
    @PutMapping(ADDPERSONAL)
    public ResponseEntity<Boolean>addPersonal(@RequestParam String id){
        return ResponseEntity.ok(companyService.addPersonal(id));
    }
    @PutMapping(DELETEPERSONAL)
    public ResponseEntity<Boolean>deletePersonal(@RequestParam String id){
        return ResponseEntity.ok(companyService.deletePersonal(id));
    }

}
