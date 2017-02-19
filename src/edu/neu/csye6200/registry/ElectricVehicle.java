package edu.neu.csye6200.registry;
/**
 * CSYE 6200 Electric Vehicle class
 * 
 * @author Harshal Vijay, Neelkamal
 * ID: 001645951
 *
 */


public class ElectricVehicle extends Vehicle {

	private int batteryCapacity;
	private double kmpwh;
	
//Constructor
	public ElectricVehicle(String make, String model, String ownerId, int modelYear, Double vehicleValue,
			int passangers, double kmpwh, int batteryCapacity) {
		super(make, model, ownerId, modelYear, vehicleValue, passangers, kmpwh, batteryCapacity);
				
		this.batteryCapacity = batteryCapacity;
		this.kmpwh = kmpwh;
	
	}
//Getters and Setters
	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public double getKmpwh() {
		return kmpwh;
	}

	public void setKmpwh(double kmpwh) {
		this.kmpwh = kmpwh;
	}
//Public methods
	public double claculateRange(){
		double range = 0.0;
		range = batteryCapacity * kmpwh;
		return range;
	}
	public String toString(){
		String returnable = "";
		returnable = super.toString(claculateRange(), getKmpwh(), getBatteryCapacity());
		return returnable;
	}
	
}
