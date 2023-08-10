package io.upschool.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CreditCardInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "card_holder_fullname", nullable = false,length = 50)
    private String cardHolderFullName;
    @Column(name = "card_number",nullable = false,length = 16)
    private String cardNumber;
    @Column(name = "expiration_date",nullable = false,length = 5)
    private String expirationDate;
    @Column(name = "cvv",nullable = false,length = 3)
    private String cvv;
}
