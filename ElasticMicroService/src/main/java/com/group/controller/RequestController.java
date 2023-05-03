package com.group.controller;

import com.group.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.group.constants.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ELASTIC+REQUEST)
public class RequestController {
    private final RequestService requestService;
}
