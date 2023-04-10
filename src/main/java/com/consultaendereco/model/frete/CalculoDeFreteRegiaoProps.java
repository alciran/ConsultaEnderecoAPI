package com.consultaendereco.model.frete;

import com.consultaendereco.config.ValorFreteRegiao;
import com.consultaendereco.exception.ExcecaoTratadaParaRespostaHttp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class CalculoDeFreteRegiaoProps implements CalculoDeFretePorUF {

    @Override
    public float getValorFrete(String uf) {
        String valorFrete = EstadosBrasileiros.valueOf(uf).getValorFrete();
        try{
            return Float.parseFloat(valorFrete);
        }catch (NumberFormatException numEx){
            log.error("Não foi possível calcular o frete por estado [" + uf + "] => " + numEx.getMessage());
            throw new ExcecaoTratadaParaRespostaHttp(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Não foi possível consultar o endereço.");
        }
    }

    private enum EstadosBrasileiros {
        AC(ValorFreteRegiao.NORTE),
        AL(ValorFreteRegiao.NORDESTE),
        AM(ValorFreteRegiao.NORTE),
        AP(ValorFreteRegiao.NORTE),
        BA(ValorFreteRegiao.NORDESTE),
        CE(ValorFreteRegiao.NORDESTE),
        DF(ValorFreteRegiao.CENTRO_OESTE),
        ES(ValorFreteRegiao.SUDESTE),
        GO(ValorFreteRegiao.CENTRO_OESTE),
        MA(ValorFreteRegiao.NORDESTE),
        MG(ValorFreteRegiao.SUDESTE),
        MS(ValorFreteRegiao.CENTRO_OESTE),
        MT(ValorFreteRegiao.CENTRO_OESTE),
        PA(ValorFreteRegiao.NORTE),
        PB(ValorFreteRegiao.NORDESTE),
        PE(ValorFreteRegiao.NORDESTE),
        PI(ValorFreteRegiao.NORDESTE),
        PR(ValorFreteRegiao.SUL),
        RJ(ValorFreteRegiao.SUDESTE),
        RN(ValorFreteRegiao.NORDESTE),
        RO(ValorFreteRegiao.NORTE),
        RR(ValorFreteRegiao.NORTE),
        RS(ValorFreteRegiao.SUL),
        SC(ValorFreteRegiao.SUL),
        SE(ValorFreteRegiao.NORDESTE),
        SP(ValorFreteRegiao.SUDESTE),
        TO(ValorFreteRegiao.NORTE);

        private final String valor;

        EstadosBrasileiros(String valor){
            this.valor = valor;
        }

        public String getValorFrete(){
            return this.valor;
        }
    }
}
