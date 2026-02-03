package com.romy.ticketing.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nombre;
    Double precio;

    @OneToMany(mappedBy = "product_id")
    @JsonIgnore
    private List<TicketProduct> ticketProduct = new ArrayList<>();


}
