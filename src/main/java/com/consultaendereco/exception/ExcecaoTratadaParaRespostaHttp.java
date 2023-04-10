package com.consultaendereco.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/* @author Alciran Franco
 * Classe de exceção genérica capturada e tratada automaticamente
 * pela classe ConsultaEnderecoExceptionHandler.
 * Para toda a classe que extender desta, quando lançada como
 * exceção será automaticamente tratada com retorno padrão de erro na
 * resposta HTTP.
 */
@Getter
@AllArgsConstructor
public class ExcecaoTratadaParaRespostaHttp extends RuntimeException {

    private HttpStatus httpStatus;
    private String mensage;

}