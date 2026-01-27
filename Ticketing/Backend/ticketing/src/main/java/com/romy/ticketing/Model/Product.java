package com.romy.ticketing.Model;

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

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    List<TicketProduct> ticketProduct = new ArrayList<>();



}
