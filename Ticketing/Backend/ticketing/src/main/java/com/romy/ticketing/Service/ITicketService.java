package com.romy.ticketing.Service;

import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.DTO.TicketDTO;
import com.romy.ticketing.Model.Ticket;

import java.util.List;
import java.util.Optional;

public interface ITicketService {


    Optional<TicketDTO> findById(Long id);

    List<TicketDTO> findAll();

    TicketDTO crearTicket(TicketDTO ticket);

    TicketDTO updateTicket(TicketDTO ticket);

    String deleteTicket(Long id);
}
