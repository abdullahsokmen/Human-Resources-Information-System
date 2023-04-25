package com.group.controller;

import com.group.CompanyAdminService;
import com.group.dto.request.CompanyAdminUpdateRequestDto;
import com.group.dto.request.RegisterRequestDto;
import com.group.dto.response.CompanyAdminResponseDto;
import com.group.dto.response.GetAllCompanyAdminDetailsResponseDto;
import com.group.repository.entity.CompanyAdmin;
import com.group.service.CompanyAdminServiceClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.group.constants.EndPoints.*;
@RestController
@RequestMapping(COMPANYADMIN)
@RequiredArgsConstructor
public class CompanyAdminController {
    private final CompanyAdminServiceClass companyAdminService;

    @PostMapping(REGISTER)
    public ResponseEntity<Boolean> register(@RequestBody RegisterRequestDto dto){
        return ResponseEntity.ok(companyAdminService.register(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean>update(@RequestBody CompanyAdminUpdateRequestDto dto){
        return ResponseEntity.ok(companyAdminService.updateAdmin(dto));
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<Boolean> deleteCompany(@RequestParam Long id){
        return ResponseEntity.ok(companyAdminService.deleteAdmin(id));
    }
    @GetMapping(FINDBYID)
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
}
