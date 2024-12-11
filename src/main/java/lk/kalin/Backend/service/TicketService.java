package lk.kalin.Backend.service;

import lk.kalin.Backend.entity.Ticket;
import lk.kalin.Backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    // Add a new ticket to the database
    public Ticket addTicket(Ticket ticket) {

        return ticketRepository.save(ticket);
    }

    // Retrieve all tickets
    public List<Ticket> getAllTickets() {

        return ticketRepository.findAll();
    }

    public String bookTicket(String ticketId) {
        return ticketId;
    }
}
