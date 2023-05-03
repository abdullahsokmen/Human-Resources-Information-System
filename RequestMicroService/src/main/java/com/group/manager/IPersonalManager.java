package com.group.manager;

import com.group.dto.PersonalInfoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.group.constants.EndPoints.PERSONALINFO;

@FeignClient(url = "http://localhost:9094/api/v1/personal",decode404 = true,name = "request-personal")
public interface IPersonalManager {

    @PostMapping(PERSONALINFO)
    public ResponseEntity<PersonalInfoResponseDto> getPersonalInfo(@RequestParam Long id);
}
