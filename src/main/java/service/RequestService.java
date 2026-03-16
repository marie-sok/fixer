package service;

import entity.Request;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.RequestRepository;

@Service
public class RequestService {

    private final RequestRepository repository;

    public RequestService(RequestRepository repository){
        this.repository = repository;
    }

    @Transactional
    public boolean takeRequest(Long id){

        Request request = repository.findById(id).orElseThrow();

        if(!request.getStatus().equals("assigned")){
            return false;
        }

        request.setStatus();

        repository.save(request);

        return true;
    }
}