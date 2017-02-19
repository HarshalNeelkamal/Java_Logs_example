package edu.neu.csye6200.registry;
/**
 * CSYE 6200 Vehicle Test class
 * 
 * @author Harshal Vijay, Neelkamal
 * ID: 001645951
 *
 */

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthStyle;

public class VehicleTest {

	Vehicle minivan;
	Vehicle sportscar;
	VehicleTaxation taxation;
	TaxRegistryIO registry;
	
	public VehicleTest(){
		
		minivan = new Vehicle("Verna", "ZsE", "Won_wiz", 2014, 30000.0, 6, 25, 85);
		sportscar = new Vehicle("Jaguar", "GTX", "Bharati.n", 2010, 30000.0, 2, 6, 70);
		taxation = VehicleTaxation.getInstance();
		taxation.addVehicle(minivan);
		taxation.addVehicle(sportscar);
		registry = new TaxRegistryIO();
		addTenVehicles();
	}
	
	public void addTenVehicles(){
		ArrayList<Vehicle> tempList = new ArrayList<Vehicle>();
		//some vehicles added by hard-coding to see if sorting works fine
		tempList.add(new Vehicle("merc", "Me", "Harshal.n", 2014, 30000.0, 6, 25, 85));
		tempList.add(new ElectricVehicle("napa", "nt", "chinmay.k", 2014, 30040.0, 4, 25, 85));
		tempList.add(new Vehicle("Audi", "221", "Antar.n", 2014, 40000.0, 63, 25, 85));
		tempList.add(new ElectricVehicle("Aviator", "2d", "WallyWest", 2014, 60000.0, 6, 25, 85));
		tempList.add(new Vehicle("Harle", "fix", "barry.allen", 2014, 30000.0, 6, 25, 85));
		tempList.add(new ElectricVehicle("Volantis", "68", "mat.hardy", 2014, 90000.0, 2, 25, 85));
		tempList.add(new Vehicle("Chevorle", "93", "Weesel", 2014, 10000.0, 6, 4, 85));
		tempList.add(new ElectricVehicle("ferrari", "mab", "Mark.m", 2014, 33000.0, 2, 12, 85));
		
		for(int i= 0; i<tempList.size();i++){
			taxation.addVehicle(tempList.get(i));
		}
	}
	
	public void run(){
		System.out.println("*********************************************************************");
		System.out.println("             VEHICLE LIST IN ASCENDING ORDER OF OWNER'ID:");
		System.out.println("*********************************************************************");
		System.out.println(taxation);//all printing commands would produce results in ascending order of ownerId 
	}
	
	public void BeginInteraction() throws IOException{
		String choice;
		do{
		System.out.println("*** Make a Choice and press Enter: ***\n    1.Add Vehicle to Tax Registery\n    2.Remove Vehicle from Tax Regestry\n    3.View Tax Registery\n    4.get Vehicle\n    5.Save Data Externally\n    6.Load Data From Externally Saved File\n    7.quit");
		do{
		choice = readLine();
		}while(choice.length() < 1 );
		
			switch (choice) {
			case "1":
					System.out.println("*** Please Make a Choise ***\n    1.Add an Electric car\n    2.Add an Gas based car");
					String subChoice;
					do{
						subChoice = readLine();
					}while(subChoice.length() < 1 );
					
					switch (subChoice) {
					case "1":
						Vehicle newVehicle = createElectricVehicle();
					    taxation.addVehicle(newVehicle);
						System.out.println(">>> Vehicle successfully added\n");
						break;
					case "2":
						newVehicle = createVehicle();
					    taxation.addVehicle(newVehicle);
						System.out.println(">>> Vehicle successfully added\n");
						break;
					default:
						System.out.println(">>> Invalid Choice\n");
						break;
					}
					
					break;
			case "2":
					String id = getParameterForVehicle();
					boolean done = taxation.removeVehicleWithId(id);
					if(done == true){
					System.out.println(">>> Car with owner Id: "+id+" successfully removed from the list");
					}else{
						System.out.println(">>> owner Id: "+id+" is Invalid");
					}
					break;
			case "3":
					System.out.println(taxation);
					break;
			case "4":
					String input = getParameterForVehicle();
					Vehicle v = taxation.getVehicle(input);
					if(v == null){
						System.out.println(">>> owner Id: "+input+" is Invalid");
					}else{
						System.out.println("====================================================================");
						System.out.println(v);
						System.out.println("====================================================================");
					}
					break;
			case "5":
					System.out.println("*** Please Provide a file name you desire ***");
					String fileName;
					do{
						fileName = readLine();
					}while(fileName.length() < 1 );
					boolean success = registry.saveVehicle(taxation.getVehicleList(), fileName);
					if(success == true){
						System.out.println(">>> Data added to file with name: "+fileName);	
					}else{
						System.out.println(">>> Operation Failed");	
					}
					break;
			case "6":
					System.out.println("*** Please Provide a file name you desire ***");
					do{
						fileName = readLine();
					}while(fileName.length() < 1 );
					ArrayList<Vehicle> fromDirectory = registry.load(fileName);
					if(fromDirectory == null){
						System.out.println(">>> File Not Found");
					}else{
						for(Vehicle veh : fromDirectory){
							System.out.println("====================================================================");
							System.out.println(veh);
							System.out.println("====================================================================");
						}
					}
					break;
			case "7":
					
					break;
			default:
					System.out.println(">>> invalid choice");	
					break;
			}
		}while(!choice.equals("7"));
		
	}
	
