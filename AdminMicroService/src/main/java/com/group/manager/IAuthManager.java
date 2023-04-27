package com.group.manager;

import com.group.dto.request.RegisterRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.group.constants.EndPoints.SAVE;

@FeignClient(url = "http://localhost:9090/api/v1/auth",decode404 = true,name = "admin-auth")

public interface IAuthManager {

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> register(@RequestBody RegisterRequestDto dto);
}
