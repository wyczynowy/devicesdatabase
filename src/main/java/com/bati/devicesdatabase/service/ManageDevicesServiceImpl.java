package com.bati.devicesdatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bati.devicesdatabase.dao.ManageDevicesDao;
import com.bati.devicesdatabase.domain.Device;

@Service(value = "manageDevicesService")
public class ManageDevicesServiceImpl implements ManageDevicesService {

	@Autowired
	@Qualifier(value = "manageDevicesDao")
	ManageDevicesDao manageDevicesDao;
	
	@Override
	public int createNewDevice(Device device) throws Exception {
		return manageDevicesDao.createNewDevice(device);
	}

	@Override
	public Device getDevice(int deviceId) {
		return manageDevicesDao.getDevice(deviceId);
	}

	@Override
	public List<Device> getAllDevices() {
		return manageDevicesDao.getAllDevices();
	}

	@Override
	public void deleteDevice(int deviceId) {
		manageDevicesDao.deleteDevice(deviceId);
	}

	@Override
	public void updateDevice(Device device) {
		manageDevicesDao.updateDevice(device);
	}

}
