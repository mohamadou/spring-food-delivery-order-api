package com.mohamadou.springfooddeliveryorderapi.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder @ToString
public class CustomerRequest {

    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private Long cityId;
    private String address;

    @NotBlank(message = "Phone is required")
    @NotNull
    private String phone;

    @NotBlank(message = "Email is required")
    private String email;

    private String password;
}
