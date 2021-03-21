package com.example.demo.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "to_do")
public class Todo {
	
	@Id // @Id makes id/pond_id a primary key, primary keys are always unique
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "todo_id")
	private int todoId;
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	//@JoinColumn(name = "fk_user_id", nullable = false)
	private User user;
	
	@Column(name = "task")
	@NotNull
	private String task;
	
	@Column(name = "info")
	private String info;
	
	
	
	public Todo(int todoId, String task, String info, User user) {
		this.todoId = todoId;
		this.task = task;
		this.info = info;
		this.user = user;
	}
	
	public Todo(int userId, String task, String info) {
		this.user.setUserId(1);
		this.task = task;
		this.info = info;

	}
	public Todo(String task, String info) {
		this.task = task;
		this.info = info;
	}
	
	
	public Todo() {
		
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


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + todoId;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		if (todoId != other.todoId)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
