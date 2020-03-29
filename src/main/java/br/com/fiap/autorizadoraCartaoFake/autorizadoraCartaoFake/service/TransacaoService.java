package br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.service;

import br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.domain.Transacao;
import br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.domain.TransacaoRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {
    private final String URL = "http://localhost:8080/api/transacao/lista";
    private final TransacaoRepository transacaoRepository;
    private final EnvioTransacaoService envioTransacaoService;
    private boolean isCargaInicialRealizada;

    public TransacaoService(TransacaoRepository transacaoRepository, EnvioTransacaoService envioTransacaoService) {
        this.transacaoRepository = transacaoRepository;
        this.envioTransacaoService = envioTransacaoService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void enviaCargaInicial() {
        List<Transacao> transacoesCargaInicial = transacaoRepository.getCargaInicialTransacoes();

        while (!isCargaInicialRealizada) {
            try {
                isCargaInicialRealizada = envioTransacaoService.enviaTransacao(transacoesCargaInicial);
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void enviaTransacaoRandomica() {
        if (!isCargaInicialRealizada) {
            return;
        }

        try {
            Transacao transacaoRandomica = transacaoRepository.getTransacaoRandomica();
            envioTransacaoService.enviaTransacao(transacaoRandomica);
        } catch (Exception e) {
            System.out.println("Abafa o caso.");
        }

    }

}