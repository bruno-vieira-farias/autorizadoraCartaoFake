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
    private Random gerador = new Random();
    private List<Transacao> transacoes = new ArrayList<>();

    public List<Transacao> getCargaInicialTransacoes() {
        for (int i = 0; i < 1300; i++) {
            transacoes.add(
                    new Transacao(i,
                    LocalDateTime.now(),
                    geraValorTransacao(),
                    obtemStatusTransacao(i),
                    geraCodigoAutorizacao(),
                    obtemNumeroCartaoParaTransacao())
            );
        }

        return transacoes;
    }

    public Transacao geraTransacaoRandomica(){
        Transacao transacaoRandomica = new Transacao(
                obtemProximoId(),
                LocalDateTime.now(),
                geraValorTransacao(),
                obtemStatusTransacao(obtemProximoId()),
                geraCodigoAutorizacao(),
                obtemNumeroCartaoParaTransacao());

        transacoes.add(transacaoRandomica);
        return transacaoRandomica;
    }

    private Integer obtemProximoId(){
        return transacoes.get(transacoes.size() -1).getId() + 1;
    }

    private BigDecimal geraValorTransacao(){
        return BigDecimal.valueOf(gerador.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private String geraCodigoAutorizacao(){
        return  RandomStringUtils.random(15, true, true);
    }

    private Long obtemNumeroCartaoParaTransacao(){
        return gerador.nextInt(90) + 10 + 4000000000000000L;
    }

    private StatusTransacao obtemStatusTransacao(int index){
        if (index < 1000){
            return  StatusTransacao.AUTORIZADA;
        }

        if (index < 1200){
            return  StatusTransacao.NAO_AUTORIZADA;
        }

        if (index < 1300){
            return  StatusTransacao.CANCELADA;
        }

        if (index % 2 == 0){
            return StatusTransacao.AUTORIZADA;
        }

        return  StatusTransacao.NAO_AUTORIZADA;
    }
}