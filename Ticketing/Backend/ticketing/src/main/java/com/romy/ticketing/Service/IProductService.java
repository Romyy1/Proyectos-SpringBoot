package com.romy.ticketing.Service;

import com.romy.ticketing.Model.DTO.ProductDTO;

import java.util.List;

public interface IProductService {

    ProductDTO findById(Long id);

    List<ProductDTO> findAll();

    ProductDTO crearProduct(ProductDTO product);

    ProductDTO updateProduct(Long id,ProductDTO product);

    void deleteProduct(Long id);
}
