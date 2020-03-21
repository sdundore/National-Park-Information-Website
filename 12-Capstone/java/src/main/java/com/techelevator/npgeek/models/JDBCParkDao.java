package com.techelevator.npgeek.models;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCParkDao implements ParkDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<>();
		String sqlSelectAllParks = "SELECT * FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParks);
		while (results.next()) {
			allParks.add(mapRowToPark(results));
		}
		return allParks;
	}

	private Park mapRowToPark(SqlRowSet results) {
		Park thePark = new Park();
		
		thePark.setParkCode(results.getString("parkcode"));
		thePark.setParkName(results.getString("parkname"));
		thePark.setState(results.getString("state"));
		thePark.setAcres(results.getLong("acreage"));
		thePark.setElevation(results.getInt("elevationinfeet"));
		thePark.setTrailLength(results.getDouble("milesoftrail"));
		thePark.setNumOfCampsites(results.getInt("numberofcampsites"));
		thePark.setClimate(results.getString("climate"));
		thePark.setYearFounded(results.getInt("yearfounded"));
		thePark.setAnnualVisitors(results.getLong("annualvisitorcount"));
		thePark.setQuote(results.getString("inspirationalquote"));
		thePark.setQuoteSource(results.getString("inspirationalquotesource"));
		thePark.setDescription(results.getString("parkdescription"));
		thePark.setEntryFee(results.getInt("entryfee"));
		thePark.setNumAnimalSpecies(results.getInt("numberofanimalspecies"));
		
		return thePark;
	}

	@Override
	public Park getParkById(String parkId) {
		Park thePark = new Park();
		String sqlSelectPark = "SELECT * FROM park WHERE parkcode = ?";
		SqlRowSet query = jdbcTemplate.queryForRowSet(sqlSelectPark, parkId);
		while (query.next()) {
			thePark = (mapRowToPark(query));
		}
		return thePark;
	}

}
