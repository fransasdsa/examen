package com.upeu.config.msconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication

public class MsConfigServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(MsConfigServerApplication.class, args);
	}

}
