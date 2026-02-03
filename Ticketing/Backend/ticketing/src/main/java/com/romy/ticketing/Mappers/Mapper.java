package com.romy.ticketing.Mappers;

import com.romy.ticketing.Exceptions.NotFoundException;
import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.DTO.TicketDTO;
import com.romy.ticketing.Model.DTO.TicketProductDTO;
import com.romy.ticketing.Model.Product;
import com.romy.ticketing.Model.Ticket;
import com.romy.ticketing.Model.TicketProduct;
import com.romy.ticketing.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Component
public class Mapper {

    @Autowired
    private ProductRepository productRepository;

    //Mapeo de Product a ProductDTO
    public ProductDTO toDTO(Product p){

        if(p==null) return null;

        return ProductDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .precio(p.getPrecio())
                .build();

    }

    public String toString(Product p) {
        return "Product{id=" + p.getId() + ", nombre='" + p.getNombre() + "', precio=" + p.getPrecio() + "}";
    }

    //Mapeo de Ticket a TicketDTO
    public TicketDTO toDTO(Ticket t){


        List<TicketProductDTO> tpDTO = new ArrayList<>();

        for (TicketProduct ticketprod : t.getTicketProduct()){

            Product p = productRepository.findById(ticketprod.getProduct_id().getId()).orElseThrow(() -> new NotFoundException("Producto no encontrado: " + ticketprod.getProduct_id()));

            System.out.println(toString(p));

            TicketProductDTO ticketP = new TicketProductDTO();

            ticketP.setId(ticketprod.getId());
            ticketP.setProduct_id(p);
            ticketP.setPrice(p.getPrecio());
            ticketP.setQuantity(ticketprod.getQuantity());
            ticketP.setTotal(p.getPrecio() * ticketprod.getQuantity());

            tpDTO.add(ticketP);

        }
      var total=  tpDTO.stream().map(TicketProductDTO::getTotal).reduce(0.0,Double::sum);

      return TicketDTO.builder()
                .date(t.getDate())
                .id(t.getId())
                .ticketProduct(tpDTO)
                .total(total)
                .build();
    }

}
