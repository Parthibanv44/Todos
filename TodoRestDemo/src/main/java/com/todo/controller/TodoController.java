package com.todo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dto.TodoDto;
import com.todo.entities.Todo;
import com.todo.repo.TodoRepo;

import jakarta.validation.Valid;

@RestController
public class TodoController {

	@Autowired
	private TodoRepo todoRepo;
	
	@GetMapping("/todos")
	public List<Todo> todos() {
		return this.todoRepo.findAll();
	}
	
	@GetMapping("/todos")
	public Optional<Object> todosAll(@RequestParam Optional<Boolean> completed) {
		return completed.map(c -> this.todoRepo.findAllByIsCompleted(c));
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getTodos(@PathVariable long id) {
		
		return this.todoRepo.findById(id)
				.map(todo -> ResponseEntity.ok().body(todo))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/addTodos")
	public ResponseEntity<Object> addTodos(@Valid @RequestBody TodoDto todoDto){
		this.todoRepo.save(new Todo(todoDto.getTask(),false));
		return ResponseEntity.ok().build();
		
	}
	
	@PutMapping("/updateTodos/{id}")
	public Optional<Object> updateTodos(@PathVariable long id,@Valid @RequestBody TodoDto todoDto){
		
		return this.todoRepo.findById(id)
				.map(todo -> {  todo.setTask(todoDto.getTask());
								this.todoRepo.save(todo);
								return ResponseEntity.ok().build();								
								});
		
	}
	
	@PutMapping("/addTodos/{id}/mark_completed")
	public Optional<Object> markCompleted(@PathVariable long id){
		
		return this.todoRepo.findById(id)
				.map(todo -> {  todo.setCompleted(true);
								this.todoRepo.save(todo);
								return ResponseEntity.ok().build();								
								});
		
	}
	
	@PutMapping("/addTodos/{id}/mark_incomplete")
	public Optional<Object> markInComplete(@PathVariable long id){
		
		return this.todoRepo.findById(id)
				.map(todo -> {  todo.setCompleted(false);
								this.todoRepo.save(todo);
								return ResponseEntity.ok().build();								
								});
		
	}
	
	@DeleteMapping("/addTodos/{id}")
	public Optional<Object> deleteTodos(@PathVariable long id){
		
		return this.todoRepo.findById(id)
				.map(todo -> {  
								this.todoRepo.delete(todo);
								return ResponseEntity.ok().build();								
								});
		
	}
	
}
