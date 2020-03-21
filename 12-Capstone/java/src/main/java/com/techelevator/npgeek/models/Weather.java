package com.techelevator.npgeek.models;

public class Weather {
	
	private int lowTemp;
	private int highTemp;
	private String forecast;
	
	public Weather(){
		
	}
	
	public Weather (int lowTemp, int highTemp, String forecast) {
		this.lowTemp = lowTemp;
		this.highTemp = highTemp;
		this.forecast = forecast;
	}
	
	
	public int getLowTemp() {
		return lowTemp;
	}
	public void setLowTemp(int lowTemp) {
		this.lowTemp = lowTemp;
	}
	public int getHighTemp() {
		return highTemp;
	}
	public void setHighTemp(int highTemp) {
		this.highTemp = highTemp;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
}
