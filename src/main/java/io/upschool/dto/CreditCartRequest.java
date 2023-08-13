package io.upschool.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCartRequest {
    private String cardHolderFullName;
    private String cardNumber;
    private String expirationDate;
    private String cvv;
}
