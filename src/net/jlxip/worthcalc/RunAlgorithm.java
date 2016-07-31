package net.jlxip.worthcalc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class RunAlgorithm {
	ConsoleSystemInterface csi;
	LoadAlgorithm load;
	ArrayList<List<String>> questions;
	
	Pattern hash_pattern = Pattern.compile(Pattern.quote("#"));
	
	public RunAlgorithm(ConsoleSystemInterface csi, LoadAlgorithm load) {
		this.csi = csi;
		this.load = load;
	}
	
	public void executeCommand(String command) {
		if(hash_pattern.split(command).length>1) {
			ArrayList<String> question = null;
			for(int i=0;i<questions.size();i++) {
				if(questions.get(i).get(0).equals(hash_pattern.split(command)[1])) {
					question = (ArrayList<String>) questions.get(i);
					break;
				}
			}
			
			if(question == null) {
				JOptionPane.showMessageDialog(null, hash_pattern.split(command)[1]+" question not found. Exiting.");
				System.exit(1);
			}
		} else {
			switch(command) {
				case "++":
					
					break;
				case "--":
					
					break;
				case "end":
					
					break;
				case "":
					break;
				default:
					JOptionPane.showMessageDialog(null, "Unknown command: "+command);	// TODO: "In question x, option y"
					break;
			}
		}
	}
	
	public void executeQuestion(ArrayList<String> question) {
		ArrayList<List<String>> options = load.getOptions(question);
		
		csi.cls();
		
		for(int i=0;i<options.size();i++) {
			String option = "-"+(i+1)+". "+options.get(i).get(1);
			int option_x = 1;
			int option_y = i+1;
			csi.print(option_x, option_y, option, CSIColor.WHITE);
		}
		
		csi.refresh();
		
		Boolean stop = false;
		while(!stop) {
			CharKey key = csi.inkey();
			if(key.isAlphaNumeric()){
				int num = key.code - 117;
				if(num < 10 && num < options.size()) {	// "< 10" MIGHT BE POINTLESS
					stop = true;
					
					ArrayList<String> commands = load.getCommands(options.get(num));
					
					for(int i=0;i<commands.size();i++) {
						executeCommand(commands.get(i));
					}
				}
			}
		}
	}
	
	public void start() {
		questions = load.getQuestions();
		ArrayList<String> startquestion = null;
		for(int i=0;i<questions.size();i++) {
			if(questions.get(i).get(0).equals("start")) {
				startquestion = (ArrayList<String>) questions.get(i);
				break;
			}
		}
		
		if(startquestion == null) {
			JOptionPane.showMessageDialog(null, "Start question not found. Exiting.");
			System.exit(1);
		}
		
		executeQuestion(startquestion);
	}
}
