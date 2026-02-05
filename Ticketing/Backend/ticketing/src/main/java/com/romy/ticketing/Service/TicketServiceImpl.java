package com.romy.ticketing.Service;

import com.romy.ticketing.Exceptions.NotFoundException;
import com.romy.ticketing.Mappers.Mapper;
import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.DTO.TicketDTO;
import com.romy.ticketing.Model.DTO.TicketProductDTO;
import com.romy.ticketing.Model.Product;
import com.romy.ticketing.Model.Ticket;
import com.romy.ticketing.Model.TicketProduct;
import com.romy.ticketing.Repository.ProductRepository;
import com.romy.ticketing.Repository.TicketProductRepository;
import com.romy.ticketing.Repository.TicketRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

@Service
public class TicketServiceImpl implements ITicketService{

    @Autowired
    private TicketRepository trepository;

    @Autowired
    private ProductRepository prepository;

    @Autowired
    private TicketProductRepository tprepository;

    @Autowired
    private Mapper mapper;

    //Buscar ticket por ID
    @Override
    public Optional<TicketDTO> findById(Long id){

        return trepository.findById(id).stream().map(mapper::toDTO).findAny();
    }

    //Devuelve el listado de todos los tickets
    @Override
    public List<TicketDTO> findAll() {
        return trepository.findAll().stream().map(mapper::toDTO).toList();
    }

    //Creamos un ticket nuevo
    @Override
    public TicketDTO crearTicket(TicketDTO t) {

        if(t == null) throw new RuntimeException("El ticket es nulo");
        if(t.getTicketProduct() == null) throw new RuntimeException("Necesitamos el detalle del ticket");

        //Ahora creamos el ticketProduct DTO para añadirlo
        List<TicketProduct> tp = new ArrayList<>();
        Double total = 0.0;
        for (TicketProductDTO dto : t.getTicketProduct()){

            Product p = prepository.findById(dto.getProduct_id().getId()).orElseThrow(() ->  new NotFoundException("Ticket no encontrado, no se puede actualizar"));

            TicketProduct ticketP = new TicketProduct();

            ticketP.setProduct_id(p);
            ticketP.setQuantity(dto.getQuantity());
            total +=p.getPrecio() * dto.getQuantity();

            tp.add(ticketP);

        }

        //Double total=  tp.stream().map(TicketProduct::getTotal).reduce(0.0,Double::sum);

        //Creamos el ticket y lo guardamos en un objeto tipo Ticket
        Ticket ticket = Ticket.builder()
                .total(total)
                .date(new Date())
                .build();

        //Enlazamos el ticket con ticketProduct
        for (TicketProduct ticketp : tp){
            ticketp.setTicket_id(ticket);

        }

        //Guardamos el ticketProduct en ticket
        ticket.setTicketProduct(tp);

        //Guardamos el ticket en la base de datos
        Ticket saved = trepository.saveAndFlush(ticket);

        //Pasamos el objeto creado al mapper para que nos devuelva su DTO
        return mapper.toDTO(saved);
    }

    //Actualizamos un ticket ya que está creado
    @Transactional
    @Override
    public TicketDTO updateTicket(Long id, TicketDTO ticket) {

        //Primero buscamos si existe el ticket en la base de datos
        Ticket t = trepository.findById(id).orElseThrow(()->
                new NotFoundException("Ticket no encontrado, no se puede actualizar"));
        Double total=0.0;
        if(ticket.getTicketProduct() != null){

            List<TicketProduct> list = new ArrayList<>();

            for(TicketProductDTO dto : ticket.getTicketProduct()){

                tprepository.deleteById(t);

                TicketProduct tp = new TicketProduct();

                tp.setTicket_id(t);
                tp.setQuantity(dto.getQuantity());

                Product p = prepository.findById(dto.getProduct_id().getId()).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
                tp.setProduct_id(p);
                total += p.getPrecio() * dto.getQuantity();
                list.add(tp);
            }

            t.setTicketProduct(new ArrayList<>());

            t.getTicketProduct().addAll(list);

        }

        t.setTotal(total);



        Ticket saved  = trepository.save(t);

        return mapper.toDTO(saved);
    }

    //Eliminamos un ticket ya existente
    @Override
    public void deleteTicket(Long id) {

        //Buscamos si existe el producto
        if(!trepository.existsById(id)){

            throw new NotFoundException("Ticket no encontrado, no se puede eliminar");

        }
        //Lo eliminamos
        trepository.deleteById(id);
    }

}
