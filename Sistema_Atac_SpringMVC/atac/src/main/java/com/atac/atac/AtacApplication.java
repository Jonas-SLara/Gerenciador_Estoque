package com.atac.atac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	É o ponto de entrada da aplicação Spring Boot.
	Usa a anotação @SpringBootApplication, que faz três coisas:
	
	@Configuration → permite definir beans
	@EnableAutoConfiguration → ativa a configuração automática (como o Tomcat embutido)
	@ComponentScan → ativa o escaneamento de componentes no pacote atual e subpacotes
 */

@SpringBootApplication
public class AtacApplication {

	/*
		SpringApplication.run(...)
		Inicia a aplicação Spring Boot; Carrega o contexto do Spring
		Sobe o Tomcat embutido (caso seja jar); Dispara o ciclo de vida da aplicação
	 */
	public static void main(String[] args) {
		SpringApplication.run(AtacApplication.class, args);
	}

}
