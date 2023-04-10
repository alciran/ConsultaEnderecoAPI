package com.consultaendereco.service;

import com.consultaendereco.exception.ExcecaoTratadaParaRespostaHttp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

@Service
@Slf4j
public class ConsumoApiService {

    @Autowired
    private RestTemplate restTemplate;

    public String consumirAPIMetodoGet(String url){
        restTemplate.setErrorHandler(new ResponseResultErrorHandler());
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        ResponseHandler responseHandler = restTemplate.execute(
                url,
                HttpMethod.GET,
                new SettingRequestCallback(),
                response -> {return (new ResponseHandler(response));}
        );
        return responseHandler.getResponseBody();
    }

    private final class ResponseHandler{
        private String responseBody;

        public String getResponseBody(){
            return this.responseBody;
        }

        public ResponseHandler(final ClientHttpResponse response) throws IOException {
            final StringWriter stringWriter = new StringWriter();
            IOUtils.copy(response.getBody(), stringWriter);
            this.responseBody = stringWriter.toString();
        }
    }

    private final class ResponseResultErrorHandler implements ResponseErrorHandler {
        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return response.getRawStatusCode() >= 400;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            log.error("Erro ao consultar CEP: Status code retornado [ " + response.getStatusCode() + " ] => " + response.getBody().toString());
            //Em caso de algum retorno diferente e/ou não tratado pela aplicação, verificar log.
            throw new ExcecaoTratadaParaRespostaHttp(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Não foi possível consultar o endereço."
            );
        }
    }

    private final class SettingRequestCallback implements RequestCallback {

        @Override
        public void doWithRequest(ClientHttpRequest request) throws IOException {
            final HttpHeaders clientHeaders = request.getHeaders();
            clientHeaders.add("Accept", "application/json");
        }

    }
}
