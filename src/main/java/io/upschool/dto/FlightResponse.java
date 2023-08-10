package io.upschool.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {
    private Long id;
    private Long routeId;
    private Long companyId;
    private String companyName;
    private String departureTime;
    private String arrivalTime;
}
