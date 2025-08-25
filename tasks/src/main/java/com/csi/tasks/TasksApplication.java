package com.csi.tasks;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csi.tasks.model.Task;
import com.csi.tasks.model.TaskRepository;

@SpringBootApplication
@RestController
public class TasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "Welcome to Tasks Application";
	}

	@Bean
	CommandLineRunner init(TaskRepository taskRepository){
		return args ->{
			Task task = new Task(
				null,
				"Ir descansar",
				"tirar um bom cochilo",
				1, UUID.randomUUID());
				
			taskRepository.save(task);
			System.out.println("tarefa iniciada");
		};
	}

}
