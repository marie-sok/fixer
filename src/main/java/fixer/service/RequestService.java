package fixer.service;

import fixer.model.Request;
import fixer.model.Status;
import fixer.model.User;
import fixer.repository.RequestRepository;
import fixer.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    public RequestService(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public Request createRequest(Request request) {
        request.setStatus(Status.NEW);
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }

    public List<Request> getRequestsByStatus(Status status) {
        return requestRepository.findByStatus(status);
    }

    @Transactional
    public boolean takeRequest(Long requestId, Long masterId) {
        Optional<Request> reqOpt = requestRepository.findById(requestId);
        if(reqOpt.isEmpty()) return false;

        Request req = reqOpt.get();
        if(req.getAssignedTo() != null) return false;

        User master = userRepository.findById(masterId).orElse(null);
        if(master == null) return false;

        req.setAssignedTo(master);
        req.setStatus(Status.IN_PROGRESS);
        req.setUpdatedAt(LocalDateTime.now());
        requestRepository.save(req);
        return true;
    }

    @Transactional
    public boolean completeRequest(Long requestId, Long masterId) {
        Optional<Request> reqOpt = requestRepository.findById(requestId);
        if(reqOpt.isEmpty()) return false;

        Request req = reqOpt.get();
        if(req.getAssignedTo() == null || !req.getAssignedTo().getId().equals(masterId)) return false;

        req.setStatus(Status.DONE);
        req.setUpdatedAt(LocalDateTime.now());
        requestRepository.save(req);
        return true;
    }

    @Transactional
    public boolean cancelRequest(Long requestId) {
        Optional<Request> reqOpt = requestRepository.findById(requestId);
        if(reqOpt.isEmpty()) return false;

        Request req = reqOpt.get();
        req.setStatus(Status.CANCELLED);
        req.setUpdatedAt(LocalDateTime.now());
        requestRepository.save(req);
        return true;
    }

    public void completeRequest(Long id) {
    }
}