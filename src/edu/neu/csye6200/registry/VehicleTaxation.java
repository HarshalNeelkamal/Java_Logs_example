package edu.neu.csye6200.registry;
/**
 * CSYE 6200 Vehicle Taxation class
 * 
 * @author Harshal Vijay, Neelkamal
 * ID: 001645951
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class VehicleTaxation {

	private ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
	private HashMap<String, Vehicle> vehicleMap = new HashMap<String, Vehicle>();
	private static VehicleTaxation instance = null;
	static Logger logger = LogManager.getLoggerInstance();
	
	private VehicleTaxation(){
		//logged while returning instance
	}
	
	public static VehicleTaxation getInstance(){
		if(instance == null){
			instance = new VehicleTaxation();
			logger.info("Instance Created for VehicleTaxation Class");
		}
		return instance;
	}
	
	public ArrayList<Vehicle> getVehicleList() {
		return vehicleList;
	}
	public void setVehicleList(ArrayList<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
		for (Vehicle v: vehicleList){
			this.vehicleMap.put(v.getOwnerId(), v);
		}
	}
	public void addVehicle(Vehicle vehicle){
		if(this.vehicleMap.containsKey(vehicle.getOwnerId())){
			int index = getVehicleIndex(vehicle.getOwnerId());
			this.vehicleList.set(index, vehicle);
		}else{
			this.vehicleList.add(vehicle);
		}
		this.vehicleMap.put(vehicle.getOwnerId(), vehicle);
		
	}
	private int getVehicleIndex(String ownerId) {
		for (int i = 0; i < vehicleList.size(); i++) {
			Vehicle veh = vehicleList.get(i);
			if (veh.getOwnerId().equalsIgnoreCase(ownerId))
				return i;
		}
		return -1; // no Match found, so return -1;
	}
	public boolean removeVehicleWithId(String id){
		if(this.vehicleMap.containsKey(id)){
			this.vehicleMap.remove(id);
			for(int i=0; i<vehicleList.size();i++){
				Vehicle v = vehicleList.get(i);
				if(v.getOwnerId().equals(id)){
					vehicleList.remove(i);
				}
			}
			return true;
		}else{
			return false;
		}
		
	} 
	public Vehicle getVehicle(String input){
			return vehicleMap.get(input);
	}
	
	public String toString(){
		ArrayList<Vehicle> sortedList = sortVehicles(); // sorts vehicle list in ascending order based on owner id.
		String returnable = "";
		returnable = returnable + "==========================================================================" + "\n";
		returnable = returnable + "                            Tax Regestry" + "\n";
		returnable = returnable + "==========================================================================" + "\n";

		for(Vehicle v : sortedList){
			returnable = returnable + "___________________________________________________" + "\n";
			returnable = returnable + v.toString();
			returnable = returnable + "___________________________________________________" + "\n";
		}
		returnable = returnable + "==========================================================================" + "\n";
	
		return returnable;
	}	
	
	private ArrayList<Vehicle> sortVehicles(){
		for (int j = 0; j < vehicleList.size() - 1; j++){
		for (int i = 0; i < vehicleList.size() - 1; i++){
			if(vehicleList.get(i).getOwnerId().toLowerCase().compareTo(vehicleList.get(i + 1).getOwnerId().toLowerCase()) > 0){
				Vehicle temp = vehicleList.get(i);
				vehicleList.set(i,vehicleList.get(i + 1));
				vehicleList.set(i+1,temp);
			}
		}
		}
		return vehicleList;
	}
	
}
