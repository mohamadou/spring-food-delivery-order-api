package com.mohamadou.springfooddeliveryorderapi.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter @ToString
public class RestaurantRequest {

    private String name;
    private String address;
    private Long cityId;
}
