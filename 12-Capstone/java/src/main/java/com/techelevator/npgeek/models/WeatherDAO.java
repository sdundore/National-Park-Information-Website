package com.techelevator.npgeek.models;

import java.util.List;

public interface WeatherDAO {
	
	List<Weather> getWeatherReport(String parkId);

}
