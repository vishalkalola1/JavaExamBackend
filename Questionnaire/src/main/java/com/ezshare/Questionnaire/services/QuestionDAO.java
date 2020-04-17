package com.ezshare.Questionnaire.services;

import java.util.Map;

import com.ezshare.Questionnaire.datamodel.Question;


public class QuestionDAO extends GenericDAO<Question, Long> {

	@Override
	public void setParameters(Map<String, Object> parameters, Question criteria) {
		parameters.put("pTitle", criteria.getTitle());
	}
}
