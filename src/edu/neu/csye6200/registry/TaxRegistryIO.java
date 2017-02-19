package edu.neu.csye6200.registry;
/**
 * CSYE 6200 TaxRegestryIO class
 * 
 * @author Harshal Vijay, Neelkamal
 * ID: 001645951
 *
 */


import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TaxRegistryIO {

	String base = "C:\\Users\\Harshal\\Desktop\\EclipseNeon\\Assignment2_CSYE6200_harshal\\src\\";
	Logger logger = LogManager.getLoggerInstance();
	
	public TaxRegistryIO(){
		logger.info("instance created for TaxRegestryIO");
	}

	public boolean saveVehicle(ArrayList<Vehicle> list, String fileName){
		logger.info("Saving data to file with Name "+ fileName);
		
		boolean success = false;
		
		for(Vehicle v : list){
			success = save(v,fileName);
		}
		logger.info("Finished Executing Save");
		return success;
	}
	
	private boolean save(Vehicle v,String fileName){
		boolean vehileAlreadyPresent = false;
		String printable = v.toString();
		try {
			FileWriter writer = new FileWriter(base+ fileName + ".txt" , true);	
			FileReader reader = new FileReader(base+ fileName + ".txt");
			BufferedWriter buffWriter = new BufferedWriter(writer);
			BufferedReader buffReader = new BufferedReader(reader);
			String line = buffReader.readLine();
			while(line != null){
				//operations to recognise saved pattern
				String line_noSpace = line.replace(" ", "");
				String[] temp = line_noSpace.split(":");
				String ownerId = v.getOwnerId();
				boolean contains = false;
				if(temp[0].equalsIgnoreCase("OwnerId")){
				contains = temp[1].equalsIgnoreCase(ownerId);
				}
				if(contains == true ){
					vehileAlreadyPresent = true;
					break;
				}
				line = buffReader.readLine();
			}
			
			if(vehileAlreadyPresent == false){
			//////////////////
			String[] printableArray= printable.split("\n");
			for(String s : printableArray){
				//String s_noSpace = s.replace(" ", "");
				buffWriter.write(s);
				buffWriter.newLine();
			}
			buffWriter.write("***End Of An Entry***");
			buffWriter.newLine();
			/////////////////
			}
			buffWriter.flush();
			buffWriter.close();
			buffReader.close();
			return true;
			
		}catch (IOException e) {
			System.out.println("An IO Exception Occured");
			logger.severe("IO Exceptio Occured in Save Method");
			e.printStackTrace();
			return false;
		}

	}
	
	public ArrayList<Vehicle> load(String fileName){
		logger.info("Loading data from file with Name "+ fileName);
		
		ArrayList<Vehicle> list = new ArrayList<Vehicle>();
		String[] input = new String[8];
		for(int i = 0 ; i < input.length; i ++){
			input[i] = " ";
		}
		FileReader reader;
		try {
			reader = new FileReader(base+ fileName + ".txt");
			BufferedReader buffReader = new BufferedReader(reader);
			String line = buffReader.readLine();
			boolean isElectric = false;
			while(line != null){
				String line_noSpace = line.replace(" ", "");
				String[] temp = line_noSpace.split(":");
				switch (temp[0]) {
				case "Make":
					input[0] = temp[1];
					break;
				case "Model":
					input[1] = temp[1];
					break;
				case "OwnerId":
					input[2] = temp[1];
					break;
				case "Year":
					input[3] = temp[1];
					break;
				case "vehiclevalue":
					input[4] = temp[1].replaceAll("Dollars","");
					break;
				case "No.OfPassangers":
					input[5] = temp[1];
					break;
				case "AverageonGas":
					input[6] = temp[1].replaceAll("Km/Liter","");
					break;
				case "FuelCapacity":
					input[7] = temp[1];
					break;
				case "ElectricAverage":
					input[6] = temp[1].replaceAll("Km/W-H","");
					isElectric = true;
					break;
				case "BatteryCapacity":
					input[7] = temp[1];
					break;
				case "***EndOfAnEntry***":
					Vehicle newVehicle;
					if(isElectric == false){
					newVehicle = new Vehicle(input[0], input[1], input[2],Integer.parseInt(input[3]),Double.parseDouble(input[4]), Integer.parseInt(input[5]),Double.parseDouble(input[6]), Integer.parseInt(input[7]));
					}else{
						newVehicle = new ElectricVehicle(input[0], input[1], input[2],Integer.parseInt(input[3]),Double.parseDouble(input[4]), Integer.parseInt(input[5]),Double.parseDouble(input[6]), Integer.parseInt(input[7]));
					}
					list.add(newVehicle);
					isElectric = false;
					break;
				default:
					break;
				}
				line = buffReader.readLine();
			}
			
		} catch (FileNotFoundException e) {
			logger.severe("File Not Found Exception Occured During Load");
			return null;
		} catch (IOException e) {
			logger.severe("IOException Exception Occured During Load");
			e.printStackTrace();
			return null;
		}
		
		logger.info("Finished Executing Load");
		return list;
		
	}
	
}


