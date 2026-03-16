package controller;

import entity.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repository.RequestRepository;

@Controller
public class RequestController {

    private final RequestRepository repo;

    public RequestController(RequestRepository repo){
        this.repo = repo;
    }

    @GetMapping("/request/create")
    public String form(){
        return "create_request";
    }

    @PostMapping("/request")
    public String create(Request request){

        request.setStatus();

        repo.save(request);

        return "redirect:/request/create";
    }
}