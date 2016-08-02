package net.jlxip.algow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class ReadAlgorithm {
	ArrayList<String> algorithm = new ArrayList<String>();
	
	Pattern at_pattern = Pattern.compile(Pattern.quote("@"));
	Pattern dotalg_pattern = Pattern.compile(Pattern.quote(".alg"));
	
	public void readFile(File file) {
		try {
			FileReader FRalgorithm = new FileReader(file);
			BufferedReader BRalgorithm = new BufferedReader(FRalgorithm);
			
			String lastLine = "";
			while((lastLine = BRalgorithm.readLine()) != null) {
				if(!lastLine.equals("")) {	// With this we removed empty lines
					if(lastLine.toCharArray()[0]=='@') {
						String includedName = at_pattern.split(lastLine)[1];
						String Sinclude = "alg"+File.separator+includedName;
						if(dotalg_pattern.split(includedName+" ").length<=1) {
							// DOESN'T HAVE THE EXTENSION
							Sinclude += ".alg";
						}
						File Finclude = new File(Sinclude);
						
						if(!Finclude.exists()) {
							JOptionPane.showMessageDialog(null, Sinclude+" file not found. Exiting.");
							System.exit(1);
						}
						
						readFile(Finclude);
					} else {
						algorithm.add(lastLine);
					}
				}
				
			}
			
			BRalgorithm.close();
			FRalgorithm.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public ArrayList<String> read() {
		File alg_folder = new File("alg");
		if(!alg_folder.exists() || !alg_folder.isDirectory()) {
			alg_folder.mkdir();
		}
		
		File Falgorithm = new File("alg"+File.separator+"start.alg");
		if(!Falgorithm.exists()) {
			JOptionPane.showMessageDialog(null, "alg"+File.separator+"start.alg file not found. Exiting.");
			System.exit(1);
		}
		
		readFile(Falgorithm);
		
		return algorithm;
	}
}
