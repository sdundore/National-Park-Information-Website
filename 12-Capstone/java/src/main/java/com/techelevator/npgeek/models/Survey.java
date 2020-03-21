package com.techelevator.npgeek.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {
	
	@NotBlank(message = "Park selection is required")
	private String parkSelection;

	@NotBlank(message = "Email address is required")
	@Email(message = "Please enter a valid email address")
	private String email;

	@NotBlank(message = "Must select your state")
	private String state;
	
	private String activity;
	
	public Survey (){
		
	}
	
	public Survey (String parkSelection, String email, String state, String activity) {
		this.parkSelection = parkSelection;
		this.email = email;
		this.state = state;
		this.activity = activity;
	}
	

	public String getParkSelection() {
		return parkSelection;
	}

	public void setParkSelection(String parkSelection) {
		this.parkSelection = parkSelection;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	
}
