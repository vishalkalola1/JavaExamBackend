package com.ezshare.Questionnaire.services;

import java.util.Map;

import com.ezshare.Questionnaire.datamodel.Answer;

public class AnswerDAO extends GenericDAO<Answer, Long> {

	@Override
	public void setParameters(Map<String, Object> parameters, Answer criteria) {
		parameters.put("pTitle", criteria.getId());
	}
}
