package com.ezshare.QuestionAPI.DTO;

import com.ezshare.QuestionAPI.Error.GeneralError;
import com.ezshare.Questionnaire.datamodel.Student;

public class UserDTO extends GeneralError {
	
	private String name;
	private String email;
	private String type;

	public UserDTO() {
	
	}
	
	public UserDTO(Student student) {
		this.name = student.getName();
		this.email = student.getEmail();
		this.type = student.getType();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
