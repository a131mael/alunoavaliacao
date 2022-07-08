package br.com.api.alunoavaliacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan
@EnableScheduling
@EntityScan(basePackages = {"org.escola.model"})
public class AlunoavaliacaoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AlunoavaliacaoApplication.class, args);
	}

}
