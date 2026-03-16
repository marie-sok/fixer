package fixer.service;

import fixer.model.Role;
import fixer.model.User;
import fixer.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(String name, String role) {
        return repository.save(new User(name, role));
    }

    public User getUser(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean updateRole(Long id, String role) {

        var userOpt = repository.findById(id);

        if (userOpt.isPresent()) {

            User user = userOpt.get();
            user.setRole(Role.valueOf(role.toUpperCase()));

            repository.save(user);

            return true;
        }

        return false;
    }
}