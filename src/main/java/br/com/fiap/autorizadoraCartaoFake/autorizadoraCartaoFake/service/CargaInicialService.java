package br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.service;

import br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.domain.Transacao;
import br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.domain.TransacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kong.unirest.HttpResponse;
import kong.unirest.JacksonObjectMapper;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargaInicialService {
    private final String URL = "http://localhost:8080/api/transacao/lista";
    private final TransacaoRepository transacaoRepository;
    private boolean jaEnviadoComSucesso;

    public CargaInicialService(TransacaoRepository transacaoRepository) {
        setup();
        this.transacaoRepository = transacaoRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void enviaCargaInicial() {
        List<Transacao> cargaInicialTransacoes = transacaoRepository.getCargaInicialTransacoes();

        while(!jaEnviadoComSucesso){
           try{
               HttpResponse<JsonNode> httpResponse = Unirest.post(URL)
                       .header("Content-Type", "application/json")
                       .header("accept", "application/json")
                       .body(cargaInicialTransacoes)
                       .asJson();

               jaEnviadoComSucesso = httpResponse.isSuccess();
               Thread.sleep(3000);
           }catch (Exception e){
               System.out.println(e.getMessage());
           }
        }
    }

    private void setup() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        Unirest.config().setObjectMapper(new JacksonObjectMapper(mapper));
    }

}