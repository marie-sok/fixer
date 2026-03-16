package fixer.service;

import fixer.model.Request;
import fixer.model.Status;
import fixer.model.User;
import fixer.repository.RequestRepository;
import fixer.repository.UserRepository;
import org.springframework.stereotype.Service;

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
        return requestRepository.save(request);
    }

    public List<Request> getRequestsByStatus(Status status) {
        return requestRepository.findByStatus(status);
    }

    public boolean takeRequest(Long requestId, Long masterId) {

        var requestOpt = requestRepository.findById(requestId);
        var masterOpt = userRepository.findById(masterId);

        if (requestOpt.isEmpty() || masterOpt.isEmpty()) {
            return false;
        }

        Request request = requestOpt.get();
        User master = masterOpt.get();

        request.setAssignedTo(master);
        request.setStatus(Status.IN_PROGRESS);

        requestRepository.save(request);

        return true;
    }

    public void assignToMaster(Long requestId, Long masterId) {
        takeRequest(requestId, masterId);
    }

    public void completeRequest(Long requestId) {

        requestRepository.findById(requestId)
                .ifPresent(req -> {

                    req.setStatus(Status.DONE);
                    requestRepository.save(req);

                });
    }

    public void cancelRequest(Long requestId) {

        requestRepository.findById(requestId)
                .ifPresent(req -> {

                    req.setStatus(Status.CANCELLED);
                    requestRepository.save(req);

                });
    }
}