package com.mohamadou.springfooddeliveryorderapi.request;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder @ToString
public class CustomerRequest {

    private Long id;

    @NotEmpty(message = "Firstname is required")
    @Size(min = 3, max = 30, message = "The length of the firstName must be between 2 and 30 characters.")
    private String firstName;

    @NotBlank(message   = "Lastname is required")
    @Size(min = 3, max = 30, message = "The length of the lastName must be between 2 and 30 characters.")
    private String lastName;

    @NotNull(message   = "City is required")
    @Positive
    private Long cityId;

    private String address;

    @NotBlank(message = "Phone is required")
    @Size(min = 3, max = 30, message = "The length of the phone must be between 6 and 30 characters.")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    private String password;
}
