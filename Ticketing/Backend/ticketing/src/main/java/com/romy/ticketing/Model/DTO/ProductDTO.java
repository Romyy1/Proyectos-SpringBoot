package com.romy.ticketing.Model.DTO;

import lombok.*;

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
