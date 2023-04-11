package com.consultaendereco.model.endereco;

import com.consultaendereco.model.frete.CalculoDeFretePorUF;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Endereco")
public class Endereco{

    @ApiModelProperty(name = "Cep", position = 0, example = "01001-000")
    private String cep;

    @ApiModelProperty(name = "Rua", position = 1, example = "Praça da Sé")
    private String rua ;

    @ApiModelProperty(name = "Complemento", position = 2, example = "lado ímpar")
    private String complemento;

    @ApiModelProperty(name = "Bairro", position = 3, example = "Sé")
    private String bairro;

    @ApiModelProperty(name = "cidade", position = 4, example = "São Paulo")
    private String cidade;

    @ApiModelProperty(name = "Estado", position = 5, example = "SP")
    private String estado;

    @ApiModelProperty(name = "Frete", position = 6, example = "99.99")
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

    @JsonIgnore
    public String getCepSemMascara(){
        return this.cep.replace("-", "");
    }

    public String getCep(){
        if(this.cep.contains("-"))
            return this.cep;
        else{
            String cepComMascara = this.cep.substring(0, 5) + "-" + this.cep.substring(5);
            return cepComMascara;
        }
    }

}
