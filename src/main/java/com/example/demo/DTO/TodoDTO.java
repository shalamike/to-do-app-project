package com.example.demo.DTO;

import java.util.Date;

//import java.sql.Date;

public class TodoDTO {
	private int todoId;
	
	private String task;
	
	private String info;
	
	private UserDTO user;
	
	public TodoDTO() {
		super();
	}
	
	public TodoDTO(int todoId, String task, String info) {
		super();
		this.todoId = todoId;
		this.task = task;
		this.info = info;
	}

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}



	@Override
	public String toString() {
		return "TodoDTO [todoId=" + todoId + ", task=" + task + ", info=" + info +"]";
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((info == null) ? 0 : info.hashCode());
//		result = prime * result + ((task == null) ? 0 : task.hashCode());
//		result = prime * result + todoId;
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		TodoDTO other = (TodoDTO) obj;
//		if (info == null) {
//			if (other.info != null)
//				return false;
//		} else if (!info.equals(other.info))
//			return false;
//		if (task == null) {
//			if (other.task != null)
//				return false;
//		} else if (!task.equals(other.task))
//			return false;
//		if (todoId != other.todoId)
//			return false;
//		return true;
//	}
	
	
	
}
