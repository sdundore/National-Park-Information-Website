package com.techelevator.npgeek.models;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCWeatherDao implements WeatherDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getWeatherReport(String parkId) {
		List <Weather> theWeather = new ArrayList<>();
		
		String sqlGetWeather = "SELECT * FROM weather WHERE parkcode = ?";
		SqlRowSet query = jdbcTemplate.queryForRowSet(sqlGetWeather, parkId);
		while (query.next()) {
			theWeather.add(mapRowToWeather(query));
		}		
		
		return theWeather;
	}

	private Weather mapRowToWeather(SqlRowSet query) {
		Weather theWeather = new Weather();
		theWeather.setLowTemp(query.getInt("low"));
		theWeather.setHighTemp(query.getInt("high"));
		theWeather.setForecast(query.getString("forecast"));
		if (theWeather.getForecast().equals("partly cloudy")) { 
			theWeather.setForecast("partlyCloudy");
			}
		return theWeather;
	}

}
