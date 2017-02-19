package edu.neu.csye6200.registry;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogManager {

	private static Logger logger = Logger.getLogger(VehicleTaxation.class.getName());
	private static FileHandler fileHandler;
	private static SimpleFormatter formatter;
	//doing this prevents multiple log files in system directory
	public static Logger getLoggerInstance(){
		try {
			fileHandler = new FileHandler("testLog.log", true);
			formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			logger.addHandler(fileHandler);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logger;
	}
}
