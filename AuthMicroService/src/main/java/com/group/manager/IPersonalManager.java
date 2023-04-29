package com.group.manager;

import com.group.dto.request.ResetPasswordRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.group.constants.EndPoints.FORGOT;

@FeignClient(url = "http://localhost:9094/api/v1/personal",decode404 = true,name = "auth-personal")
public interface IPersonalManager {

    @PostMapping(FORGOT)
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordRequestDto dto);
}
