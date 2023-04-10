package com.consultaendereco.model.endereco;

import com.consultaendereco.model.frete.CalculoDeFretePorUF;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco extends ConsultaEndereco{

    private String rua ;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private float frete;

    public Endereco(EnderecoViaCep enderecoViaCep, CalculoDeFretePorUF calculoFretePorUF){
        this.setCep(enderecoViaCep.getCep());
        this.setRua(enderecoViaCep.getLogradouro());
        this.setComplemento(enderecoViaCep.getComplemento());
        this.setBairro(enderecoViaCep.getBairro());
        this.setCidade(enderecoViaCep.getLocalidade());
        this.setEstado(enderecoViaCep.getUf());
        this.setFrete(calculoFretePorUF.getValorFrete(this.getEstado()));
    }

}
