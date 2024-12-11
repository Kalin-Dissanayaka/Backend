package lk.kalin.Backend.repository;

import lk.kalin.Backend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Optional<Ticket> findFirstByOrderByTicketIdAsc(); // Fetch the first ticket (FIFO)
}
