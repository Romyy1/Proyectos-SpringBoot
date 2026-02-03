package com.romy.ticketing.Model.DTO;

import com.romy.ticketing.Model.TicketProduct;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {

    Long id;
    String nombre;
    Double precio;

}
