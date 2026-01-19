package com.romy.ticketing.Model.DTO;


import com.romy.ticketing.Model.Product;
import com.romy.ticketing.Model.Ticket;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class TicketProductDTO {

    private Long id;

    private Ticket ticket_id;

    private Product product_id;

    private Integer quantity;

    private Double price;

    private Double total;

}
