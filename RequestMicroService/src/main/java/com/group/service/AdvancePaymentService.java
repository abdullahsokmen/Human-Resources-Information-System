package com.group.service;

import com.group.repository.IAdvancePaymenRepository;
import com.group.repository.entity.AdvancePayment;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AdvancePaymentService extends ServiceManager<AdvancePayment,Long> {
    private final IAdvancePaymenRepository advancePaymenRepository;


    public AdvancePaymentService(IAdvancePaymenRepository advancePaymenRepository) {
        super(advancePaymenRepository);
        this.advancePaymenRepository = advancePaymenRepository;
    }
}
