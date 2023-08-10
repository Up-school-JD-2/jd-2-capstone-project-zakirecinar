package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="flights")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "routeId",nullable = false,length = 100)
    private Long routeId;
    @Column(name = "companyId",nullable = true,length = 100)
    private Long companyId;
    @Column(name = "companyName",nullable = false,length = 20)
    private String companyName;
    @Column(name = "departureTime",nullable = false,length = 6)
    private String departureTime;
    @Column(name = "arrivalTime",nullable = false,length = 6)
    private String arrivalTime;
}
