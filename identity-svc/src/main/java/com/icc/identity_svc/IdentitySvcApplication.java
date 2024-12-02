package com.icc.identity_svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@SpringBootApplication
public class IdentitySvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentitySvcApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authz) ->
						authz.anyRequest().permitAll()
				)
				.httpBasic(withDefaults())
				.formLogin((form) ->
						form.loginPage("/login").permitAll()
				)
				.logout((logout) -> logout.permitAll());

		return http.build();
	}
}
