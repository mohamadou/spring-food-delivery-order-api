package com.mohamadou.springfooddeliveryorderapi.exception;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString @Builder
public class ResourceErrorResponse {
    public int status;
    public String message;
    public String error;
    public Long timestamp;
}
