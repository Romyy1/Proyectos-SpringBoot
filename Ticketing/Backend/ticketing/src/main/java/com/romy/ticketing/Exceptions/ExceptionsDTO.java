package com.romy.ticketing.Exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExceptionsDTO {

     private String message;

     private String error;

     private int status;

     private Date fecha;
}
