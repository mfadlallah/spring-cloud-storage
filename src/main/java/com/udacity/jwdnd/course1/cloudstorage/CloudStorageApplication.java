package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudStorageApplication {

	@Autowired
	CredentialsService credentialsService;

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}

	@Bean(name = "credentialsPassword")
	public CredentialsPassword credentialsPassword() {
		return (String password, String key) -> credentialsService.getPassword(password, key);
	}

	public interface CredentialsPassword {
		String getCredentialPassword(String password, String key);
	}
}
