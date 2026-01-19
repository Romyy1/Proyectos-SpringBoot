package com.romy.ticketing.Model.DTO;

import com.romy.ticketing.Model.TicketProduct;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TicketDTO {

    Long id;
    Date date;
    List<TicketProductDTO> ticketProduct;
    Double total;
}
