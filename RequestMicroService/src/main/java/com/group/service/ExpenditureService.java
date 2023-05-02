package com.group.service;

import com.group.repository.IExpenditureRepository;
import com.group.repository.entity.Expenditure;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class ExpenditureService extends ServiceManager<Expenditure,Long> {
   private final IExpenditureRepository expenditureRepository;

    public ExpenditureService(IExpenditureRepository expenditureRepository) {
        super(expenditureRepository);
        this.expenditureRepository = expenditureRepository;
    }
}
