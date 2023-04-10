package com.consultaendereco.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alciran Franco
 * Classe para configuração com valores de frete fixos para cada região.
 */
@Configuration
public class ValorFreteRegiao {

    public static String CENTRO_OESTE;
    public static String NORDESTE;
    public static String NORTE;
    public static String SUDESTE;
    public static String SUL;

    @Value("${regiao.centroOeste}")
    private void setCentroOeste(String centroOeste){
        ValorFreteRegiao.CENTRO_OESTE = centroOeste;
    }

    @Value("${regiao.nordeste}")
    private void setNordeste(String nordeste){
        ValorFreteRegiao.NORDESTE = nordeste;
    }

    @Value("${regiao.norte}")
    private void setNorte(String norte){
        ValorFreteRegiao.NORTE = norte;
    }

    @Value("${regiao.sudeste}")
    private void setSudeste(String sudeste){
        ValorFreteRegiao.SUDESTE = sudeste;
    }

    @Value("${regiao.sul}")
    private void setSul(String sul){
        ValorFreteRegiao.SUL = sul;
    }
}
