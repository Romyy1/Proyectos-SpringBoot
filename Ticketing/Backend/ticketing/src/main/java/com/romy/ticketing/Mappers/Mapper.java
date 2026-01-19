package com.romy.ticketing.Mappers;

import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.DTO.TicketDTO;
import com.romy.ticketing.Model.DTO.TicketProductDTO;
import com.romy.ticketing.Model.Product;
import com.romy.ticketing.Model.Ticket;
import com.romy.ticketing.Model.TicketProduct;

import java.util.stream.Collectors;

public class Mapper {

    //Mapeo de Product a ProductDTO

    public static ProductDTO toDTO(Product p){

        if(p==null) return null;

        return ProductDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .precio(p.getPrecio())
                .build();

    }

    //Mapeo de Ticket a TicketDTO

    public static TicketDTO toDTO(Ticket t){

        if(t==null) return null;

        var tpDTO = t.getTicketProduct().stream().map(td ->
                TicketProductDTO.builder()
                        .id(td.getId())
                        .ticket_id(td.getTicket_id())
                        .product_id(td.getProduct_id())
                        .quantity(td.getQuantity())
                        .price(td.getPrice())
                        .total(td.getPrice() * td.getQuantity())
                        .build()
        ).toList();

        var total = tpDTO.stream()
                .map(TicketProductDTO::getTotal)
                .reduce(0.0, Double::sum);

        return TicketDTO.builder()
                .date(t.getDate())
                .id(t.getId())
                .ticketProduct(tpDTO)
                .total(total)
                .build();



    }

    //Mapeo de TicketProduct a TicketProductDTO

    public static TicketProductDTO toDTO(TicketProduct tp){

        if(tp==null) return null;

        return TicketProductDTO.builder()
                .id(tp.getId())
                .price(tp.getPrice())
                .product_id(tp.getProduct_id())
                .ticket_id(tp.getTicket_id())
                .quantity(tp.getQuantity())
                .build();

    }

}
