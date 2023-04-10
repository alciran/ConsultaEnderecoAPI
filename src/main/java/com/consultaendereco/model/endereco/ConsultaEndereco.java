package com.consultaendereco.model.endereco;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ConsultaEndereco {

    @NotNull(message = "Atributo [ cep ] não pode ser nulo!")
    @NotEmpty(message = "Atributo [ cep ] não pode ser vazio!")
    @Size(min=8, max=9, message = "Tamanho atributo [ cep ] deve ser entre 8 caracteres ou 9 caracteres com máscara!")
    private String cep;

    public boolean cepEhValido(){
        if(this.cep == null || this.cep.isEmpty())
            return false;

        boolean possuiApenasNumeros = this.getCepSemMascara().matches("^\\d+$");
        return (possuiApenasNumeros && this.getCepSemMascara().length() == 8);
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
