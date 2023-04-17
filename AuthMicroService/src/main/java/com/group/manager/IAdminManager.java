package com.group.manager;

import com.group.dto.request.SaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.group.constants.EndPoints.SAVE;

@FeignClient(url = "http://localhost:9091/api/v1/admin",decode404 = true,name = "auth-admin")
public interface IAdminManager {

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody SaveRequestDto dto);
}
