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
	
	public int points;
	
	Pattern hash_pattern = Pattern.compile(Pattern.quote("#"));
	Pattern plus_pattern = Pattern.compile(Pattern.quote("+"));
	Pattern less_pattern = Pattern.compile(Pattern.quote("-"));
	
	public RunAlgorithm(ConsoleSystemInterface csi, LoadAlgorithm load) {
		this.csi = csi;
		this.load = load;
		points = 0;
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
			
			executeQuestion(question);
		} else if(plus_pattern.split(command).length>1){
			int add = Integer.parseInt(plus_pattern.split(command)[1]);
			points = points + add;
		} else if(less_pattern.split(command).length>1) {
			int take = Integer.parseInt(less_pattern.split(command)[1]);
			points = points - take;
		} else {
			switch(command) {
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
		
		String Squestion = question.get(1);
		int Squestion_x = (80 - Squestion.length()) / 2;	// We might put here just 1 later...
		int Squestion_y = 1;
		csi.print(Squestion_x, Squestion_y, Squestion, CSIColor.LIGHT_LIME);
		
		for(int i=0;i<options.size();i++) {
			String option = "-"+(i+1)+". "+options.get(i).get(1);
			int option_x = 1;
			int option_y = i+2;
			csi.print(option_x, option_y, option, CSIColor.WHITE);
		}
		
		csi.refresh();
		
		Boolean stop = false;
		while(!stop) {
			CharKey key = csi.inkey();
			if(key.code >= 117 && key.code <= 139){
				int num = key.code - 130;
				if(num < 0) {
					num = key.code - 117;
				}
				num--;	// This' because we added 1 before
				if(num >= 0 && num < options.size()) {
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
