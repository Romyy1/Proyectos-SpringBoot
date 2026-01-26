package com.romy.ticketing.Exceptions;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotFoundException extends RuntimeException{

     public NotFoundException(String msje){

          super(msje);

     }

}
