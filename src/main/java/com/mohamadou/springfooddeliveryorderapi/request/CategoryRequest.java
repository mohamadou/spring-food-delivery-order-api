package com.mohamadou.springfooddeliveryorderapi.request;

import com.mohamadou.springfooddeliveryorderapi.entity.OrderDetails;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder @ToString
public class CategoryRequest {

    private Long id;

    @NotEmpty(message = "Category Name is required")
    private String categoryName;

    private String description;
}
