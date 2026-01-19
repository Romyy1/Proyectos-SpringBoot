package com.romy.ticketing.Service;

import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.Product;
import com.romy.ticketing.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IProductService {

    ProductDTO findById(Long id);

    List<ProductDTO> findAll();

    ProductDTO crearProduct(ProductDTO product);

    ProductDTO updateProduct(ProductDTO product);

    String deleteProduct(Long id);
}
