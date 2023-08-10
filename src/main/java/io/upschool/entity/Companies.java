package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "companies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Companies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false,length = 100)
    private String name;
    @Column(name = "country",nullable = false,length = 255)
    private String country;
    @ManyToOne(fetch = FetchType.LAZY)//benim ihtiyacım olduğunda gelsin
    @JoinColumn(name = "airport_id",nullable = false)
    private Airport airport;
    //DTO=Data Transfer Object
}
