package ru.edu.project.lab;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import ru.edu.project.lab.controller.AuthorController;
import ru.edu.project.lab.AuthorService;
import ru.edu.project.lab.AuthorRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@SpringBootApplication
public class LabApplication {
	public static void main(String[] args) {
		for(int i =0; i<100;i++) {
		}
		SpringApplication.run(LabApplication.class, args);
	}

}
