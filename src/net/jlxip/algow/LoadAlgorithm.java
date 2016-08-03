package net.jlxip.algow;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class LoadAlgorithm {
	ArrayList<String> algorithm;
	
	// PATTERN AREA
	Pattern dot_pattern = Pattern.compile(Pattern.quote("."));
	Pattern space_pattern = Pattern.compile(Pattern.quote(" "));
	Pattern quote_pattern = Pattern.compile(Pattern.quote("\""));
	Pattern endline_pattern = Pattern.compile(Pattern.quote("\n"));
	Pattern underscore_pattern = Pattern.compile(Pattern.quote("_"));
	Pattern openbracket_pattern = Pattern.compile(Pattern.quote("{"));
	// END PATTERN AREA
	
	public LoadAlgorithm(ArrayList<String> algorithm) {
		this.algorithm = algorithm;
	}
	
	public ArrayList<List<String>> getQuestions() {
		ArrayList<List<String>> gotQuestions = new ArrayList<List<String>>();
		
		for(int i=0;i<algorithm.size();i++) {
			String[] dots = dot_pattern.split(algorithm.get(i));
			if(algorithm.get(i).toCharArray()[0]=='.') {
				String line = dots[1];
				line = openbracket_pattern.split(line)[0];
				String question_name = space_pattern.split(line)[0];
				String question_caption = quote_pattern.split(line)[1];
				ArrayList<String> question = new ArrayList<String>();
				question.add(question_name);
				question.add(question_caption);
				question.add(String.valueOf(i));	// Line of the declaration
				gotQuestions.add(question);
			} else {
				if(algorithm.get(i).equals("}")) {
					int declaration_line = Integer.parseInt(gotQuestions.get(gotQuestions.size()-1).get(2));
					String content = "";
					for(int j=declaration_line+1;j<i;j++) {
						if(j==i-1) {
							content += algorithm.get(j);
						} else {
							content += algorithm.get(j) + "\n";
						}
					}
					gotQuestions.get(gotQuestions.size()-1).add(content);
				}
			}
		}
		
		return gotQuestions;
	}
	
	public ArrayList<List<String>> getOptions(List<String> question) {
		ArrayList<List<String>> gotOptions = new ArrayList<List<String>>();
		
		String[] lines = endline_pattern.split(question.get(3));
		for(int i=0;i<lines.length;i++) {
			String clearLine = lines[i].replace("\t", "");	// Remove tabs
			
			ArrayList<Character> clearLine2 = new ArrayList<Character>();
			Boolean stopClearing = false;
			for(int j=0;j<clearLine.toCharArray().length;j++) {
				if(!stopClearing) {
					if(clearLine.toCharArray()[j]!=' ') {	// Remove spaces!
						stopClearing = true;
						clearLine2.add(clearLine.toCharArray()[j]);
					}
				} else {
					clearLine2.add(clearLine.toCharArray()[j]);
				}
			}
			
			char[] clearLine3 = new char[clearLine2.size()];
			for(int j=0;j<clearLine2.size();j++) {
				clearLine3[j]=clearLine2.get(j);
			}
			
			clearLine = String.valueOf(clearLine3);
			
			if(clearLine.toCharArray()[0]=='_') {
				String[] underscores = underscore_pattern.split(clearLine);
				String restofline = underscores[1];
				String option_caption = quote_pattern.split(restofline)[1];
				ArrayList<String> option = new ArrayList<String>();
				option.add(option_caption);
				option.add(String.valueOf(i));
				gotOptions.add(option);
			} else {
				if(clearLine.equals("}")) {
					int declaration_line = Integer.parseInt(gotOptions.get(gotOptions.size()-1).get(1));
					String content = "";
					for(int j=declaration_line+1;j<i;j++) {
						if(j==i-1) {
							content += lines[j];
						} else {
							content += lines[j] + "\n";
						}
					}
					gotOptions.get(gotOptions.size()-1).add(content);
				}
			}
		}
		
		for(int i=0;i<gotOptions.size();i++) {	// Error check :D
			if(gotOptions.get(i).size() == 1) {
				JOptionPane.showMessageDialog(null, "No commands found in "+gotOptions.get(i).get(0));
			}
		}
		
		return gotOptions;
	}
	
	public ArrayList<String> getCommands(List<String> option) {
		ArrayList<String> gotCommands = new ArrayList<String>();
		
		String lines[] = endline_pattern.split(option.get(2));
		for(int i=0;i<lines.length;i++) {
			String clearLine = lines[i].replace("\t", "");	// Remove tabs
			
			ArrayList<Character> clearLine2 = new ArrayList<Character>();
			Boolean stopClearing = false;
			for(int j=0;j<clearLine.toCharArray().length;j++) {
				if(!stopClearing) {
					if(clearLine.toCharArray()[j]!=' ') {	// Remove spaces!
						stopClearing = true;
						clearLine2.add(clearLine.toCharArray()[j]);
					}
				} else {
					clearLine2.add(clearLine.toCharArray()[j]);
				}
			}
			
			char[] clearLine3 = new char[clearLine2.size()];
			for(int j=0;j<clearLine2.size();j++) {
				clearLine3[j]=clearLine2.get(j);
			}
			
			clearLine = String.valueOf(clearLine3);			
			if(clearLine.toCharArray()[0] != '*') {	// With this we remove commentaries
				gotCommands.add(clearLine);
			}
		}
		
		return gotCommands;
	}
}
