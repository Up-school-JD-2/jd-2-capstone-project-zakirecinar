package io.upschool.services;

import io.upschool.dto.*;
import io.upschool.entity.Companies;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import io.upschool.repository.FlightRepository;
import io.upschool.repository.TicketRepository;
import io.upschool.util.CreditCardMaskingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;

    @Autowired
    private CreditCardMaskingUtil maskingUtil;

    public TicketResponse getPurchaseById(TicketSearchDto ticketSearchDto) {
        Ticket ticket = ticketRepository.findById(ticketSearchDto.getId()).get();
        return TicketResponse.builder()
                .id(ticket.getId())
                .passengerName(ticket.getPassengerName())
                .seatNumber(ticket.getSeatNumber())
                .cardNumber(ticket.getCardNumber())
                .flight_id(ticket.getFlight().getId())
                .build();
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
        Ticket ticket = ticketRepository.save(Ticket.builder()
                .passengerName(ticketPurchaseRequest.getPassengerName())
                .seatNumber(ticketPurchaseRequest.getSeatNumber())
                .cardNumber(CreditCardMaskingUtil.maskCreditCardNumber(ticketPurchaseRequest.getCardNumber()))
                .flight(ticketPurchaseRequest.getFlight())
                .build());
        return ticket;
    }


    /*public String purchaseTicketWithMaskedCard(TicketPurchaseRequest ticketPurchaseRequest) {
        String maskedCardNumber = maskingUtil.maskCreditCardNumber(String.valueOf(ticketPurchaseRequest.getCardNumber()));

        // Bilet satın alma işlemini gerçekleştir ve maskelenmiş kart numarasını kullan
        // ...

        return  maskedCardNumber;
    }*/
    public void delete(Long id) {
        var ticket = ticketRepository.findById(id).get();
        ticketRepository.save(ticket);
        ticketRepository.deleteById(id);
    }
}

