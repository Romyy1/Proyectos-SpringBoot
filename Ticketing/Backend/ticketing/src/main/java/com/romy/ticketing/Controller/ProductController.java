package com.romy.ticketing.Controller;

import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl service;

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id){

        return service.findById(id);

    }

    @GetMapping("/list")
    public List<ProductDTO> findAll(){

        return service.findAll();

    }

}
