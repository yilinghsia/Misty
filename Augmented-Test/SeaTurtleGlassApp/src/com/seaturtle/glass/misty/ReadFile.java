package com.seaturtle.glass.misty;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gets the following info from the AIS data
 * ship length (decimeter), ship breath(decimeter), x position (meter), y position(meter), speed(cm/second), orientation(degree), ship name, update time (dd-mm-yyyy hh:mmLss), AIS name
 * 
 * Checks whether its a tow,tug ship
 **/
public class ReadFile {

	public static void main(String[] args) {
		String fileToRead = "files/Data.csv";
		BufferedReader br = null;
		String line = "";
		List<String> ship_length = new ArrayList<String>(), ship_breadth = new ArrayList<String>(), x_position = new ArrayList<String>(), y_position = new ArrayList<String>(), ship_speed = new ArrayList<String>(),
					 orientation = new ArrayList<String>(),ship_name = new ArrayList<String>(),update_time = new ArrayList<String>(),AIS_name = new ArrayList<String>(), isTug = new ArrayList<String>();
		String[] data = new String[0]; 
		
		try {
			br = new BufferedReader(new FileReader(fileToRead));
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\\|"); // split aan de hand van |  symbool
				data = split[0].split("\\u0002");

				ship_length.add(data[1]);
				ship_breadth.add(data[3]);
				x_position.add(data[5]);
				y_position.add(data[6]);
				ship_speed.add(data[7]);
				orientation.add(data[13]);
				ship_name.add(data[16]);
				isTug.add(data[28]);
				update_time.add(data[30]);
				AIS_name.add(data[48]);
			}				
		//System.out.println(AIS_name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done with reading file");
	}
}
