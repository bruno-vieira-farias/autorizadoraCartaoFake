//package br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.Scheduler;
//
//import br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.service.CargaInicialService;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CargaInicialScheduler {
//    private final CargaInicialService cargaInicialService;
//
//    public CargaInicialScheduler(CargaInicialService cargaInicialService) {
//        this.cargaInicialService = cargaInicialService;
//    }
//
//    @Scheduled(cron = "*/5 * * * * ?")
//    public void execute() {
////        cargaInicialService.enviaCargaInicial();
//    }
//}