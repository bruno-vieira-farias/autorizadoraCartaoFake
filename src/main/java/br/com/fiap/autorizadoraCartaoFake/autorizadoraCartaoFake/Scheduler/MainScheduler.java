package br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.Scheduler;

import br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.service.EnvioMassaInicialService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MainScheduler {
    private final EnvioMassaInicialService envioMassaInicialService;

    public MainScheduler(EnvioMassaInicialService envioMassaInicialService) {
        this.envioMassaInicialService = envioMassaInicialService;
    }

    @Scheduled(cron = "*/2 * * * * ?")
    public void execute() {
        envioMassaInicialService.envia();
    }
}