package fixer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import fixer.service.RequestService;

@Controller
public class MasterController {

    private final RequestService service;

    public MasterController(RequestService service) {
        this.service = service;
    }

    @PostMapping("/take/{requestId}/{masterId}")
    @ResponseBody
    public String take(@PathVariable Long requestId, @PathVariable Long masterId) {

        boolean ok = service.takeRequest(requestId, masterId);

        if (!ok) {
            return "409 Conflict";
        }

        return "OK";
    }
}