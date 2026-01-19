package com.romy.ticketing.Service;

import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.Product;
import com.romy.ticketing.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductoRepository repository;

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

            productDTO.get(i).setNombre(p.get(i).getNombre());
            productDTO.get(i).setPrecio(p.get(i).getPrecio());

        }
        return productDTO;

    }

    @Override
    public ProductDTO crearProduct(ProductDTO product) {



        return null;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO product) {
        return null;
    }

    @Override
    public String deleteProduct(Long id) {

        Product pr = repository.findById(id).orElseThrow();

        repository.delete(pr);

        String sol = "El producto con id" + id + " ha sido eliminado";

        return sol;
    }

}
