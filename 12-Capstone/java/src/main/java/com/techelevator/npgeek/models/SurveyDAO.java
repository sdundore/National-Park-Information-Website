package com.techelevator.npgeek.models;

public interface SurveyDAO {

	void commitSurveyToDatabase(Survey survey);
	Survey findSurveyByParkCode(String parkCode);
}
