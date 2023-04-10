package com.consultaendereco.controller;

import com.consultaendereco.model.endereco.ConsultaEndereco;
import com.consultaendereco.service.ConsultaEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/consulta-endereco")
@Validated
public class ConsultaEnderecoController {

    @Autowired
    private ConsultaEnderecoService consultaEnderecoService;

    @PostMapping
    public ResponseEntity<Object> consultarendereco(@Valid @RequestBody ConsultaEndereco consultaEndereco){
        return ResponseEntity.ok(consultaEnderecoService.getEnderecoViaCepAPI(consultaEndereco));
    }

}
