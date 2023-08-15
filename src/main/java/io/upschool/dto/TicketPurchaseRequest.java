package io.upschool.dto;

import io.upschool.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketPurchaseRequest {

    private String passengerName;
    private String seatNumber;
    private String cardNumber;
    private Flight flight;
}
