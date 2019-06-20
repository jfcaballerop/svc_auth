package com.mrknight.svcauth;

import com.mrknight.svcauth.model.Usuario;
import com.mrknight.svcauth.repository.UsuarioRespository;

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

			Usuario usuario1 = new Usuario("usuario1", bCryptPasswordEncoder.encode("pass1"));
			repo.save(usuario1);
			Usuario usuario2 = new Usuario("usuario2", bCryptPasswordEncoder.encode("pass2"));
			repo.save(usuario2);
			Usuario usuario3 = new Usuario("usuario3", bCryptPasswordEncoder.encode("pass3"));
			repo.save(usuario3);

		};
	}

}
