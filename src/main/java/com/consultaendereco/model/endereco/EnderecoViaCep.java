package com.consultaendereco.model.endereco;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoViaCep extends ConsultaEndereco {

    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

}