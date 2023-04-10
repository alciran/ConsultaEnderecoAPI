package com.consultaendereco;

import com.consultaendereco.model.frete.CalculoDeFretePorUF;
import com.consultaendereco.model.frete.CalculoDeFreteRegiaoProps;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculoFreteEAPIPropsTest {

    @Value("${regiao.centroOeste}")
    private String centroOeste;

    @Value("${regiao.nordeste}")
    private String nordeste;

    @Value("${regiao.norte}")
    private String norte;

    @Value("${regiao.sudeste}")
    private String sudeste;

    @Value("${regiao.sul}")
    private String sul;

    @Value("${api.viacep.url}")
    private String apiViaCepURL;

    private CalculoDeFretePorUF calculoFretePorUF = new CalculoDeFreteRegiaoProps();

    private boolean validarValorTipoFloat(String valor){
        try {
            Float.parseFloat(valor);
            return true;
        }catch (NumberFormatException numEx){
            return false;
        }
    }

    @Test
    @Order(1)
    public void valorPropRegiaoCentroOesteTeste(){
        assertTrue(!centroOeste.isEmpty());
        assertTrue(validarValorTipoFloat(centroOeste));
    }

    @Test
    @Order(2)
    public void valorPropRegiaoNordesteTeste(){
        assertTrue(!nordeste.isEmpty());
        assertTrue(validarValorTipoFloat(nordeste));
    }

    @Test
    @Order(3)
    public void valorPropRegiaoNorteTeste(){
        assertTrue(!norte.isEmpty());
        assertTrue(validarValorTipoFloat(norte));
    }

    @Test
    @Order(4)
    public void valorPropRegiaoSudesteTeste(){
        assertTrue(!sudeste.isEmpty());
        assertTrue(validarValorTipoFloat(sudeste));
    }

    @Test
    @Order(5)
    public void valorPropFreteRegiaoSulTeste(){
        assertTrue(!sul.isEmpty());
        assertTrue(validarValorTipoFloat(sul));
    }


    @Test
    public void valorFreteRegiaoCentroOestePorEstados(){
        assertEquals(Float.parseFloat(centroOeste), calculoFretePorUF.getValorFrete("DF"));
        assertEquals(Float.parseFloat(centroOeste), calculoFretePorUF.getValorFrete("GO"));
        assertEquals(Float.parseFloat(centroOeste), calculoFretePorUF.getValorFrete("MS"));
        assertEquals(Float.parseFloat(centroOeste), calculoFretePorUF.getValorFrete("MT"));
    }

    @Test
    public void valorFreteRegiaoNordestePorEstados(){
        assertEquals(Float.parseFloat(nordeste), calculoFretePorUF.getValorFrete("AL"));
        assertEquals(Float.parseFloat(nordeste), calculoFretePorUF.getValorFrete("BA"));
        assertEquals(Float.parseFloat(nordeste), calculoFretePorUF.getValorFrete("CE"));
        assertEquals(Float.parseFloat(nordeste), calculoFretePorUF.getValorFrete("MA"));
        assertEquals(Float.parseFloat(nordeste), calculoFretePorUF.getValorFrete("PB"));
        assertEquals(Float.parseFloat(nordeste), calculoFretePorUF.getValorFrete("PE"));
        assertEquals(Float.parseFloat(nordeste), calculoFretePorUF.getValorFrete("PI"));
        assertEquals(Float.parseFloat(nordeste), calculoFretePorUF.getValorFrete("RN"));
        assertEquals(Float.parseFloat(nordeste), calculoFretePorUF.getValorFrete("SE"));
    }

    @Test
    public void valorFreteRegiaoNortePorEstados(){
        assertEquals(Float.parseFloat(norte), calculoFretePorUF.getValorFrete("AC"));
        assertEquals(Float.parseFloat(norte), calculoFretePorUF.getValorFrete("AM"));
        assertEquals(Float.parseFloat(norte), calculoFretePorUF.getValorFrete("AP"));
        assertEquals(Float.parseFloat(norte), calculoFretePorUF.getValorFrete("PA"));
        assertEquals(Float.parseFloat(norte), calculoFretePorUF.getValorFrete("RO"));
        assertEquals(Float.parseFloat(norte), calculoFretePorUF.getValorFrete("RR"));
        assertEquals(Float.parseFloat(norte), calculoFretePorUF.getValorFrete("TO"));
    }

    @Test
    public void valorFreteRegiaoSudestePorEstados(){
        assertEquals(Float.parseFloat(sudeste), calculoFretePorUF.getValorFrete("ES"));
        assertEquals(Float.parseFloat(sudeste), calculoFretePorUF.getValorFrete("MG"));
        assertEquals(Float.parseFloat(sudeste), calculoFretePorUF.getValorFrete("SP"));
        assertEquals(Float.parseFloat(sudeste), calculoFretePorUF.getValorFrete("RJ"));
    }

    @Test
    public void valorFreteRegiaoSulPorEstados(){
        assertEquals(Float.parseFloat(sul), calculoFretePorUF.getValorFrete("PR"));
        assertEquals(Float.parseFloat(sul), calculoFretePorUF.getValorFrete("SC"));
        assertEquals(Float.parseFloat(sul), calculoFretePorUF.getValorFrete("RS"));
    }

    @Test
    public void apiViaCepURlPropriedadeTeste(){
        assertTrue(!apiViaCepURL.isEmpty());
    }

    @Test
    public void apiViaCepURLStructure() throws URISyntaxException {
        URI uri = new URI(apiViaCepURL);
        assertNotNull(uri.getHost());
    }

    @Test
    public void conaxaoApiViaCepURl() throws MalformedURLException, IOException {
        HttpURLConnection connection = (HttpURLConnection) (new URL(apiViaCepURL)).openConnection();
        assertNotNull(connection.getResponseCode());
        connection.disconnect();
    }

}
