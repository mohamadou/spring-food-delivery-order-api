package com.mohamadou.springfooddeliveryorderapi.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class OfferRequest {

    private LocalDate dateActiveFrom;
    private LocalDate dateActiveTo;
    private LocalTime timeActiveFrom;
    private LocalTime timeActiveTo;
    private Double offerPrice;
}
