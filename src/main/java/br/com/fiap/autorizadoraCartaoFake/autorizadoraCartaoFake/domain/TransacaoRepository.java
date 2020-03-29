package br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake.domain;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TransacaoRepository {

    public List<Transacao> getCargaInicialTransacoes() {
        List<Transacao> transacoes = new ArrayList<>();
        Random gerador = new Random();

        for (int i = 0; i < 1000; i++) {
            transacoes.add(new Transacao(
                    i,
                    LocalDateTime.now(),
                    BigDecimal.valueOf(gerador.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP),
                    StatusTransacao.AUTORIZADA,
                    RandomStringUtils.random(15, true, true),
                    gerador.nextInt(90) + 10 + 4000000000000000L)
            );
        }

        for (int i = 1000; i < 1200; i++) {
            transacoes.add(new Transacao(
                    i,
                    LocalDateTime.now(),
                    BigDecimal.valueOf(gerador.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP),
                    StatusTransacao.NAO_AUTORIZADA,
                    RandomStringUtils.random(15, true, true),
                    gerador.nextInt(90) + 10 + 4000000000000000L)
            );
        }

        for (int i = 1200; i < 1300; i++) {
            transacoes.add(new Transacao(
                    i,
                    LocalDateTime.now(),
                    BigDecimal.valueOf(gerador.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP),
                    StatusTransacao.CANCELADA,
                    RandomStringUtils.random(15, true, true),
                    gerador.nextInt(90) + 10 + 4000000000000000L)
            );
        }

        return transacoes;
    }
}