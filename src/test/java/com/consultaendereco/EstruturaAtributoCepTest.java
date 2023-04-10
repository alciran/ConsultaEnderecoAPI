package com.consultaendereco;

import com.consultaendereco.model.endereco.ConsultaEndereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstruturaAtributoCepTest {

    private final ConsultaEndereco consultaEndereco = new ConsultaEndereco();

    @Test
    public void cepMinimoCaracteresTeste(){
        consultaEndereco.setCep("840000");
        assertFalse(consultaEndereco.cepEhValido());
    }

    @Test
    public void cepMaximoCaracteresTeste(){
        consultaEndereco.setCep("840000000");
        assertFalse(consultaEndereco.cepEhValido());
    }

    @Test
    public void cepSemMascaraTeste(){
        consultaEndereco.setCep("01001000");
        assertTrue(consultaEndereco.cepEhValido());
    }

    @Test
    public void cepComMascaraTeste(){
        consultaEndereco.setCep("01001-000");
        assertTrue(consultaEndereco.cepEhValido());
    }

    @Test
    public void cepComLetrasTeste(){
        consultaEndereco.setCep("01abc-000");
        assertFalse(consultaEndereco.cepEhValido());
    }

    @Test
    public void cepComCaracteresEspeciaisTeste(){
        consultaEndereco.setCep("01@#$-!00");
        assertFalse(consultaEndereco.cepEhValido());
    }

    @Test
    public void cepComESemMascaraConsultaEnderecoTeste(){
        consultaEndereco.setCep("01001000");
        assertEquals("01001000", consultaEndereco.getCepSemMascara());
        assertEquals("01001-000", consultaEndereco.getCep());
    }
}
