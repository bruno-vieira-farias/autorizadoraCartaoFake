package br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class TransacaoRepository {

    public List<Transacao> getCargaInicialTransacoes() {
        return Arrays.asList(
                new Transacao(1, LocalDateTime.now(), BigDecimal.valueOf(10.0), StatusTransacao.AUTORIZADA, "", 4000000000000001L),
                new Transacao(2, LocalDateTime.now(), BigDecimal.valueOf(10.0), StatusTransacao.AUTORIZADA, "", 4000000000000002L),
                new Transacao(3, LocalDateTime.now(), BigDecimal.valueOf(10.0), StatusTransacao.AUTORIZADA, "", 4000000000000003L),
                new Transacao(4, LocalDateTime.now(), BigDecimal.valueOf(10.0), StatusTransacao.AUTORIZADA, "", 4000000000000004L)
        );
    }
}