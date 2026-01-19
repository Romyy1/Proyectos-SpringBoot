package com.romy.ticketing.Service;

import com.romy.ticketing.Mappers.Mapper;
import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.DTO.TicketDTO;
import com.romy.ticketing.Model.Product;
import com.romy.ticketing.Model.Ticket;
import com.romy.ticketing.Model.TicketProduct;
import com.romy.ticketing.Repository.TicketRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements ITicketService{

    @Autowired
    private TicketRepository repository;

    //Buscar ticket por ID
    @Override
    public Optional<TicketDTO> findById(Long id){

        return repository.findById(id).stream().map(Mapper::toDTO).findAny();
    }

    //Devuelve el listado de todos los tickets
    @Override
    public List<TicketDTO> findAll() {
        return repository.findAll().stream().map(Mapper::toDTO).toList();
    }

    //Creamos un ticket nuevo
    @Override
    public TicketDTO crearTicket(TicketDTO ticket) {
        return null;
    }

    //Actualizamos un ticket ya que está creado
    @Override
    public TicketDTO updateTicket(TicketDTO ticket) {
        return null;
    }

    //Eliminamos un ticket ya existente
    @Override
    public String deleteTicket(Long id) {


        return "Se ha eliminado el ticket co id: "+id;
    }

}
