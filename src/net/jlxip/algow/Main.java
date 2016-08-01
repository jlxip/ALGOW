/*
 * TODO: REMOVE OPTIONS NAME
 * TODO: ALLOW UTF-8 CHARACTERS
 * TODO: INCLUDE OTHER FILES
 * */

package net.jlxip.algow;

import java.util.ArrayList;
import java.util.Properties;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public class Main {
	public static final String fontSize = "18"; 
	public static final String font = "Courier";
	
	public ConsoleSystemInterface csi;
	
	public Main() {
		Properties text = new Properties();
		text.setProperty("fontSize", fontSize);
		text.setProperty("font", font);
		
		try {
			csi = new WSwingConsoleInterface("", text);
		} catch(ExceptionInInitializerError  eiie) {
			eiie.printStackTrace();
		}
		
		csi.cls();
		
		String worthcalc = "AGLOW";
		int worthcalc_x = (80 - worthcalc.length()) / 2;
		int worthcalc_y = 8;
		csi.print(worthcalc_x, worthcalc_y, worthcalc, CSIColor.WHITE);
		
		String start = "ENTER TO START";
		int start_x = (80 - start.length()) / 2;
		int start_y = 13;
		csi.print(start_x, start_y, start, CSIColor.LIGHT_LIME);
		
		csi.refresh();
		
		Boolean stop = false;
		while(!stop) {
			CharKey key = csi.inkey();
			if(key.code == CharKey.ENTER){
				stop = true;
			}
		}
		
		ReadAlgorithm read = new ReadAlgorithm();
		ArrayList<String> algorithm = read.read();
		
		LoadAlgorithm load = new LoadAlgorithm(algorithm);
		
		RunAlgorithm run = new RunAlgorithm(csi, load);
		run.start();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
