package com.bati.devicesdatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bati.devicesdatabase.dao.ManageParkingsDao;
import com.bati.devicesdatabase.domain.ParkingObject;

@Service(value = "manageParkingsService")
public class ManageParkingsServiceImpl implements ManageParkingsService {

	@Autowired
	@Qualifier(value = "manageParkingsDao")
	ManageParkingsDao manageParkingsDao;
	
	@Override
	@Transactional
	public int createNewParkingObject(ParkingObject parkingOject) throws Exception {
		return manageParkingsDao.createNewParkingObject(parkingOject);
	}

	@Override
	public ParkingObject getParkingObject(int parkingObjectId) {
		return manageParkingsDao.getParkingObject(parkingObjectId);
	}

	@Override
	public List<ParkingObject> getAllParkingObjects() {
		return manageParkingsDao.getAllParkingObjects();
	}

	@Override
	public void deleteParkingObject(int parkingObject) {
		manageParkingsDao.deleteParkingObject(parkingObject);
	}

	@Override
	public void updateParkingObject(ParkingObject parkingObject) {
		manageParkingsDao.updateParkingObject(parkingObject);
	}
}
