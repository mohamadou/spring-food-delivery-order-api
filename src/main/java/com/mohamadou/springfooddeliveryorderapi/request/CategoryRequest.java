package com.mohamadou.springfooddeliveryorderapi.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class CategoryRequest {

    private Long id;

    @NotBlank(message = "Category Name is required")
    @NotNull
    private String categoryName;
}
