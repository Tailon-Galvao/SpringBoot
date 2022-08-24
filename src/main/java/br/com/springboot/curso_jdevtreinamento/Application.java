package br.com.springboot.curso_jdevtreinamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication //INjeção de dependência que Inicializa(starta a aplicação web) todo o projeto 
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);// È a linha principal que roda o projeto java spring boot
    }
}
