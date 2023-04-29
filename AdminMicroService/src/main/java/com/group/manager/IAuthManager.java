package com.group.manager;

import com.group.dto.request.RegisterRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.group.constants.EndPoints.*;

@FeignClient(url = "http://localhost:9090/api/v1/auth",decode404 = true,name = "admin-auth")
public interface IAuthManager {

    @PostMapping(SAVE)
    public ResponseEntity<Long> register(@RequestBody RegisterRequestDto dto);

    @DeleteMapping(DELETE+BYID)
    public ResponseEntity<Boolean> deleteByAuthId(@PathVariable Long id);
}
