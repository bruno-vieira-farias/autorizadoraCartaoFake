package br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.service;

import br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.domain.Transacao;
import br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.domain.TransacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kong.unirest.JacksonObjectMapper;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioMassaInicialService {
    private final String URL = "http://localhost:8080/api/transacao/lista";
    private final TransacaoRepository transacaoRepository;

    public EnvioMassaInicialService(TransacaoRepository transacaoRepository) {
        setup();
        this.transacaoRepository = transacaoRepository;
    }

    public void envia() {
        List<Transacao> massaTransacao = transacaoRepository.getMassaTransacaoInicial();

        Unirest.post(URL)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(massaTransacao)
                .asJson();

    }

    private void setup() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        Unirest.config().setObjectMapper(new JacksonObjectMapper(mapper));
    }


}
