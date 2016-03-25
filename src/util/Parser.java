package util;

import java.util.ArrayList;

import bean.Circle;
import bean.ClosedCurve;
import bean.Difference;
import bean.Intersection;
import bean.Node;
import bean.Rectangle;
import bean.Rotate;
import bean.Scale;
import bean.Square;
import bean.Translate;
import bean.Triangle;
import bean.Union;

public class Parser {

	public static Node parse(String input){
		//Removing all newlines, tabs and whitespace from input string
		String instructions = input.replaceAll("\n", "").replaceAll("\t", "").replaceAll(" ", "").replaceAll("\r", "");
		try {
			
			//Using getArgList to produce a list of the comma separated arguments
			ArrayList<String> argList = getArgList(instructions);
			
			//Recursively creating the required nodes
			//SHAPES
			if (instructions.startsWith("Square")){
				return new Square(Double.parseDouble(argList.get(0)));
			}
			
			else if (instructions.startsWith("Rectangle")){
				return new Rectangle(Double.parseDouble(argList.get(0)), Double.parseDouble(argList.get(1)));
			}
			
			else if (instructions.startsWith("Triangle")){
				return new Triangle(Double.parseDouble(argList.get(0)));
			}
			
			else if (instructions.startsWith("Circle")){
				return new Circle(Double.parseDouble(argList.get(0)));
			}
			
			//A curve segment is defined by 4 points, each with an x and y value (8 values total)
			//A curve is a sequence of one or more curve segments
			else if (instructions.startsWith("Curve")){
				
				//Create an empty list of curve segment coordinates
				ArrayList<double[]> curveSegmentList = new ArrayList<double[]>();
				
				//If this curve consists of more than one curve segment, add each of them to the list 
				if (argList.get(0).startsWith("(")){
					for (int i = 0; i < argList.size(); ++i){
						
						//Taking the current curve segment string and converting it to a double array
						ArrayList<String> argList2 = getArgList(argList.get(i));
						double[] curvePoints = new double[argList2.size()];
						for (int j = 0; j < argList2.size(); ++j){
							curvePoints[j] = Double.parseDouble(argList2.get(j));
						}
						//Adding the current curve segment to the list
						curveSegmentList.add(curvePoints);
					}
				}
				//If the curve consists of only one curve segment, add it to the list
				else{
					double[] curvePoints = new double[argList.size()];
					for (int i = 0; i < argList.size(); ++i){
						curvePoints[i] = Double.parseDouble(argList.get(i));
					}
					curveSegmentList.add(curvePoints);
				}
				return new ClosedCurve(curveSegmentList);
			}
			
			//TRANSFORMS
			//Repeat transforms take an extra argument (the number of repeats)
			else if (instructions.startsWith("RotateN")){
				return new Rotate(parse(argList.get(0)),Double.parseDouble(argList.get(1)),Integer.parseInt(argList.get(2)));
			}
			
			else if (instructions.startsWith("Rotate")){
				return new Rotate(parse(argList.get(0)),Double.parseDouble(argList.get(1)));
			}
			
			else if (instructions.startsWith("TranslateN")){
				return new Translate(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(2)),Integer.parseInt(argList.get(3)));
			}
			
			else if (instructions.startsWith("Translate")){
				return new Translate(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(2)));
			}
			
			else if (instructions.startsWith("ScaleN")){
				//Two different calls for Scale because there are two different Scale functions (uniform and non-uniform)
				if (argList.size() == 3){
					return new Scale(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(1)),Integer.parseInt(argList.get(2)));
				}
				else{
					return new Scale(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(2)),Integer.parseInt(argList.get(3)));
				}
			}
			
			else if (instructions.startsWith("Scale")){
				if (argList.size() == 2){
					return new Scale(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(1)));
				}
				else{
					return new Scale(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(2)));
				}
			}
			
			//MIXES
			//Mix nodes take a List of Nodes as their input, so generate a List by parsing each
			//element of argsList into a Node
			else if (instructions.startsWith("Union")){
				ArrayList<Node> mixNodes = new ArrayList<Node>();
				for (int i = 0; i < argList.size(); ++i){
					mixNodes.add(parse(argList.get(i)));
				}
				return new Union(mixNodes);
				}
			
			else if (instructions.startsWith("Intersection")){
				ArrayList<Node> mixNodes = new ArrayList<Node>();
				for (int i = 0; i < argList.size(); ++i){
					mixNodes.add(parse(argList.get(i)));
				}
				return new Intersection(mixNodes);		}
			
			else if (instructions.startsWith("Difference")){
				ArrayList<Node> mixNodes = new ArrayList<Node>();
				for (int i = 0; i < argList.size(); ++i){
					mixNodes.add(parse(argList.get(i)));
				}
				return new Difference(mixNodes);
			}
		} catch (Exception e) {}
		//Default return, should never be needed
		return null;
	}
	private static ArrayList<String> getArgList(String instructions){
		ArrayList<String> argList = new ArrayList<String>();
		
		//Creating a substring of whatever is contained within the outermost pair of brackets
		String argument = instructions.substring(instructions.indexOf('(') + 1,instructions.lastIndexOf(')'));
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
	
	
	public static boolean check(String str){
		if (str.trim().length() == 0){
			return false;
		}
		int brackets = 0;
		for (int i = 0; i < str.length(); i++)
		{
			char current = str.charAt(i);
			 if (current == '('){
				 ++brackets;
			 }
			 if (current == ')'){
				 --brackets;
			 }
			 if (brackets < 0){
				 return false;
			 }
		}
		if (brackets != 0){
			return false;
		}
		return true;
	}
}