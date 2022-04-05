package com.rwallpapers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@SpringBootApplication
@EnableReactiveMongoAuditing
public class WallpaperAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WallpaperAppApplication.class, args);
	}

}
