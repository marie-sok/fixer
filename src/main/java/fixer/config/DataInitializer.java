package fixer.config;

import fixer.model.Request;
import fixer.model.Role;
import fixer.model.Status;
import fixer.model.User;
import fixer.repository.RequestRepository;
import fixer.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RequestRepository requestRepository;

    public DataInitializer(UserRepository userRepository,
                           RequestRepository requestRepository) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public void run(String... args) {

        System.out.println("🔥 DataInitializer START");

        if (userRepository.count() > 0) {
            System.out.println("⚠️ Users already exist");
            return;
        }

        User master1 = new User(null, "Остап Бендер", Role.MASTER);
        User master2 = new User(null, "Киса Воробьянинов", Role.MASTER);
        User client = new User(null, "Ипполит", Role.CLIENT);

        userRepository.save(master1);
        userRepository.save(master2);
        userRepository.save(client);

        Request r1 = new Request();
        r1.setClientName("Иван");
        r1.setPhone("12345");
        r1.setAddress("Москва");
        r1.setDescription("Не работает розетка");
        r1.setStatus(Status.NEW);
        r1.setCreatedAt(LocalDateTime.now());

        Request r2 = new Request();
        r2.setClientName("Мария");
        r2.setPhone("67890");
        r2.setAddress("Санкт-Петербург");
        r2.setDescription("Протечка трубы");
        r2.setStatus(Status.NEW);
        r2.setCreatedAt(LocalDateTime.now());

        requestRepository.save(r1);
        requestRepository.save(r2);

        System.out.println("✅ Data initialized");
    }
}