package com.consultaendereco.service;

import com.consultaendereco.exception.AttributoInvalidoException;
import com.consultaendereco.exception.ExcecaoTratadaParaRespostaHttp;
import com.consultaendereco.exception.RecursoNaoEncontrado;
import com.consultaendereco.model.ResultadoComErroViaCep;
import com.consultaendereco.model.endereco.ConsultaEndereco;
import com.consultaendereco.model.endereco.Endereco;
import com.consultaendereco.model.endereco.EnderecoViaCep;
import com.consultaendereco.model.frete.CalculoDeFreteRegiaoProps;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsultaEnderecoService {

    @Autowired
    private ConsumoApiService consumoApiServicen;

    @Value("${api.viacep.url}")
    private String apiViaCepURL;


    public Object getEnderecoViaCepAPI(ConsultaEndereco consultaEndereco){
        if(consultaEndereco.cepEhValido()){
            String objetoJsonString = consumoApiServicen.consumirAPIMetodoGet(apiViaCepURL + consultaEndereco.getCep() + "/json");
            try{
                return tratarObjetoRetornado(objetoJsonString, consultaEndereco.getCep());
            }catch (IOException ioEx){
                throw new ExcecaoTratadaParaRespostaHttp(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Erro ao tentar buscar endereço para o Cep [ " + consultaEndereco.getCep() + " ] => " + ioEx.getMessage()
                );
            }
        } else{
            throw new AttributoInvalidoException("Cep [ " + consultaEndereco.getCep() + " ] inválido para consulta!" );
        }
    }

    private Object tratarObjetoRetornado(String objetoJsonString, String cep) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        ResultadoComErroViaCep resultErrorViaCep = objectMapper.readValue(objetoJsonString, ResultadoComErroViaCep.class);
        if(resultErrorViaCep.isErro())
            throw new RecursoNaoEncontrado("Nenhum endereço encontrado para o CEP [ " + cep + " ]");
        else{
            EnderecoViaCep enderecoViaCep = objectMapper.readValue(objetoJsonString, EnderecoViaCep.class);
            return new Endereco(enderecoViaCep, new CalculoDeFreteRegiaoProps());
        }
    }

}
