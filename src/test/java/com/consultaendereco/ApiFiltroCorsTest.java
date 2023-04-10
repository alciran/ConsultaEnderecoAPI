package com.consultaendereco;

import com.consultaendereco.annotation.EnabledIfDevProfile;
import com.consultaendereco.annotation.EnabledIfProdProfile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class ApiFiltroCorsTest {

    @Value("${allow-origin}")
    private String allowOrigin;

    @Autowired
    private MockMvc mockMvc;

    private ResultActions resultActions;

    public void setResultActions() throws Exception {
        this.resultActions = this.mockMvc.perform(get("/testeApiCors"));
    }

    @Test
    @DisplayName("Configuração de CORS")
    @EnabledIfProdProfile
    public void verificarFiltroCORSHeader() throws Exception{
        this.setResultActions();
        this.resultActions.andExpect(MockMvcResultMatchers.header().stringValues("Access-Control-Allow-Origin",
                allowOrigin));
    }

    @Test
    @DisplayName("Configuração de CORS")
    @EnabledIfDevProfile
    public void verificarFiltroCORSHeaderDev() throws Exception{
        this.setResultActions();
        //Permite all '*' para ambiente de dev.
        this.resultActions.andExpect(MockMvcResultMatchers.header().stringValues("Access-Control-Allow-Origin",
                "*"));
    }

    @RestController
    @RequestMapping(path = "testeApiCors")
    private class TestController {
        @GetMapping
        public String getMethodTest() {
            String outPut = "========== RestController with GET Method for " + this.getClass().getSimpleName()
                    + " Successfully load ==========";
            log.info(outPut);
            return outPut;
        }
    }


}
