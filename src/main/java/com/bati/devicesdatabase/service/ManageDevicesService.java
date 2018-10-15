package com.bati.devicesdatabase.service;

import java.util.List;

import com.bati.devicesdatabase.domain.Device;

public interface ManageDevicesService {
	int createNewDevice(Device device) throws Exception;
	Device getDevice(int deviceId);
	List<Device> getAllDevices();
	void deleteDevice(int deviceId);
	void updateDevice(Device device);
}
