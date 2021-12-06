package com.mohamadou.springfooddeliveryorderapi.entity;


import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "offer")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate dateActiveFrom;
    private LocalDate dateActiveTo;
    private LocalDateTime timeActiveFrom;
    private LocalDateTime timeActiveTo;
    private Double offerPrice;

   /* @ManyToMany
    private List<MenuItem> menuItem;*/
}
