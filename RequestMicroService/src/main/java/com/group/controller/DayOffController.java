package com.group.controller;

import com.group.service.DayOffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.group.constants.EndPoints.ADVANCEPAYMENT;
import static com.group.constants.EndPoints.DAYOFF;

@RequiredArgsConstructor
@RestController
@RequestMapping(DAYOFF)
public class DayOffController {
    private final DayOffService dayOffService;
}
