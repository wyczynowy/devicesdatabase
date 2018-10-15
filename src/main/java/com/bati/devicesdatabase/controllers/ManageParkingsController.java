package com.bati.devicesdatabase.controllers;

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
@RequestMapping(value="/manageparkings")
public class ManageParkingsController {
	
	@Autowired
	@Qualifier(value = "manageParkingsService")
	ManageParkingsService manageParkingService;
	
	@Autowired
	@Qualifier(value = "manageDevicesService")
	ManageDevicesService manageDevicesService;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home(Model model) {
		List<ParkingObject> paList = new ArrayList<ParkingObject>();
		paList = manageParkingService.getAllParkingObjects();
		Collections.sort(paList);
		model.addAttribute("parkingObjects", paList);
		return "manageparkings";
	}
	
	@RequestMapping(value = "/addparkingobject", method = RequestMethod.GET)
	public String addParkingObject(Model model) {
		ParkingObject pa = new ParkingObject();
		model.addAttribute("newParkingObject", pa);
		return "addparkingobjectform";
	}
	
	@RequestMapping(value = "/addfilledparkingobjectform", method = RequestMethod.POST)
	public String addFilledParkingObjectForm(Model model, @RequestParam Map<String, String> newParkingObject) {
		ParkingObject po = new ParkingObject();
		po.setName(newParkingObject.get("name"));
		po.setAddress(newParkingObject.get("address"));
		po.setContact(newParkingObject.get("contact"));
		po.setDescription(newParkingObject.get("description"));
		
		try {
			@SuppressWarnings("unused")
			int newParkingObjectId = manageParkingService.createNewParkingObject(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<ParkingObject> paList = new ArrayList<ParkingObject>();
		paList = manageParkingService.getAllParkingObjects();
		Collections.sort(paList);
		model.addAttribute("parkingObjects", paList);
		return "manageparkings";
	}
	
	@RequestMapping(value = "/deleteparkingobject")
	public String deleteParkingObject(Model model, @RequestParam("parkingObjectId") int parkingObjectId) {
		
		// Najpierw nalezy sprawdzic czy obiekt ktory chcemy usunac nie jest powiazany z innymi
		// tabelami w bazie danych, jezeli tak to nalezy zapytac uzytkownika czy chce usunac
		// wszystkie powiazane z tym obiektem dane czy anulowac operacje
		
		List<Device> dList = new ArrayList<Device>();
		dList = manageDevicesService.getAllDevices();	// Pobieramy urzadzenia z tabeli devices
		int countRelatedDevices = 0;	// Informacja ile wpisow w tabeli devices jest powiazana z parkingiem, ktory chcemy usunac
		
		List<ParkingObject> paList = new ArrayList<ParkingObject>();
		paList = manageParkingService.getAllParkingObjects();
		Collections.sort(paList);
		
		// Przeszukujemy tabele z urzadzeniami
		for(Device device : dList) {
			if(device.getParkingObjectId() == parkingObjectId) countRelatedDevices++;	// Zliczamy ilosc powiazanych wpisow
		}
		
		if(countRelatedDevices == 0) {	// Jezeli parking nie jest powiazany z zadnymi innymi tabelami to go usuwamy
			manageParkingService.deleteParkingObject(parkingObjectId); 
			
			paList = manageParkingService.getAllParkingObjects();
			Collections.sort(paList);
			model.addAttribute("parkingObjects", paList);
			return "manageparkings";
		} else {	// W przeciwnym wypadku nalezy spytac uzytkownika co dalej
			
			String parkingNameToDelete = null;
			for(ParkingObject parkingObject : paList) {
				if(parkingObject.getId() == parkingObjectId) {
					parkingNameToDelete = parkingObject.getName();
					break;
				}
			}
			model.addAttribute("parkingObjectId", parkingObjectId);
			model.addAttribute("countRelatedDevices", countRelatedDevices);	// Dodajemy informacje i ilosci powiazanych urzadzen z tym parkingiem
			model.addAttribute("parkingName", parkingNameToDelete);
			return "processdeletparkingobject";
		}
			
	}
	
	@RequestMapping(value= "/proceeddeletparkingobject")
	public String proceedDeletParkingObject(Model model, @RequestParam("parkingObjectId") int parkingObjectId) {
		
		List<Device> dList = new ArrayList<Device>();
		dList = manageDevicesService.getAllDevices();
		List<Device> toDeletedDevicesList = new ArrayList<Device>();
		
		for(Device device : dList) {
			if(device.getParkingObjectId() == parkingObjectId) toDeletedDevicesList.add(device);
		}
		
		for(Device device : toDeletedDevicesList)
			manageDevicesService.deleteDevice(device.getId());
		
		manageParkingService.deleteParkingObject(parkingObjectId);
		
		List<ParkingObject> paList = new ArrayList<ParkingObject>();
		paList = manageParkingService.getAllParkingObjects();
		Collections.sort(paList);
		model.addAttribute("parkingObjects", paList);
		return "manageparkings";
	}
	
	
	@RequestMapping(value = "/editparkingobject")
	public String editparkingobject(Model model, @RequestParam("parkingObjectId") int parkingObjectId) {
		ParkingObject po = new ParkingObject();
		po = manageParkingService.getParkingObject(parkingObjectId);
		model.addAttribute("parkingObject", po);
		return "editparkingobject";
	}
	
	@RequestMapping(value = "/updatefilledparkingobjectform")
	public String updateFilledNote(Model model, @RequestParam Map<String, String> parkingObject) {
		ParkingObject po = new ParkingObject();
		po.setId(Integer.parseInt(parkingObject.get("id")));
		po.setName(parkingObject.get("name"));
		po.setAddress(parkingObject.get("address"));
		po.setContact(parkingObject.get("contact"));
		po.setDescription(parkingObject.get("description"));	
		manageParkingService.updateParkingObject(po);
		
		List<ParkingObject> paList = new ArrayList<ParkingObject>();
		paList = manageParkingService.getAllParkingObjects();
		Collections.sort(paList);
		model.addAttribute("parkingObjects", paList);
		
		return "manageparkings";		
	}
}
