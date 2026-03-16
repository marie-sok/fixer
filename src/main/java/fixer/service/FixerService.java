package fixer.service;

import fixer.model.Request;
import fixer.model.User;
import fixer.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixerService {

    private final RequestRepository requestRepository;

    public FixerService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void assignRequestsToMaster(User masterId) {
        List<Request> newRequests = requestRepository.findAll();
        for (Request request : newRequests) {
            request.setAssignedTo(masterId);
        }
        requestRepository.saveAll(newRequests);
    }
}