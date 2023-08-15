package io.upschool.services;

import io.upschool.dto.*;
import io.upschool.entity.Companies;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import io.upschool.exception.AirportAlreadySavedException;
import io.upschool.exception.TicketAlreadySavedException;
import io.upschool.exception.TicketNotFoundException;
import io.upschool.repository.FlightRepository;
import io.upschool.repository.TicketRepository;
import io.upschool.util.CreditCardMaskingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;

    @Autowired
    private CreditCardMaskingUtil maskingUtil;

    public TicketResponse getPurchaseById(TicketSearchDto ticketSearchDto) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketSearchDto.getId());

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            return TicketResponse.builder()
                    .id(ticket.getId())
                    .passengerName(ticket.getPassengerName())
                    .seatNumber(ticket.getSeatNumber())
                    .cardNumber(ticket.getCardNumber())
                    .flight_id(ticket.getFlight().getId())
                    .build();
        } else {
            throw new TicketNotFoundException("Ticket not found");
        }
    }

    public List<TicketResponse> getAllTickets() {
        var ticketResponse = ticketRepository.findAll().stream().map(tickets -> TicketResponse.builder()
                .id(tickets.getId())
                .passengerName(tickets.getPassengerName())
                .seatNumber(tickets.getSeatNumber())
                .cardNumber(tickets.getCardNumber())
                .flight_id(tickets.getFlight().getId())
                .build()).toList();
        return ticketResponse;
    }

    public Ticket save(TicketPurchaseRequest ticketPurchaseRequest) {
        if(ticketRepository.existsById(ticketPurchaseRequest.getFlight().getId())){
            throw new TicketAlreadySavedException("There is a flight with this id.");
        }
        Ticket ticket = ticketRepository.save(Ticket.builder()
                .passengerName(ticketPurchaseRequest.getPassengerName())
                .seatNumber(ticketPurchaseRequest.getSeatNumber())
                .cardNumber(CreditCardMaskingUtil.maskCreditCardNumber(ticketPurchaseRequest.getCardNumber()))
                .flight(ticketPurchaseRequest.getFlight())
                .build());
        return ticket;
    }

    public void delete(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticketRepository.delete(ticket);
            System.out.println(id+ " Ticket Cancelled");
        } else {
            throw new TicketNotFoundException("Ticket not found");
        }
    }
}

