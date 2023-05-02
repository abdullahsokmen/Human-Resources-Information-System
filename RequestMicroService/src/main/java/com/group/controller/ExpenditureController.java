package com.group.controller;

import com.group.service.ExpenditureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.group.constants.EndPoints.DAYOFF;
import static com.group.constants.EndPoints.EXPENDITURE;

@RequiredArgsConstructor
@RestController
@RequestMapping(EXPENDITURE)
public class ExpenditureController {
    private final ExpenditureService expenditureService;
}
