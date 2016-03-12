package util;

import java.util.ArrayList;
import java.util.List;

import bean.*;

public class Parser {

	public static Node parse(String input){
		//Removing all newlines, tabs and whitespace from input string
		String instructions = input.replaceAll("\n", "").replaceAll("\t", "").replaceAll(" ", "");
		
		//Creating a substring of whatever is contained within the outermost pair of brackets
		String argument = instructions.substring(instructions.indexOf('(') + 1,instructions.lastIndexOf(')'));
		
		//Using getArgList to produce a list of the comma separated arguments
		List<String> argList = getArgList(argument);
		
		//Recursively creating the required nodes
		//SHAPES
		if (instructions.startsWith("Square")){
			return new Square(Double.parseDouble(argList.get(0)),argList.get(1));
		}
		
		else if (instructions.startsWith("Rectangle")){
			return new Rectangle(Double.parseDouble(argList.get(0)), Double.parseDouble(argList.get(1)), argList.get(2));
		}
		
		else if (instructions.startsWith("Triangle")){
			return new Triangle(Double.parseDouble(argList.get(0)),argList.get(1));
		}
		
		else if (instructions.startsWith("Circle")){
			return new Circle(Double.parseDouble(argList.get(0)),argList.get(1));
		}
		
		//TRANSFORMS
		else if (instructions.startsWith("Rotate")){
			return new Rotate(parse(argList.get(0)),Double.parseDouble(argList.get(1)));
		}
		
		else if (instructions.startsWith("Translate")){
			return new Translate(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(2)));
		}
		
		else if (instructions.startsWith("Scale")){
			//Two different calls for Scale because there are two different Scale functions (uniform and non-uniform)
			if (argList.size() == 2){
				return new Scale(parse(argList.get(0)),Double.parseDouble(argList.get(1)));
			}
			else{
				return new Scale(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(2)));
			}
		}
		
		//MIXES
		//Mix nodes take a List of Nodes as their input, so generate a List by parsing each
		//element of argsList into a Node
		else if (instructions.startsWith("Union")){
			List<Node> mixNodes = new ArrayList<Node>();
			for (int i = 0; i < argList.size(); ++i){
				mixNodes.add(parse(argList.get(i)));
			}
			return new Union(mixNodes);
			}
		
		else if (instructions.startsWith("Intersection")){
			List<Node> mixNodes = new ArrayList<Node>();
			for (int i = 0; i < argList.size(); ++i){
				mixNodes.add(parse(argList.get(i)));
			}
			return new Intersection(mixNodes);		}
		
		else if (instructions.startsWith("Difference")){
			List<Node> mixNodes = new ArrayList<Node>();
			for (int i = 0; i < argList.size(); ++i){
				mixNodes.add(parse(argList.get(i)));
			}
			return new Difference(mixNodes);
		}
		
		//Default return, should never be needed
		return null;
	}
	private static List<String> getArgList(String argument){
		ArrayList<String> argList = new ArrayList<String>();
		int endIndex = argument.length() - 1;
		int pointer = argument.length() - 1;
		int brackets = 0;
		boolean clause = false;
		//For each character in argument, moving from the end to the beginning
		while(pointer > 0){
			//If a close bracket is seen and we are not currently in a clause, begin a clause and keep
			//track of how many open and close brackets there have been
			if (argument.charAt(pointer) == ')'){
				clause = true;
				++brackets;
			}
			if (argument.charAt(pointer) == '('){
				--brackets;
			}
			//If we have reached the end of the current clause
			if (brackets == 0 && clause){
				//End the current clause
				clause = false;
			}
			//If we have reached a comma and are not in a clause i.e. we have moved the pointer
			//over one entire argument
			if (argument.charAt(pointer) == ',' && !clause){
				//Add that argument to the beginning of argList
				argList.add(0, argument.substring(pointer + 1, endIndex + 1));
				
				//Move the end pointer to the beginning of the next argument
				endIndex = pointer - 1;
			}
			//Move to the next character in argument
			--pointer;
		}
		//Add the final argument (that doesn't have a comma at the start of it) to argList
		argList.add(0, argument.substring(0, endIndex + 1));
		
		
		return argList;
	}
}