package br.com.xmetrocubo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);

//		Map<String, PasswordEncoder> encoders = new HashMap<>();
//		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
//		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
//		passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());
//
//		String result = passwordEncoder.encode("admin1234");
//		System.out.println("My Hash " + result);
	}

}