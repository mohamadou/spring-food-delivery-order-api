package com.mohamadou.springfooddeliveryorderapi.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter @ToString
public class RestaurantRequest {

    @NotEmpty(message = "Restaurant Name is required")
    private String name;
    private String address;
    private Long cityId;
}
