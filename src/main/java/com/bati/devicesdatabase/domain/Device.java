package com.bati.devicesdatabase.domain;

import java.time.LocalDate;

public class Device implements Comparable<Device>{
	private int id;
	private int serial;
	private String deviceType;
	private String manufacturerName;
	private LocalDate manufacturedDate;
	private LocalDate testedDate;
	private String testerName;
	private String additionalInfo;
	private int parkingObjectId;	// foreign key
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public LocalDate getManufacturedDate() {
		return manufacturedDate;
	}
	public void setManufacturedDate(LocalDate manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
	}
	public LocalDate getTestedDate() {
		return testedDate;
	}
	public void setTestedDate(LocalDate testedDate) {
		this.testedDate = testedDate;
	}
	public String getTesterName() {
		return testerName;
	}
	public void setTesterName(String testerName) {
		this.testerName = testerName;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public int getParkingObjectId() {
		return parkingObjectId;
	}
	public void setParkingObjectId(int parkingObjectId) {
		this.parkingObjectId = parkingObjectId;
	}
	
	@Override
	public int compareTo(Device device) {
        int compared = deviceType.compareTo(device.getDeviceType());
        
        if(compared == 0) {
            return ("" + serial).compareTo("" + device.getSerial());
        }
        else {
            return compared;
        }
	}
	
	
}
