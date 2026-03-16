package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.RequestRepository;
import repository.UserRepository;

@Controller
public class DispatcherController {

    private final RequestRepository repo;
    private final UserRepository userRepo;

    public DispatcherController(RequestRepository repo, UserRepository userRepo){
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @GetMapping("/dispatcher")
    public String dashboard(Model model){

        model.addAttribute("requests",repo.findAll());
        model.addAttribute("masters",userRepo.findAll());

        return "dispatcher_dashboard";
    }

}