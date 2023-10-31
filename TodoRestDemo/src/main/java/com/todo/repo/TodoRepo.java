package com.todo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entities.Todo;

public interface TodoRepo extends JpaRepository<Todo, Long>{

	public List<Todo> findAllByIsCompleted( Boolean c);
}
