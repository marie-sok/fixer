package fixer.controller;

import fixer.model.Request;
import fixer.model.Status;
import fixer.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    @PostMapping
    public Request create(@RequestBody Request request) {
        return service.createRequest(request);
    }

    @GetMapping
    public List<Request> list(@RequestParam Status status) {
        return service.getRequestsByStatus(status);
    }
}