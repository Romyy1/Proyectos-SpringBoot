package com.romy.ticketing.Controller;

import com.romy.ticketing.Mappers.Mapper;
import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.Product;
import com.romy.ticketing.Service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl service;
    @Autowired
    private Mapper mapper;

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id){

        return service.findById(id);

    }

    @PostMapping("/create")
    public ProductDTO createProduct(@RequestBody Product p){

        return service.crearProduct(mapper.toDTO(p));

    }

    @GetMapping("/list")
    public List<ProductDTO> findAll(){

        return service.findAll();

    }

    @PutMapping("/update/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody Product product){

        return service.updateProduct(id,mapper.toDTO(product));

    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){

        service.deleteProduct(id);

    }


}
