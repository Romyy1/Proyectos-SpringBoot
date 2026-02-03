package com.romy.ticketing.Service;

import com.romy.ticketing.Exceptions.NotFoundException;
import com.romy.ticketing.Mappers.Mapper;
import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.Product;
import com.romy.ticketing.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private Mapper mapper;

    @Override
    public ProductDTO findById(Long id){

        Product p = repository.findById(id).orElseThrow(()->
                new NotFoundException("Producto no encontrado"));

        return mapper.toDTO(p);

    }
    @Override
    public List<ProductDTO> findAll(){

        return repository.findAll().stream().map(mapper::toDTO).toList();

    }

    @Override
    public ProductDTO crearProduct(ProductDTO product) {

        //Creamos el producto y lo guardamos en un objeto tipo Product
        Product prod = Product.builder()
                .nombre(product.getNombre())
                .precio(product.getPrecio())
                .build();
        //Para poder retornar el DTO, llamamos al method
        // toDTO() del Mapper y le pasamos como argumento el objeto
        // que acabamos de crear y mientras también lo guardamos
        // en la base de datos
        Product saved = repository.save(prod);

        return mapper.toDTO(saved);
    }

    @Override
    public ProductDTO updateProduct(Long id,ProductDTO product) {

        //Primero buscamos si existe el producto en la base de datos
        Product prod = repository.findById(id).orElseThrow(()->
                new NotFoundException("Producto no encontrado, no se puede actualizar"));

        prod.setNombre(product.getNombre());
        prod.setPrecio(product.getPrecio());

        return mapper.toDTO(repository.save(prod));
    }

    @Override
    public void deleteProduct(Long id) {
        //Buscamos si existe el producto
        if(!repository.existsById(id)){

            throw new NotFoundException("Producto no encontrado, no se puede eliminar");

        }
        //Lo eliminamos
        repository.deleteById(id);

    }

}
