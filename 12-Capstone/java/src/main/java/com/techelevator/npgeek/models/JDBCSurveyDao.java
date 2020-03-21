package com.techelevator.npgeek.models;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyDao implements SurveyDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void commitSurveyToDatabase(Survey survey) {
		
		String sqlAddSurvey = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sqlAddSurvey, survey.getParkSelection(), survey.getEmail(), survey.getState(), survey.getActivity());

	}

	@Override
	public Survey findSurveyByParkCode(String parkCode) {
		Survey theSurvey = new Survey();
		
		String sqlGetSurvey = "SELECT * FROM survey_result WHERE parkcode = ?";
		SqlRowSet query = jdbcTemplate.queryForRowSet(sqlGetSurvey, parkCode);
		while (query.next()) {
			theSurvey.setParkSelection(query.getString("parkcode"));
			theSurvey.setEmail(query.getString("emailaddress"));
			theSurvey.setState(query.getString("state"));
			theSurvey.setActivity(query.getString("activitylevel"));
		}
		return theSurvey;
	}

}
