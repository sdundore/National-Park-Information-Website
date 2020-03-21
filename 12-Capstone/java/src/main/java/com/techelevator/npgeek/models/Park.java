package com.techelevator.npgeek.models;

public class Park {

	private String parkCode;
	private String parkName;
	private String state;
	private long acres;
	private int elevation;
	private double trailLength;
	private int numOfCampsites;
	private String climate;
	private int yearFounded;
	private long annualVisitors;
	private String quote;
	private String quoteSource;
	private String description;
	private int entryFee;
	private int numAnimalSpecies;
	
	public Park() {
		
	}

	public Park (String parkCode, String parkName, String state, long acres, int elevation, double trailLength, int numOfCampsites, String climate, int yearFounded, long annualVisitors, String quote, String quoteSource, String description, int entryFee, int numAnimalSpecies){
		this.parkCode = parkCode;
		this.parkName = parkName;
		this.state = state;
		this.acres = acres;
		this.elevation = elevation;
		this.trailLength = trailLength;
		this.numOfCampsites = numOfCampsites;
		this.climate = climate;
		this.yearFounded = yearFounded;
		this.annualVisitors = annualVisitors;
		this.quote = quote;
		this.quoteSource = quoteSource;
		this.description = description;
		this.entryFee = entryFee;
		this.numAnimalSpecies = numAnimalSpecies;		
	}
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getAcres() {
		return acres;
	}
	public void setAcres(long acres) {
		this.acres = acres;
	}
	public int getElevation() {
		return elevation;
	}
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	public double getTrailLength() {
		return trailLength;
	}
	public void setTrailLength(double trailLength) {
		this.trailLength = trailLength;
	}
	public int getNumOfCampsites() {
		return numOfCampsites;
	}
	public void setNumOfCampsites(int numOfCampsites) {
		this.numOfCampsites = numOfCampsites;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public int getYearFounded() {
		return yearFounded;
	}
	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}
	public long getAnnualVisitors() {
		return annualVisitors;
	}
	public void setAnnualVisitors(long annualVisitors) {
		this.annualVisitors = annualVisitors;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getQuoteSource() {
		return quoteSource;
	}
	public void setQuoteSource(String quoteSource) {
		this.quoteSource = quoteSource;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getEntryFee() {
		return entryFee;
	}
	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
	}
	public int getNumAnimalSpecies() {
		return numAnimalSpecies;
	}
	public void setNumAnimalSpecies(int numAnimalSpecies) {
		this.numAnimalSpecies = numAnimalSpecies;
	}
}
