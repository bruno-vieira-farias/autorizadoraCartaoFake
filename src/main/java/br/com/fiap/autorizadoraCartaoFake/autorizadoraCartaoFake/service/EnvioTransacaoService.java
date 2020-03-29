package br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.service;

import br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.domain.Transacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kong.unirest.HttpResponse;
import kong.unirest.JacksonObjectMapper;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioTransacaoService {
    private final String URL = "http://localhost:8080/api/transacao";

    public EnvioTransacaoService() {
        setup();
    }

    public boolean enviaTransacao(List<Transacao> transacoes) {
        HttpResponse<JsonNode> httpResponse = Unirest.post(URL.concat("/lista"))
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(transacoes)
                .asJson();

        return httpResponse.isSuccess();
    }

    public boolean enviaTransacao(Transacao transacao) {
        HttpResponse<JsonNode> httpResponse = Unirest.post(URL)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(transacao)
                .asJson();

        return httpResponse.isSuccess();
    }

    private void setup() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        Unirest.config().setObjectMapper(new JacksonObjectMapper(mapper));
    }

}
