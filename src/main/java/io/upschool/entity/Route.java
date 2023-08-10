package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="route")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sourceAirportCode",nullable = false,length = 3)
    private String sourceAirportCode;
    @Column(name = "destinationAirportCode",nullable = false,length = 3)
    private String destinationAirportCode;
}
