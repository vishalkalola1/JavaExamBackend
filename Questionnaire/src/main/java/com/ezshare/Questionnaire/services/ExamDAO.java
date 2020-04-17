package com.ezshare.Questionnaire.services;

import java.util.Map;

import com.ezshare.Questionnaire.datamodel.Exam;

public class ExamDAO extends GenericDAO<Exam, Long> {

	@Override
	public void setParameters(Map<String, Object> parameters, Exam criteria) {
		
		parameters.put("pTitle", criteria.getTitle());
	}	
}