package fixer.controller;

import fixer.model.User;
import fixer.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users_app")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public User create(@RequestParam String name, @RequestParam String role) {
        return service.createUser(name, role);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return service.getUser(id);
    }

    @PostMapping("/{id}/role")
    public String updateRole(@PathVariable Long id, @RequestParam String role) {
        boolean ok = service.updateRole(id, role);
        return ok ? "OK" : "User not found";
    }
}