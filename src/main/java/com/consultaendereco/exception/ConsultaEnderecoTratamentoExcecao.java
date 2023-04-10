package com.consultaendereco.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.consultaendereco.model.RespostaRetornoPadrao;

@ControllerAdvice
public class ConsultaEnderecoTratamentoExcecao extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        RespostaRetornoPadrao respostaRetornoPadrao = new RespostaRetornoPadrao("Atributo invalido: " + ex.getMessage());
        return handleExceptionInternal(ex, respostaRetornoPadrao, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> message = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError ->  message.add(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()).toString()));

        RespostaRetornoPadrao respostaRetornoPadrao = new RespostaRetornoPadrao(message.toString());
        return handleExceptionInternal(ex, respostaRetornoPadrao, headers, HttpStatus.BAD_REQUEST, request);
    }


    // Tratamento para exceções lançadas na aplicação para retorno na resposta Http
    @ExceptionHandler({ ExcecaoTratadaParaRespostaHttp.class })
    public ResponseEntity<Object> handleGenericLoadException(ExcecaoTratadaParaRespostaHttp ex, WebRequest request) {
        return handleExceptionInternal(ex, new RespostaRetornoPadrao(ex.getMensage()), new HttpHeaders(), ex.getHttpStatus(), request);
    }
}