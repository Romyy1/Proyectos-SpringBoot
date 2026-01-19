package com.romy.ticketing.Service;

import com.romy.ticketing.Model.DTO.ProductDTO;
import com.romy.ticketing.Model.DTO.TicketProductDTO;
import com.romy.ticketing.Model.TicketProduct;
import com.romy.ticketing.Repository.TicketProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TicketProductServiceImpl implements ITicketProductService{

    @Autowired
    private TicketProductRepository repository;

    public List<TicketProductDTO> buscarPorId(Long id) {

        List<TicketProductDTO> tp = repository.buscarPorId(id);

        List<TicketProductDTO> tpDTO = new ArrayList<>();

        for(int i =0;i<tp.size();i++){

            tpDTO.get(i).setTicket_id(tp.get(i).getTicket_id());
            tpDTO.get(i).setPrice(tp.get(i).getPrice());
            tpDTO.get(i).setQuantity(tp.get(i).getQuantity());
            tpDTO.get(i).setProduct_id(tp.get(i).getProduct_id());
        }

        return tpDTO;
    }

    @Override
    public List<TicketProductDTO> findAll() {
        return List.of();
    }

    @Override
    public TicketProductDTO crearTicketProduct(TicketProductDTO tp) {
        return null;
    }

    @Override
    public TicketProductDTO updateTicketProduct(TicketProductDTO tp) {
        return null;
    }

    @Override
    public String deleteTicketProduct(Long id) {
        return "";
    }
}
