package net.jlxip.worthcalc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ReadAlgorithm {
	public ArrayList<String> read() {
		File Falgorithm = new File("algorithm.dat");
		if(!Falgorithm.exists()) {
			JOptionPane.showMessageDialog(null, "algorithm.dat file not found. Exiting.");
			System.exit(1);
		}
		
		ArrayList<String> algorithm = new ArrayList<String>();
		
		try {
			FileReader FRalgorithm = new FileReader(Falgorithm);
			BufferedReader BRalgorithm = new BufferedReader(FRalgorithm);
			
			String lastLine = "";
			while((lastLine = BRalgorithm.readLine()) != null) {
				algorithm.add(lastLine);
			}
			
			BRalgorithm.close();
			FRalgorithm.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return algorithm;
	}
}
