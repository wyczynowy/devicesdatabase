package com.bati.devicesdatabase.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bati.devicesdatabase.domain.Device;
import com.bati.devicesdatabase.domain.ParkingObject;
import com.bati.devicesdatabase.service.ManageDevicesService;
import com.bati.devicesdatabase.service.ManageParkingsService;

@Controller
@RequestMapping(value = "/managedevices")
public class ManageDevicesController {
	
	@Autowired
	@Qualifier(value = "manageDevicesService")
	ManageDevicesService manageDevicesService;
	
	@Autowired
	@Qualifier(value = "manageParkingsService")
	ManageParkingsService manageParkingService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		List<Device> dList = new ArrayList<Device>();
		dList = manageDevicesService.getAllDevices();
		Collections.sort(dList);
		model.addAttribute("devices", dList);
		
		List<ParkingObject> paList = new ArrayList<ParkingObject>();
		paList = manageParkingService.getAllParkingObjects();
		model.addAttribute("parkingObjects", paList);
		
		return "managedevices";
	}
	
	@RequestMapping(value = "/adddevice", method = RequestMethod.GET)
	public String addDevice(Model model) {
		Device d = new Device();
		model.addAttribute("newDevice", d);
		
		List<ParkingObject> paList = new ArrayList<ParkingObject>();
		paList = manageParkingService.getAllParkingObjects();
		model.addAttribute("parkingObjects", paList);
		
		return "adddeviceform";
	}
	
	@RequestMapping(value = "addfilleddeviceform", method = RequestMethod.POST)
	public String addFilledDeviceForm(Model model, @RequestParam Map<String, String> newDevice) {
		
		Device d = new Device();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		d.setSerial(Integer.parseInt(newDevice.get("serial")));
		d.setDeviceType(newDevice.get("deviceType"));
		d.setManufacturedDate(LocalDate.parse(newDevice.get("manufacturedDate"), formatter));
		d.setManufacturerName(newDevice.get("manufacturerName"));
		d.setTestedDate(LocalDate.parse(newDevice.get("testedDate"), formatter));
		d.setTesterName(newDevice.get("testerName"));
		d.setAdditionalInfo(newDevice.get("additionalInfo"));
		d.setParkingObjectId(Integer.parseInt(newDevice.get("parkingObjectId")));
		
		try {
			@SuppressWarnings("unused")
			int newDeviceId = manageDevicesService.createNewDevice(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(newDevice.get("action").equals("Zapisz i dodaj kolejny")) {
//			Device d = new Device();
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String convertedManufacteredDate = formatter2.format(d.getManufacturedDate());
			String convertedTestedDate = formatter2.format(d.getTestedDate());
			
			d.setSerial(0);
			model.addAttribute("newDevice", d);
			model.addAttribute("convertedManufacteredDate", convertedManufacteredDate);
			model.addAttribute("convertedTestedDate", convertedTestedDate);
			
			List<ParkingObject> paList = new ArrayList<ParkingObject>();
			paList = manageParkingService.getAllParkingObjects();
			model.addAttribute("parkingObjects", paList);
			
			return "adddeviceform";
		}
		
		List<Device> dList = new ArrayList<Device>();
		dList = manageDevicesService.getAllDevices();
		Collections.sort(dList);
		model.addAttribute("devices", dList);
		
		List<ParkingObject> paList = new ArrayList<ParkingObject>();
		paList = manageParkingService.getAllParkingObjects();
		model.addAttribute("parkingObjects", paList);
		
		return "managedevices";
	}
	
	@RequestMapping(value = "/deletedevice")
	public String deleteDevice(Model model, @RequestParam("deviceId") int deviceId) {
		manageDevicesService.deleteDevice(deviceId);
		
		List<Device> dList = new ArrayList<Device>();
		dList = manageDevicesService.getAllDevices();
		Collections.sort(dList);
		model.addAttribute("devices", dList);
		return "managedevices";
	}
	
	@RequestMapping(value = "editdevice")
	public String editDevice(Model model, @RequestParam("deviceId") int deviceId) {
		
		Device d = new Device();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		d = manageDevicesService.getDevice(deviceId);
		model.addAttribute("device", d);
		
		String convertedManufacteredDate = formatter.format(d.getManufacturedDate());
		String convertedTestedDate = formatter.format(d.getTestedDate());
		model.addAttribute("convertedManufacteredDate", convertedManufacteredDate);
		model.addAttribute("convertedTestedDate", convertedTestedDate);
		
		List<ParkingObject> paList = new ArrayList<ParkingObject>();
		paList = manageParkingService.getAllParkingObjects();
		model.addAttribute("parkingObjects", paList);
		
		return "editdevice";
	}
	
	@RequestMapping(value = "/updatefilleddeviceform")
	public String updateFilledDeviceForm(Model model, @RequestParam Map<String, String> device) {
		
		Device d = new Device();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		d.setId(Integer.parseInt(device.get("id")));
		d.setSerial(Integer.parseInt(device.get("serial")));
		d.setDeviceType(device.get("deviceType"));
		d.setManufacturedDate(LocalDate.parse(device.get("manufacturedDate"), formatter));
		d.setManufacturerName(device.get("manufacturerName"));
		d.setTestedDate(LocalDate.parse(device.get("testedDate"), formatter));
		d.setTesterName(device.get("testerName"));
		d.setAdditionalInfo(device.get("additionalInfo"));
		d.setParkingObjectId(Integer.parseInt(device.get("parkingObjectId")));
		manageDevicesService.updateDevice(d);
		
		List<Device> dList = new ArrayList<Device>();
		dList = manageDevicesService.getAllDevices();
		Collections.sort(dList);
		model.addAttribute("devices", dList);
		
		List<ParkingObject> paList = new ArrayList<ParkingObject>();
		paList = manageParkingService.getAllParkingObjects();
		model.addAttribute("parkingObjects", paList);
		
		return "managedevices";
	}
}