	public static void main(String[] args) throws IOException {
 
		VehicleTest testObj = new VehicleTest();
		testObj.run();
		testObj.BeginInteraction();
		
	}
// to soother bugs of in.read
	 static String readLine() throws java.io.IOException {
		   String retStr = "";
		   char inChar;
		   while ( (inChar = (char) System.in.read()) >= ' ') { // Let's take anything greater than an ASCII space (32)
		 retStr = retStr + inChar; // Add each character to our String
		   }
		   return retStr; // OK, we're done. Return the input string
		}
	 static Vehicle createVehicle() throws IOException{
		 String[] input = new String[8];
		 String[] parameters = {"make","model","ownerId","modelYear","vehicleValue","passangers","kmpl","fuelCap"};
		System.out.println("*** Please Enter the Following Details: ***\n");
		for (int i = 0; i < parameters.length; i++) {
			boolean everyThingFine = true;
			do{
			everyThingFine = true;
			System.out.println((i+1)+"."+parameters[i]);
			do{
				input[i] = readLine();
			}while(input[i].length() < 1 );
			
			if(i > 2){
				if(input[i].matches("[0-9]+") == true){
					
				}else{
					System.out.println("!!! "+parameters[i]+" cant contain alphabets; please re-enter");
					everyThingFine = false;
				}
			}
			}while(everyThingFine == false);
		}
		 Vehicle newVehicle = new Vehicle(input[0], input[1], input[2],Integer.parseInt(input[3]),Double.parseDouble(input[4]), Integer.parseInt(input[5]),Double.parseDouble(input[6]), Integer.parseInt(input[7])); 
         return newVehicle;
	 }
// interacts specifically with parameters for electric vehicle
	 static Vehicle createElectricVehicle() throws IOException{
		 String[] input = new String[10];
		 String[] parameters = {"make","model","ownerId","modelYear","vehicleValue","passangers","kmpwh","Battery Cap"};
		System.out.println("*** Please Enter the Following Details: ***\n");
		for (int i = 0; i < parameters.length; i++) {
			boolean everyThingFine = true;
			do{
			everyThingFine = true;
			System.out.println((i+1)+"."+parameters[i]);
			do{
				input[i] = readLine();
			}while(input[i].length() < 1 );
			
			if(i > 2){
				if(input[i].matches("[0-9]+") == true){
					
				}else{
					System.out.println("!!! "+parameters[i]+" cant contain alphabets; please re-enter");
					everyThingFine = false;
				}
			}
			}while(everyThingFine == false);
		}
		 Vehicle newVehicle = new ElectricVehicle(input[0], input[1], input[2],Integer.parseInt(input[3]),Double.parseDouble(input[4]), Integer.parseInt(input[5]),Double.parseDouble(input[6]), Integer.parseInt(input[7])); 
         return newVehicle;
	 }
//interacts with user for retreving ID
     static String getParameterForVehicle() throws IOException{
 		String input;
 		System.out.println("*** Enter the owner Id. ***");
    	 do{
				input = readLine();
			}while(input.length() < 1 );
		return input;
     }
}
