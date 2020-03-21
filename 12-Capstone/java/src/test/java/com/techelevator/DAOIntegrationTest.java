package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.models.JDBCParkDao;
import com.techelevator.npgeek.models.JDBCSurveyDao;
import com.techelevator.npgeek.models.JDBCSurveyResultDao;
import com.techelevator.npgeek.models.JDBCWeatherDao;
import com.techelevator.npgeek.models.Park;
import com.techelevator.npgeek.models.Survey;
import com.techelevator.npgeek.models.SurveyResult;
import com.techelevator.npgeek.models.Weather;

public class DAOIntegrationTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private JDBCParkDao parkdao;
	private JDBCWeatherDao weatherdao;
	private JDBCSurveyDao surveydao;
	private JDBCSurveyResultDao surveyresultdao;
	
	final String PARK_CODE = "SOFA";

	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/* This method provides access to the DataSource for subclasses so that
	 * they can instantiate a DAO for testing */
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Before
	public void setup() {
		String sqlInsertPark = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) VALUES (?, 'Grey Sofa National Park', 'Michigan', 1, 0, 2, 4, 'Indoor', 2020, 10, 'Stay awhile and listen', 'Deckard Cain', 'Just a couch', 0, 1)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertPark, PARK_CODE);
		parkdao = new JDBCParkDao(dataSource);
		
		String sqlInsertWeather = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast) VALUES (?, 1, 20, 30, 'snow')";
		jdbcTemplate.update(sqlInsertWeather, PARK_CODE);
		weatherdao = new JDBCWeatherDao(dataSource);
		
		String sqlInsertSurvey = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) VALUES (?, 'a@b.com', 'MI', 'active')";
		jdbcTemplate.update(sqlInsertSurvey, PARK_CODE);
		surveydao = new JDBCSurveyDao(dataSource);
		
		surveyresultdao = new JDBCSurveyResultDao(dataSource);
	}
	
	@Test
	public void saveParkAndFindItById() {
		Park actualPark = new Park (PARK_CODE, "Grey Sofa National Park", "Michigan", 1, 0, 2, 4, "Indoor", 2020, 10, "Stay awhile and listen", "Deckard Cain", "Just a couch", 0, 1);
		Park expectedPark = parkdao.getParkById(PARK_CODE);
		
		assertParksEqual(expectedPark, actualPark);
	}
	
	@Test
	public void testGetAllParks() {
		List<Park> allParks = parkdao.getAllParks();
		int actual = allParks.size();
		int expected = 11;
		assertEquals (expected, actual);
	}

	private void assertParksEqual(Park expectedPark, Park actualPark) {
		assertEquals(expectedPark.getAcres(), actualPark.getAcres());
		assertEquals(expectedPark.getAnnualVisitors(), actualPark.getAnnualVisitors());
		assertEquals(expectedPark.getClimate(), actualPark.getClimate());
		assertEquals(expectedPark.getDescription(), actualPark.getDescription());
		assertEquals(expectedPark.getElevation(), actualPark.getElevation());
		assertEquals(expectedPark.getEntryFee(), actualPark.getEntryFee());
		assertEquals(expectedPark.getNumAnimalSpecies(), actualPark.getNumAnimalSpecies());
		assertEquals(expectedPark.getNumOfCampsites(), actualPark.getNumOfCampsites());
		assertEquals(expectedPark.getParkCode(), actualPark.getParkCode());
		assertEquals(expectedPark.getParkName(), actualPark.getParkName());
		assertEquals(expectedPark.getQuote(), actualPark.getQuote());
		assertEquals(expectedPark.getQuoteSource(), actualPark.getQuoteSource());
		assertEquals(expectedPark.getState(), actualPark.getState());
		assertEquals(expectedPark.getTrailLength(), actualPark.getTrailLength(), .00001);
		assertEquals(expectedPark.getYearFounded(), actualPark.getYearFounded());
	}
	
	@Test
	public void getWeatherReportReturnsWeather() {
		Weather theWeather = new Weather(20, 30, "snow");
		
		List <Weather> expected = new ArrayList<>();
		expected.add(theWeather);
		List <Weather> actual = weatherdao.getWeatherReport(PARK_CODE);

		assertWeatherEquals(expected,actual);
		
	}

	private void assertWeatherEquals(List<Weather> expected, List<Weather> actual) {
		assertEquals(expected.get(0).getLowTemp(), actual.get(0).getLowTemp());
		assertEquals(expected.get(0).getHighTemp(), actual.get(0).getHighTemp());
		assertEquals(expected.get(0).getForecast(), actual.get(0).getForecast());	
	}

	@Test
	public void testFindSurvey() {
		Survey expected = new Survey ("SOFA", "a@b.com", "MI", "active");
		
		Survey actual = surveydao.findSurveyByParkCode(PARK_CODE);
		
		assertSurveyEquals(expected,actual);
	}

	private void assertSurveyEquals(Survey expected, Survey actual) {
		assertEquals(expected.getActivity(), actual.getActivity());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.getParkSelection(), actual.getParkSelection());
		assertEquals(expected.getState(), actual.getState());
	}
	
	@Test
	public void testGetSurveyResults() {
		SurveyResult expected = new SurveyResult ("Grey Sofa National Park", 1);
		SurveyResult actual = new SurveyResult();
		
		List <SurveyResult> results = surveyresultdao.getSurveyResults();
		for (SurveyResult survey : results) {
			if (survey.getParkName().equals("Grey Sofa National Park")) {
				actual = survey;
			}
		}
		
		assertSurveyResultEquals(expected, actual);
	}

	private void assertSurveyResultEquals(SurveyResult expected, SurveyResult actual) {
		assertEquals(expected.getParkName(), actual.getParkName());
		assertEquals(expected.getNumPicked(), actual.getNumPicked());
		
	}

	
}
