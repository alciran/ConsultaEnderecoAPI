package com.consultaendereco.model.endereco;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoViaCep extends ConsultaEndereco {

    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

}