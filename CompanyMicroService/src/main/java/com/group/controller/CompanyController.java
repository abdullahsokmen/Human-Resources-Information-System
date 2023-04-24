package com.group.controller;

import com.group.dto.request.CompanySaveRequestDto;
import com.group.dto.request.CompanyUpdateRequestDto;
import com.group.dto.response.CompanyResponseDto;
import com.group.dto.response.GetAllCompanyDetailsResponseDto;
import com.group.service.CompanyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.group.constants.EndPoints.*;
@RequiredArgsConstructor
@RequestMapping(COMPANY)
@RestController
public class CompanyController {
    private final CompanyService companyService;

    @PutMapping(SAVE)
    public ResponseEntity<Boolean> saveCompany(@RequestBody CompanySaveRequestDto dto){
        return ResponseEntity.ok(companyService.saveCompany(dto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateCompany(@RequestBody CompanyUpdateRequestDto dto){
        return ResponseEntity.ok(companyService.updateCompany(dto));
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<Boolean> deleteCompany(@RequestParam String id){
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<CompanyResponseDto> findCompanyById(@RequestParam String id){
        return ResponseEntity.ok(companyService.findCompanyById(id));
    }

    @PutMapping(DEACTIVATE)
    public ResponseEntity<Boolean> deactivateCompany(@RequestParam String id){
        return ResponseEntity.ok(companyService.deactivateCompany(id));
    }

    @GetMapping(GETALLDETAILS)
    public ResponseEntity<GetAllCompanyDetailsResponseDto> getAllDetails(@RequestParam String id){
        return ResponseEntity.ok(companyService.getAllDetails(id));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }


}
