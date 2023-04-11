package com.consultaendereco.steps;

import java.util.List;
import java.util.Map;

import com.consultaendereco.CucumberBootStrap;
import com.consultaendereco.model.RespostaRetornoPadrao;
import com.consultaendereco.model.endereco.ConsultaEndereco;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class RespostaRetornoSteps extends CucumberBootStrap {

    private final ConsultaEndereco consultaEnderecoTest = new ConsultaEndereco(); 
    private RespostaRetornoPadrao respostaRetornoPadrao;

    @Given("^o determinado cep inexistente")
    public void consultaEnderecoCepSemMascara(List<Map<String, String>> mapCep){
        consultaEnderecoTest.setCep(mapCep.get(0).get("cep"));
        respostaRetornoPadrao = testRestTemplate.postForObject("/consulta-endereco/", consultaEnderecoTest, RespostaRetornoPadrao.class);
    }

    @Then("^a mensagem de cep não encontrado deve ser retornada")
    public void mensagemCepNaoEmcontrado(List<Map<String, String>> mapMensagem){
        String mensagem = mapMensagem.get(0).get("mensagem");        
        assertThat(respostaRetornoPadrao.getMensagem(), is(equalTo(mensagem)));
    }


    
}
