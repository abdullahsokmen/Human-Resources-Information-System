package com.group.controller;

import com.group.service.AdvancePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.group.constants.EndPoints.*;
@RequiredArgsConstructor
@RestController
@RequestMapping(ADVANCEPAYMENT)
public class AdvancePaymenController {
    private final AdvancePaymentService advancePaymentService;

}
