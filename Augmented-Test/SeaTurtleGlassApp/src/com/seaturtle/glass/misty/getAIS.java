package com.seaturtle.glass.misty;

import java.io.IOException;
import java.net.URL;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class getAIS {

	public static void main(){

		try {
			URL AISurl = new URL("http://mob0.marinetraffic.com/ais/de/getxml_i.aspx?sw_x=24.0&sw_y=34.0&ne_x=30.0&ne_y=39.5&zoom=14");
			XMLReader myReader = XMLReaderFactory.createXMLReader();
			myReader.parse(new InputSource(AISurl.openStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
