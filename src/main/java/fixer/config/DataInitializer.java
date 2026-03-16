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

    public DataInitializer(UserRepository userRepository, RequestRepository requestRepository) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Проверим, что база пустая
        if(userRepository.count() == 0) {
            // Создаём мастеров
            User master1 = new User("Остап Бендер", Role.MASTER);
            User master2 = new User("Киса Воробьянинов", Role.MASTER);
            userRepository.save(master1);
            userRepository.save(master2);

            // Создаём клиентов
            User client1 = new User("Иван Иванов", Role.CLIENT);
            User client2 = new User("Мария Петрова", Role.CLIENT);
            userRepository.save(client1);
            userRepository.save(client2);

            // Создаём заявки
            Request r1 = new Request();
            r1.setClientName(client1.getName());
            r1.setPhone("1234567890");
            r1.setAddress("ул. Ленина 12");
            r1.setDescription("Протекает кран");
            r1.setStatus(Status.NEW);
            r1.setCreatedAt(LocalDateTime.now());
            r1.setUpdatedAt(LocalDateTime.now());
            requestRepository.save(r1);

            Request r2 = new Request();
            r2.setClientName(client2.getName());
            r2.setPhone("0987654321");
            r2.setAddress("ул. Пушкина 7");
            r2.setDescription("Не греет вода");
            r2.setStatus(Status.NEW);
            r2.setCreatedAt(LocalDateTime.now());
            r2.setUpdatedAt(LocalDateTime.now());
            requestRepository.save(r2);
        }

    }
}