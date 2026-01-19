package com.romy.ticketing.Service;

import com.romy.ticketing.Model.DTO.TicketProductDTO;

import java.util.List;
import java.util.Optional;

public interface ITicketProductService {

    List<TicketProductDTO> buscarPorId(Long id);

    List<TicketProductDTO> findAll();

    TicketProductDTO crearTicketProduct(TicketProductDTO tp);

    TicketProductDTO updateTicketProduct(TicketProductDTO tp);

    String deleteTicketProduct(Long id);

}
