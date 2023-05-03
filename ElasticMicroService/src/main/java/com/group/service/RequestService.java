package com.group.service;

import com.group.repository.IRequestRepository;
import com.group.repository.Request;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class RequestService extends ServiceManager<Request,Long> {
    private final IRequestRepository repository;

    public RequestService(IRequestRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
