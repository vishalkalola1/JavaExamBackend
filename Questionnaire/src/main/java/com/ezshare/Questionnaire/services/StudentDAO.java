package com.ezshare.Questionnaire.services;

import java.util.Map;

import com.ezshare.Questionnaire.datamodel.Student;

public class StudentDAO extends GenericDAO<Student, String> {

	@Override
	public void setParameters(Map<String, Object> parameters, Student criteria) {
		parameters.put("pTitle", criteria.getName());
	}
}