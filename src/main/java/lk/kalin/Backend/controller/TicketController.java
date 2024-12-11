//package lk.kalin.Backend.controller;
//
//import lk.kalin.Backend.entity.Ticket;
//import lk.kalin.Backend.service.TicketPoolService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/tickets")
//
//public class TicketController {
//
//    @Autowired
//    private TicketPoolService ticketPoolService;
//
//
//
//    @PostMapping("/manageTickets")
//    public ResponseEntity<String> manageTickets() {
//        try {
//            ticketPoolService.manageTickets();
//            return ResponseEntity.ok("Ticket management completed successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Error during ticket management: " + e.getMessage());
//        }
//    }
//}
