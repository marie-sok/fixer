package fixer.service;

import fixer.model.Request;
import fixer.model.Role;
import fixer.model.Status;
import fixer.model.User;
import fixer.repository.RequestRepository;
import fixer.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    public RequestService(RequestRepository requestRepository,
                          UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public Request createRequest(Request request) {
        request.setStatus(Status.NEW);
        request.setCreatedAt(LocalDateTime.now());

        Request saved = requestRepository.save(request);
        log("CREATE", saved);

        return saved;
    }

    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    public List<Request> getByMaster(Long masterId) {
        return requestRepository.findAll()
                .stream()
                .filter(r -> r.getAssignedTo() != null
                        && r.getAssignedTo().getId().equals(masterId))
                .toList();
    }

    public List<Request> getByClient(Long clientId) {
        return requestRepository.findAll()
                .stream()
                .filter(r -> r.getClient() != null
                        && r.getClient().getId().equals(clientId))
                .toList();
    }

    public List<User> getAllMasters() {
        return userRepository.findByRole(Role.MASTER);
    }

    public Request assignMaster(Long requestId, Long masterId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        User master = userRepository.findById(masterId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (master.getRole() != Role.MASTER) {
            throw new RuntimeException("User is not a master");
        }

        request.setAssignedTo(master);
        request.setStatus(Status.ASSIGNED);

        Request saved = requestRepository.save(request);
        log("ASSIGN", saved);

        return saved;
    }

    public boolean takeRequest(Long requestId, Long masterId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow();

        if (request.getStatus() != Status.ASSIGNED) {
            return false;
        }

        request.setStatus(Status.IN_PROGRESS);

        Request saved = requestRepository.save(request);
        log("TAKE", saved);

        return true;
    }

    public Request completeRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow();

        request.setStatus(Status.DONE);

        Request saved = requestRepository.save(request);
        log("DONE", saved);

        return saved;
    }

    public void delete(Long id) {
        requestRepository.deleteById(id);
    }

    private void log(String action, Request request) {
        String masterName = "none";

        if (request.getAssignedTo() != null) {
            masterName = request.getAssignedTo().getName();
        }

        System.out.println("📌 [" + action + "] Request #" + request.getId()
                + " | status=" + request.getStatus()
                + " | master=" + masterName);
    }

    public void cancelRequest(Long id) {
    }

    public List<Request> getRequestsByStatus(Status status) {
        return List.of();
    }

    public void assignToMaster(Long id, Long id1) {
    }
}