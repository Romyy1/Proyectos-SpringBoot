package com.romy.ticketing.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Date date;

    @Column(name="ticket_product")
    @OneToMany(mappedBy = "ticket_id")
    List<TicketProduct> ticketProduct = new ArrayList<>();

    Double total;

}
