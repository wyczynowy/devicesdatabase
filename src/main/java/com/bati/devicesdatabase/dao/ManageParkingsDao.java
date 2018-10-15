package com.bati.devicesdatabase.dao;

import java.util.List;

import com.bati.devicesdatabase.domain.ParkingObject;

public interface ManageParkingsDao {
	int createNewParkingObject(ParkingObject parkingOject) throws Exception;
	ParkingObject getParkingObject(int parkingObjectId);
	List<ParkingObject> getAllParkingObjects();
	void deleteParkingObject(int parkingObject);
	void updateParkingObject(ParkingObject parkingObject);
}
