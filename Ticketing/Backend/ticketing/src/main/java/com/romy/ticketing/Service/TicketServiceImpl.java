package com.romy.ticketing.Service;

import com.romy.ticketing.Exceptions.NotFoundException;
import com.romy.ticketing.Mappers.Mapper;
import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.DTO.TicketDTO;
import com.romy.ticketing.Model.DTO.TicketProductDTO;
import com.romy.ticketing.Model.Product;
import com.romy.ticketing.Model.Ticket;
import com.romy.ticketing.Model.TicketProduct;
import com.romy.ticketing.Repository.TicketRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public TicketDTO crearTicket(TicketDTO t) {

        if(t == null) throw new RuntimeException("El ticket es nulo");
        if(t.getTicketProduct() == null) throw new RuntimeException("Necesitamos el detalle del ticket");

        //Creamos el ticket y lo guardamos en un objeto tipo Ticket
        Ticket ticket = Ticket.builder()
                .id(t.getId())
                .total(t.getTotal())
                .date(t.getDate())
                .build();

        //Ahora creamos el ticketProduct DTO para añadirlo

        List<TicketProductDTO> tpDTO = new ArrayList<>();

        for (TicketProductDTO dto : tpDTO){



        }

        //Pasamos el objeto creado al mapper para que nos devuelva su DTO
        return Mapper.toDTO(repository.save(ticket));
    }

    //Actualizamos un ticket ya que está creado
    @Override
    public TicketDTO updateTicket(Long id, TicketDTO ticket) {

        //Primero buscamos si existe el ticket en la base de datos
        Ticket t = repository.findById(id).orElseThrow(()->
                new NotFoundException("Producto no encontrado, no se puede actualizar"));

        t.setTicketProduct(ticket.getTicketProduct().stream().map(Mapper::toDTO).toList());
        t.setDate(ticket.getDate());
        t.setTotal(ticket.getTotal());
        t.setId(ticket.getId());

        return Mapper.toDTO(repository.save(t));
    }

    //Eliminamos un ticket ya existente
    @Override
    public void deleteTicket(Long id) {

        //Buscamos si existe el producto
        if(!repository.existsById(id)){

            throw new NotFoundException("Ticket no encontrado, no se puede eliminar");

        }
        //Lo eliminamos
        repository.deleteById(id);
    }

}
