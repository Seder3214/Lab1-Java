package ru.edu.project.lab;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@OpenAPIDefinition(
		info=@Info(

				title = "PenzGtu Java Lab API",
				description = "API fro labs", version = "3.0.0",
				contact = @Contact(
						name = "Student PenzGTU",
						email="aldandrei999@gmail.com"
				)
		)
)
@SpringBootApplication
@EnableWebMvc
public class LabApplication {
	public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}

}
