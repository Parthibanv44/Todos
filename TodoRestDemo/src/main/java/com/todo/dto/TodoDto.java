package com.todo.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoDto {

	@NotBlank
	private String task;

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
}
