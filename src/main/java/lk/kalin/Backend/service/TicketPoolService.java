package lk.kalin.Backend.service;

import lk.kalin.Backend.entity.Customer;
import lk.kalin.Backend.entity.Vendor;
import lk.kalin.Backend.repository.VendorRepository;
import lk.kalin.Backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TicketPoolService {

    private final Lock ticketLock = new ReentrantLock(); // ReentrantLock for better control
    private final Condition notFull = ticketLock.newCondition();
    private final Condition notEmpty = ticketLock.newCondition();
    private final List<String> ticketPool = new ArrayList<>(); // In-memory ticket pool
    private final int maximumTicketCapacity = 200;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void addTicket(int vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + vendorId));

        ticketLock.lock();
        try {
            while (ticketPool.size() >= maximumTicketCapacity || vendor.getTotalTickets() <= 0) {
                notFull.await(); // Wait if the pool is full or vendor has no tickets
            }

            // Add ticket to in-memory pool
            String ticket = "Ticket from Vendor " + vendor.getVendorId();
            ticketPool.add(ticket);

            // Decrement the vendor's total tickets
            vendor.setTotalTickets(vendor.getTotalTickets() - 1);
            vendorRepository.save(vendor);

            System.out.println("Vendor " + vendor.getVendorId() + " added ticket. Pool Size: " + ticketPool.size());

            notEmpty.signalAll(); // Notify waiting consumers
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Add ticket interrupted: " + e.getMessage());
        } finally {
            ticketLock.unlock();
        }
    }

    public void retrieveTicketByCustomer(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        ticketLock.lock();
        try {
            while (ticketPool.isEmpty()) {
                notEmpty.await(); // Wait if no tickets are available
            }

            // Retrieve the first ticket from the pool
            String ticket = ticketPool.removeFirst();

            System.out.println("Customer " + customer.getCustomerId() + " retrieved ticket. Pool Size: " + ticketPool.size());

            notFull.signalAll(); // Notify waiting producers
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Retrieve ticket interrupted: " + e.getMessage());
        } finally {
            ticketLock.unlock();
        }
    }

    public void manageTickets() {
        List<Vendor> vendors = vendorRepository.findAll();
        List<Customer> customers = customerRepository.findAll();

        Thread addTicketsThread = new Thread(() -> {
            for (Vendor vendor : vendors) {
                while (vendor.getTotalTickets() > 0) {
                    addTicket(vendor.getVendorId());
                }
            }
        });

        Thread retrieveTicketsThread = new Thread(() -> {
            for (Customer customer : customers) {
                while (customer.getTicketQuantity() > 0) {
                    retrieveTicketByCustomer(customer.getCustomerId());
                }
            }
        });

        addTicketsThread.start();
        retrieveTicketsThread.start();

        try {
            addTicketsThread.join();
            retrieveTicketsThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Ticket management interrupted: " + e.getMessage());
        }
    }
}
