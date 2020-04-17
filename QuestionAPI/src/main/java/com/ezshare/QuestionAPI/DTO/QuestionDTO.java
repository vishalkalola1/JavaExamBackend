package com.ezshare.QuestionAPI.DTO;

import com.ezshare.QuestionAPI.Error.GeneralError;
import com.ezshare.Questionnaire.datamodel.Exam;
import com.ezshare.Questionnaire.datamodel.Question;

public class QuestionDTO extends GeneralError {
	
	private Long id;
	private String title;
	private Exam exam;
	private String type;

	public QuestionDTO() {
	}
	
	public QuestionDTO(Question question) {
		this.id = question.getId();
		this.title = question.getTitle();
		this.exam = question.getExam();
		this.type = question.getType();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
