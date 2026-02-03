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

    @OneToMany(mappedBy = "ticket_id", cascade = CascadeType.ALL)
    private List<TicketProduct> ticketProduct = new ArrayList<>();

    Double total;

}

