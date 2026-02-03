package com.romy.ticketing.Exceptions;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException{


     public NotFoundException(String msje){

          super(msje);

     }

}
