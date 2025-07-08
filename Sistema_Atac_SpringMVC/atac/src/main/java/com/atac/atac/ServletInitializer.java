package com.atac.atac;
/*
	Permite empacotar sua aplicação como um .war para rodar em um servidor externo, como:

    Tomcat (standalone), WildFly, GlassFish
	Estende SpringBootServletInitializer, que configura a aplicação quando o Tomcat não
	é embutido, mas sim fornecido externamente.
*/

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AtacApplication.class);
	}
}
