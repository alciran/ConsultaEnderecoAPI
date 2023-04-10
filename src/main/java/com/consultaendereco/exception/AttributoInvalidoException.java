package com.consultaendereco.exception;

import org.springframework.http.HttpStatus;

public class AttributoInvalidoException extends ExcecaoTratadaParaRespostaHttp{
    public AttributoInvalidoException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
