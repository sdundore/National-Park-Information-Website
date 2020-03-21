package com.techelevator.npgeek.models;

public class SurveyResult {

	private String parkName;
	private int numPicked;
	
	public SurveyResult() {
		
	}
	
	public SurveyResult(String parkName, int numPicked) {
		this.parkName = parkName;
		this.numPicked = numPicked;
	}
	
	
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public int getNumPicked() {
		return numPicked;
	}
	public void setNumPicked(int numPicked) {
		this.numPicked = numPicked;
	}
	
}
