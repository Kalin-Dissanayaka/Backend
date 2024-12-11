package lk.kalin.Backend;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Vector;

public class TicketPool {
    private static final Logger logger = LoggerFactory.getLogger(TicketPool.class);

    @Setter
    @Getter
    private int maximumTicketCapacity;

    @Setter
    @Getter
    private Vector<Ticket> ticketQueue;

    private int sold = 0;
    private int totalTickets;

    public TicketPool(int maximumTicketCapacity, int totalTickets) {
        this.maximumTicketCapacity = maximumTicketCapacity;
        this.ticketQueue = new Vector<>();
        this.totalTickets = totalTickets;
    }

    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maximumTicketCapacity) {
            try {
                logger.info("Ticket queue is full. Waiting for space...");
                wait();
            } catch (InterruptedException e) {
                logger.error("Thread interrupted while waiting to add ticket", e);
                throw new RuntimeException(e);
            }
        }
        ticketQueue.add(ticket);
        logger.info("Ticket added by thread: {} | Current size: {}", Thread.currentThread().getName(), ticketQueue.size());
        notifyAll();
    }

    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                logger.info("No tickets available. Waiting for tickets...");
                wait();
            } catch (InterruptedException e) {
                logger.error("Thread interrupted while waiting to buy ticket", e);
                throw new RuntimeException(e.getMessage());
            }
        }
        if (sold >= totalTickets) {
            logger.warn("All tickets have been sold. No more tickets to buy.");
            return null; // Stop further purchases
        }

        Ticket ticket = ticketQueue.remove(0);
        sold++;
        logger.info("Ticket bought by thread: {} | Ticket details: {} | Remaining tickets: {}",
                Thread.currentThread().getName(), ticket, ticketQueue.size());
        notifyAll();
        return ticket;
    }


    public int available() {
        logger.debug("Checking available tickets. Current size: {}", ticketQueue.size());
        return ticketQueue.size();
    }

    public int dueTicket() {
        int dueTickets = totalTickets - sold;
        logger.debug("Calculating due tickets. Total: {}, Sold: {}, Due: {}", totalTickets, sold, dueTickets);
        return dueTickets;
    }

    public int getSold() {
        logger.debug("Checking sold tickets. Sold: {}", sold);
        return sold;
    }

    public Ticket[] getTickets() {
        Ticket[] tickets = new Ticket[ticketQueue.size()];
        for (int i = 0; i < ticketQueue.size(); i++) {
            tickets[i] = ticketQueue.get(i);
        }

        return tickets;
    }
}
