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
    @Column(name = "passangerName",nullable = false,length = 150)
    private String passengerName;
    @Column(name = "seatNumber",nullable = false,length = 150)
    private String seatNumber;
    @Column(name = "cardNumber",nullable = false,length = 80)
    private String cardNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id",nullable = false)
    private Flight flight;
}
