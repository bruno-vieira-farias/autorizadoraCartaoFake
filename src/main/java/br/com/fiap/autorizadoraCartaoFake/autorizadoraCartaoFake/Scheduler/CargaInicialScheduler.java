package br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.Scheduler;

import br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.service.TransacaoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CargaInicialScheduler {
    private final TransacaoService transacaoService;

    public CargaInicialScheduler(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @Scheduled(cron = "*/5 * * * * ?")
    public void execute() {
        transacaoService.enviaTransacaoRandomica();
    }
}