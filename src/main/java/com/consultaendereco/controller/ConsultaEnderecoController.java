package com.consultaendereco.controller;

import com.consultaendereco.model.RespostaRetornoPadrao;
import com.consultaendereco.model.endereco.ConsultaEndereco;
import com.consultaendereco.model.endereco.Endereco;
import com.consultaendereco.service.ConsultaEnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "consulta-endereco")
@RequestMapping("/consulta-endereco")
@Validated
public class ConsultaEnderecoController {

    @Autowired
    private ConsultaEnderecoService consultaEnderecoService;

    @ApiOperation(value = "Consultar endereço")
    @PostMapping(produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, response = Endereco.class, message="Endereço retornado com sucesso!"),
            @ApiResponse(code = 400, response = RespostaRetornoPadrao.class, message="Attributo Cep inválido!")
    })
    public ResponseEntity<Object> consultarendereco(@Valid @RequestBody ConsultaEndereco consultaEndereco){
        return ResponseEntity.ok(consultaEnderecoService.getEnderecoViaCepAPI(consultaEndereco));
    }

}
