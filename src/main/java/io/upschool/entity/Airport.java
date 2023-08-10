package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="airport")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false,length = 150)
    private String name;
    @Column(name="code",nullable = false,length = 3)
    private String code;

}
