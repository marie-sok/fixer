package tests;

import fixer.FixerApplication;
import fixer.model.Request;
import fixer.model.Status;
import fixer.model.User;
import fixer.repository.RequestRepository;
import fixer.repository.UserRepository;
import fixer.service.RequestService;
import fixer.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FixerApplication.class)
public class FixerApplicationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void fullWorkflowTest() {

        User master = userService.createUser("Master", "MASTER");

        Request r1 = requestService.createRequest(
                new Request("Client1","111","Addr1","Problem1"));

        requestService.assignToMaster(r1.getId(), master.getId());

        Request updated = requestRepository.findById(r1.getId()).orElseThrow();

        assertEquals(Status.IN_PROGRESS, updated.getStatus());

        requestService.completeRequest(r1.getId());

        Request done = requestRepository.findById(r1.getId()).orElseThrow();

        assertEquals(Status.DONE, done.getStatus());

        long count = requestRepository.countByAssignedTo_Id(master.getId());

        assertEquals(1, count);
    }
}