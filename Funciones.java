//package pkg_main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Funciones {
	public static String[] readFile(String sFile) {
		FileReader tFileReader = null;
		
		try {
			tFileReader = new FileReader(sFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader tBuffReader = new BufferedReader(tFileReader);
		ArrayList<String> tLines = new ArrayList<String>();
		String sLine = null;
		
		try {
			while ((sLine = tBuffReader.readLine()) != null) {
				tLines.add(sLine);
			}
			
			tBuffReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tLines.toArray(new String[tLines.size()]);
	}
}
