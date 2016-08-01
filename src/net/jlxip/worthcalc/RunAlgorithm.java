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
	
	// PATTERN AREA
	Pattern hash_pattern = Pattern.compile(Pattern.quote("#"));
	Pattern plus_pattern = Pattern.compile(Pattern.quote("+"));
	Pattern less_pattern = Pattern.compile(Pattern.quote("-"));
	Pattern end_pattern = Pattern.compile(Pattern.quote("end "));
	Pattern quote_pattern = Pattern.compile(Pattern.quote("\""));
	Pattern double_equals_pattern = Pattern.compile(Pattern.quote("=="));
	Pattern morethan_pattern = Pattern.compile(Pattern.quote(">"));
	Pattern lessthan_pattern = Pattern.compile(Pattern.quote("<"));
	Pattern equals_pattern = Pattern.compile(Pattern.quote("="));
	Pattern space_pattern = Pattern.compile(Pattern.quote(" "));
	// END PATTERN AREA
	
	public RunAlgorithm(ConsoleSystemInterface csi, LoadAlgorithm load) {
		this.csi = csi;
		this.load = load;
		points = 0;
	}
	
	public String newCommand(String[] spaces) {
		String newcommand = "";
		for(int i=1;i<spaces.length;i++) {
			if(i==spaces.length-1) {
				newcommand += spaces[1];
			} else {
				newcommand += spaces[1] + " ";
			}
		}
		return newcommand;
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
		} else if(command.toCharArray()[0]=='+'){
			int add = Integer.parseInt(plus_pattern.split(command)[1]);
			points = points + add;
		} else if(command.toCharArray()[0]=='-') {
			int take = Integer.parseInt(less_pattern.split(command)[1]);
			points = points - take;
		} else if(space_pattern.split(command)[0].equals("end")) {
			csi.cls();
			
			String end_message = quote_pattern.split(command)[1];
			int end_message_x = (80 - end_message.length()) / 2;
			int end_message_y = 12;
			csi.print(end_message_x, end_message_y, end_message, CSIColor.WHITE);
			
			String enter = "Hit Enter to exit";
			int enter_x = (80 - enter.length()) / 2;
			int enter_y = 18;
			csi.print(enter_x, enter_y, enter, CSIColor.GREEN);
			
			csi.refresh();
			
			Boolean stop = false;
			while(!stop) {
				CharKey key = csi.inkey();
				if(key.code == CharKey.ENTER){
					stop = true;
				}
			}
			System.exit(0);
		} else if(double_equals_pattern.split(command).length>1) {
			String[] spaces = space_pattern.split(command);
			int num = Integer.valueOf(double_equals_pattern.split(spaces[0])[1]);
			
			if(points==num) {
				String newcommand = newCommand(spaces);
				executeCommand(newcommand);
			}
		} else if(morethan_pattern.split(command).length>1) {
			String[] spaces = space_pattern.split(command);
			int num;
			String newcommand = newCommand(spaces);
			
			if(equals_pattern.split(command).length>1) {
				// >=
				num = Integer.valueOf(equals_pattern.split(spaces[0])[1]);
				if(points>=num) {
					executeCommand(newcommand);
				}
			} else {
				// >
				num = Integer.valueOf(morethan_pattern.split(spaces[0])[1]);
				if(points>num) {
					executeCommand(newcommand);
				}
			}
		} else if(lessthan_pattern.split(command).length>1) {
			String[] spaces = space_pattern.split(command);
			int num;
			String newcommand = newCommand(spaces);
			
			if(equals_pattern.split(command).length>1) {
				// <=
				num = Integer.valueOf(equals_pattern.split(spaces[0])[1]);
				if(points<=num) {
					executeCommand(newcommand);
				}
			} else {
				// <
				num = Integer.valueOf(lessthan_pattern.split(spaces[0])[1]);
				if(points<num) {
					executeCommand(newcommand);
				}
			}
		} else {
			switch(command) {
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
