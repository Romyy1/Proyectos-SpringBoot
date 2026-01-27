package com.romy.ticketing.Controller;

import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO product){

        return service.updateProduct(id,product);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){

        service.deleteProduct(id);

    }


}
