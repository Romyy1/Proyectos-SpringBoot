package com.romy.ticketing.Controller;

import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.DTO.TicketDTO;
import com.romy.ticketing.Model.Ticket;
import com.romy.ticketing.Model.TicketProduct;
import com.romy.ticketing.Service.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketServiceImpl service;

    @GetMapping("/{id}")
    public Optional<TicketDTO> ticket(@PathVariable Long id){

        return service.findById(id);

    }


    @GetMapping("/list")
    public List<TicketDTO> findAll(){

        return service.findAll();

    }

    @PutMapping("/update/{id}")
    public TicketDTO updateProduct(@PathVariable Long id, @RequestBody TicketDTO ticket){

        return service.updateTicket(id,ticket);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){

        service.deleteTicket(id);

    }

}
