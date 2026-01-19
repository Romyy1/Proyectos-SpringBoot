package com.romy.ticketing.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TicketProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "ticket_id", nullable = false)
    @ManyToOne
    Ticket ticket_id;

    @JoinColumn(name = "product_id", nullable = false)
    @ManyToOne
    Product product_id;

    Integer quantity;

    Double price;

    Double total;
}
