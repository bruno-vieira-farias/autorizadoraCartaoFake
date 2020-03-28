package br.com.fiap.autorizadoraCartaoFake.autorizadoraCartaoFake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutorizadoraCartaoFakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutorizadoraCartaoFakeApplication.class, args);

	}

}
