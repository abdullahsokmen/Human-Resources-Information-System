package com.group.service;

import com.group.repository.IDayOffRepository;
import com.group.repository.entity.DayOff;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class DayOffService extends ServiceManager<DayOff,Long> {
    private final IDayOffRepository dayOffRepository;

    public DayOffService(IDayOffRepository dayOffRepository) {
        super(dayOffRepository);
        this.dayOffRepository = dayOffRepository;
    }
}
