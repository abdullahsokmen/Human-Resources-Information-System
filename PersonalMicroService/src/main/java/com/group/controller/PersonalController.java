package com.group.controller;

import com.group.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.group.constants.EndPoints.*;

@RestController
@RequestMapping(PERSONAL)
@RequiredArgsConstructor
public class PersonalController {
    private final PersonalService personalService;
}
