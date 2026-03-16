package fixer.repository;

import fixer.model.Request;
import fixer.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    long countByAssignedTo_Id(Long masterId);

    List<Request> findByStatus(Status status);

}