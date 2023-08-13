package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.Ticket;
import io.upschool.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getTickets(){
        var tickets=ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }
    @PostMapping
    public Ticket createTicket(@RequestBody TicketPurchaseRequest ticketPurchaseRequest){
        return ticketService.save(ticketPurchaseRequest);
    }
    @PostMapping("/ticketID")
    public ResponseEntity<TicketResponse> getPassangerByTicketID(@RequestBody TicketSearchDto ticketSearchDto){
        return ResponseEntity.ok(ticketService.getPurchaseById(ticketSearchDto));
    }


    @DeleteMapping("{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        ticketService.delete(id);
    }
}

