package com.mohamadou.springfooddeliveryorderapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offer")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate dateActiveFrom;
    private LocalDate dateActiveTo;
    private LocalTime timeActiveFrom;
    private LocalTime timeActiveTo;
    private Double offerPrice;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "in_offer",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id"))
    @ToString.Exclude
    private List<MenuItem> menuItems = new ArrayList<>();


    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }
}
