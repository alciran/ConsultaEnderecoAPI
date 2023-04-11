package com.consultaendereco.steps;

import java.util.List;
import java.util.Map;

import com.consultaendereco.CucumberBootStrap;
import com.consultaendereco.model.endereco.ConsultaEndereco;
import com.consultaendereco.model.endereco.Endereco;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ConsultarEnderecoSteps extends CucumberBootStrap {
    
    private final ConsultaEndereco consultaEnderecoTest = new ConsultaEndereco();    
    private Endereco enderecoRetornado;

    @Given("^o determinado cep")
    public void consultaEnderecoCepSemMascara(List<Map<String, String>> mapCep){
        consultaEnderecoTest.setCep(mapCep.get(0).get("cep"));
    }

    @When("^cep (.+) é passado para buscar endereço")
    public void getEnderecoCepSemMascara(String cep){
        enderecoRetornado = testRestTemplate.postForObject("/consulta-endereco/", consultaEnderecoTest, Endereco.class);
        assertThat(enderecoRetornado, is(notNullValue()));
        assertThat(enderecoRetornado.getCep(), is(equalTo(consultaEnderecoTest.getCep())));
        assertThat(enderecoRetornado.getCepSemMascara(), is(equalTo(consultaEnderecoTest.getCepSemMascara())));
    }

    @Then("^o endereço com cep, estado e frete é retornado")
    public void enderecoRetornadoCepSemMascara(List<Map<String, String>> mapEndereco){        
        Endereco enderecoFeature = new Endereco();
        enderecoFeature.setCep(mapEndereco.get(0).get("cep"));
        enderecoFeature.setEstado(mapEndereco.get(0).get("estado"));
        enderecoFeature.setFrete(Float.parseFloat(mapEndereco.get(0).get("frete")));    
        enderecoFeature.setCidade(mapEndereco.get(0).get("cidade"));
        
        assertThat(enderecoRetornado.getCep(), is(equalTo(enderecoFeature.getCep())));
        assertThat(enderecoRetornado.getEstado(), is(equalTo(enderecoFeature.getEstado()))); 
        assertThat(enderecoRetornado.getFrete(), is(equalTo(enderecoFeature.getFrete())));    
        assertThat(enderecoRetornado.getCidade(), is(equalTo(enderecoFeature.getCidade())));  
    }

}
