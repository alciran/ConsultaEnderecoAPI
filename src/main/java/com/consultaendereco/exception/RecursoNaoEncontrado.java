package com.consultaendereco.exception;

import org.springframework.http.HttpStatus;

public class RecursoNaoEncontrado extends ExcecaoTratadaParaRespostaHttp {

    public RecursoNaoEncontrado(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
