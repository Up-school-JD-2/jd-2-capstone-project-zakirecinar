package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "ticket")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "flight_id",nullable = false,length = 150)
    private Long flightId;
    @Column(name = "passanger_name",nullable = false,length = 150)
    private String passengerName;
    @Column(name = "seat_number",nullable = false,length = 150)
    private String seatNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id",nullable = false,insertable = false,updatable = false)
    private Flight flight;
}
