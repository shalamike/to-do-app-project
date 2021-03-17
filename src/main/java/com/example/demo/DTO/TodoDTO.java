package com.example.demo.DTO;

import java.util.Date;

//import java.sql.Date;

public class TodoDTO {
	private int todoId;
	
	private String task;
	
	private String info;
	
	private Date dueDate;
	
	private Date startDate;
	
	private Date dateComplete;
	
	public TodoDTO() {
		super();
	}
	
	public TodoDTO(int todoId, String task, String info, Date dueDate, Date startDate, Date dateComplete) {
		super();
		this.todoId = todoId;
		this.task = task;
		this.info = info;
		this.dueDate = dueDate;
		this.startDate = startDate;
		this.dateComplete = dateComplete;
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDateComplete() {
		return dateComplete;
	}

	public void setDateComplete(Date dateComplete) {
		this.dateComplete = dateComplete;
	}

	@Override
	public String toString() {
		return "TodoDTO [todoId=" + todoId + ", task=" + task + ", info=" + info + ", dueDate=" + dueDate
				+ ", startDate=" + startDate + ", dateComplete=" + dateComplete + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateComplete == null) ? 0 : dateComplete.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + todoId;
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
		TodoDTO other = (TodoDTO) obj;
		if (dateComplete == null) {
			if (other.dateComplete != null)
				return false;
		} else if (!dateComplete.equals(other.dateComplete))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		if (todoId != other.todoId)
			return false;
		return true;
	}
	
	
	
}
