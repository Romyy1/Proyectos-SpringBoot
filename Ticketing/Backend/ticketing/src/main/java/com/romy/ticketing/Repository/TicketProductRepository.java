package com.romy.ticketing.Repository;

import com.romy.ticketing.Model.DTO.TicketProductDTO;
import com.romy.ticketing.Model.TicketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketProductRepository extends JpaRepository<TicketProduct,Long> {

    @Query("select tp from TicketProduct tp join Ticket t on ?1 = tp.ticket_id")
    List<TicketProductDTO> buscarPorId(Long id);

    List<TicketProduct> findAll();

}
