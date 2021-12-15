package com.mohamadou.springfooddeliveryorderapi.request;

import com.mohamadou.springfooddeliveryorderapi.entity.Category;
import com.mohamadou.springfooddeliveryorderapi.entity.Offer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MenuItemRequest {

    @NotEmpty(message = "ItemName is required")
    private String itemName;
    private Long categoryId;
    private String description;
    private String ingredients;
    private String recipe;
    private Double price;
    private Boolean active;
    private List<Offer> offers;
}
