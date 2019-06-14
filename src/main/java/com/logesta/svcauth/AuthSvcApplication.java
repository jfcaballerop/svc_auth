package com.logesta.svcauth;

import com.logesta.svcauth.model.Usuario;
import com.logesta.svcauth.repository.UsuarioRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AuthSvcApplication {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthSvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UsuarioRespository repo) {
		return args -> {

			Usuario usuario = new Usuario("test1",bCryptPasswordEncoder.encode("test"));
			repo.save(usuario);
			Usuario usuario2 = new Usuario("test2", bCryptPasswordEncoder.encode("test"));
			repo.save(usuario2);

		};
	}

}
