package com.ezshare.Questionnaire.services;

import java.util.Map;

import com.ezshare.Questionnaire.datamodel.MCQAnswer;

public class MCQAnswerDAO extends GenericDAO<MCQAnswer, Long>{

	@Override
	public void setParameters(Map<String, Object> parameters, MCQAnswer criteria) {
		// TODO Auto-generated method stub
		parameters.put("pTitle", criteria.getId());
	}
	
}
