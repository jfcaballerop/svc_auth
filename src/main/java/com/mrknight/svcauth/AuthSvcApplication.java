package com.mrknight.svcauth;

import java.util.HashSet;
import java.util.Set;

import com.mrknight.svcauth.model.Role;
import com.mrknight.svcauth.model.Usuario;
import com.mrknight.svcauth.repository.RoleRepository;
import com.mrknight.svcauth.repository.UsuarioRepository;

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
	public CommandLineRunner initData(UsuarioRepository userRepo, RoleRepository roleRepo) {
		return args -> {
			Role role1 = new Role("ROLE_ADMIN", "Admin role");
			roleRepo.save(role1);
			Role role2 = new Role("ROLE_USER", "User role");
			roleRepo.save(role2);
			Role role3 = new Role("ROLE_SVC", "Service role");
			roleRepo.save(role3);

			Set<Role> roles = new HashSet<>();

			roles.add(role2);
			Usuario usuario1 = new Usuario("usuario", bCryptPasswordEncoder.encode("usuario"), roles); // Solo user
			userRepo.save(usuario1);

			roles.add(role1);
			Usuario usuario2 = new Usuario("admin", bCryptPasswordEncoder.encode("admin"),roles); // User & Admin
			userRepo.save(usuario2);

			roles.clear();
			roles.add(role3);
			Usuario usuario3 = new Usuario("service", bCryptPasswordEncoder.encode("service"),roles);
			userRepo.save(usuario3);


		};
	}

}
