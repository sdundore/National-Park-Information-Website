package com.techelevator.npgeek.models;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyResultDao implements SurveyResultDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSurveyResultDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<SurveyResult> getSurveyResults() {
		List<SurveyResult> results = new ArrayList<>();
		
		String sqlGetSurvey = "SELECT park.parkname, COUNT(survey_result.parkcode) as \"pickednumber\" FROM survey_result JOIN park ON park.parkcode = survey_result.parkcode GROUP BY park.parkname ORDER BY \"pickednumber\" DESC, park.parkname ASC;";
		SqlRowSet query = jdbcTemplate.queryForRowSet(sqlGetSurvey);
			while (query.next()) {
				results.add(mapRowToSurveyResult(query));
			}
		
		return results;
	}

	private SurveyResult mapRowToSurveyResult(SqlRowSet query) {
		SurveyResult result = new SurveyResult();
		result.setParkName(query.getString("parkname"));
		result.setNumPicked(query.getInt("pickednumber"));
		
		return result;
	}

}
