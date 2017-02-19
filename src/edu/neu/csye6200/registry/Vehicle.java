package edu.neu.csye6200.registry;
/**
 * CSYE 6200 Vehicle starter class
 * 
 * @author Harshal Vijay, Neelkamal
 * ID: 001645951
 *
 */

public class Vehicle {

	private String make = "",model = "",ownerId = "";
	private Double vehicleValue;
	private int modelYear;
	private int passengers;
	private int fuelCap;
	private double kmpl;
	
	public Vehicle(String make,String model,String ownerId,int modelYear,Double vehicleValue,int passangers,double kmpl, int fuelCap) {
		this.make = make;
		this.model = model;
		this.ownerId = ownerId;
		this.modelYear = modelYear;
		this.vehicleValue = vehicleValue;
		this.fuelCap = fuelCap;
		this.kmpl = kmpl;
		this.passengers = passangers;
		}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public int getFuelCap() {
		return fuelCap;
	}

	public void setFuelCap(int fuelCap) {
		this.fuelCap = fuelCap;
	}

	public double getKmpl() {
		return kmpl;
	}

	public void setKmpl(double kmpl) {
		this.kmpl = kmpl;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Double getVehicleValue() {
		return vehicleValue;
	}

	public void setVehicleValue(Double vehicleValue) {
		this.vehicleValue = vehicleValue;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}
	
	public double claculateRange(){
		double range = 0.0;
		range = getKmpl() * getFuelCap();
		return range;
	}
	
	public double calculateAnualTax(){
		double tax = 0.0;
		tax = (vehicleValue/1000.0)*2.10;		
		return tax;
	}
	
	public String toString(){
		return String.format(" %1$-20s : %2$s \n %3$-20s : %4$s \n %5$-20s : %6$s \n %7$-20s : %8$s \n %9$-20s : %10$s \n %11$-20s : %12$s \n %13$-20s : %14$s \n %15$-20s : %16$s \n %17$-20s : %18$s \n %19$-20s : %20$s\n","OwnerId",getOwnerId(),"Make",getMake(),"Model",getModel(),"Year",getModelYear(),"Fuel Capacity",getFuelCap(),"Average on Gas",getKmpl() + " Km/Liter", "Range",claculateRange() + " Km","No. Of Passangers",getPassengers(),"vehicle value",getVehicleValue() + " Dollars","Annual Tax",calculateAnualTax()+ " Dollars");
	}
	
	public String toString(double range,double average,int capacity){
		return String.format(" %1$-20s : %2$s \n %3$-20s : %4$s \n %5$-20s : %6$s \n %7$-20s : %8$s \n %9$-20s : %10$s \n %11$-20s : %12$s \n %13$-20s : %14$s \n %15$-20s : %16$s \n %17$-20s : %18$s \n %19$-20s : %20$s\n","OwnerId",getOwnerId(),"Make",getMake(),"Model",getModel(),"Year",getModelYear(),"Battery Capacity",capacity,"Electric Average",average + " Km/W-H", "Battery Range",range + " Km","No. Of Passangers",getPassengers(),"vehicle value",getVehicleValue() + " Dollars","Annual Tax",calculateAnualTax()+ " Dollars");
	}
	
}
