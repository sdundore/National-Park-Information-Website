package com.techelevator.npgeek.models;

import java.util.List;

public interface ParkDAO {

	List<Park> getAllParks();

	Park getParkById(String parkId);

}
