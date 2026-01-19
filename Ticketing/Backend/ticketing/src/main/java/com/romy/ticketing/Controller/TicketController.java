package com.romy.ticketing.Controller;

import com.romy.ticketing.Model.DTO.TicketDTO;
import com.romy.ticketing.Model.TicketProduct;
import com.romy.ticketing.Service.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
