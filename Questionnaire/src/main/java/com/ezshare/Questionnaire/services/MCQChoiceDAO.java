package com.ezshare.Questionnaire.services;

import java.util.Map;

import com.ezshare.Questionnaire.datamodel.MCQChoice;

public class MCQChoiceDAO extends GenericDAO<MCQChoice, Long> {

	@Override
	public void setParameters(Map<String, Object> parameters, MCQChoice criteria) {
		// TODO Auto-generated method stub
		parameters.put("pTitle", criteria.getId());
	}
	
}