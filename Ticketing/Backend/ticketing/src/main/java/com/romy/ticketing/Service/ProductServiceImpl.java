package com.romy.ticketing.Service;

import com.romy.ticketing.Mappers.Mapper;
import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.Product;
import com.romy.ticketing.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public ProductDTO findById(Long id){

        Product p = repository.findById(id).orElseThrow();

        ProductDTO pDTO = new ProductDTO();

        pDTO.setNombre(p.getNombre());
        pDTO.setPrecio(p.getPrecio());

        return pDTO;

    }
    @Override
    public List<ProductDTO> findAll(){

        List<Product> p = repository.findAll();
        List<ProductDTO> productDTO = new ArrayList<>();
        for(int i =0;i<p.size();i++){
            ProductDTO pr = new ProductDTO();
            pr.setNombre(p.get(i).getNombre());
            pr.setPrecio(p.get(i).getPrecio());
            pr.setTicketProduct(p.get(i).getTicketProduct());
            pr.setId(p.get(i).getId());
            productDTO.add(pr);

        }
        return productDTO;

    }

    @Override
    public ProductDTO crearProduct(ProductDTO product) {

        //Creamos el producto y lo guardamos en un objeto tipo Product
        Product prod = Product.builder()
                .nombre(product.getNombre())
                .precio(product.getPrecio())
                .ticketProduct(product.getTicketProduct())
                .build();
        //Para poder retornar el DTO, llamamos al method
        // toDTO() del Mapper y le pasamos como argumento el objeto
        // que acabamos de crear y mientras también lo guardamos
        // en la base de datos
        return Mapper.toDTO(repository.save(prod));
    }

    @Override
    public ProductDTO updateProduct(Long id,ProductDTO product) {

        //Primero buscamos si existe el producto en la base de datos
        Product prod = repository.findById(id).orElseThrow();

        prod.setNombre(product.getNombre());
        prod.setTicketProduct(product.getTicketProduct());
        prod.setPrecio(product.getPrecio());

        return Mapper.toDTO(repository.save(prod));
    }

    @Override
    public String deleteProduct(Long id) {

        Product pr = repository.findById(id).orElseThrow();

        repository.delete(pr);

        String sol = "El producto con id" + id + " ha sido eliminado";

        return sol;
    }

}
