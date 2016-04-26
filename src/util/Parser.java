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
/**
 * 	@class Parser
 *	This Parser class contains all methods for parsing user commands
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */

public class Parser {
	private static ArrayList<String> errList = new ArrayList<String>();
	
	/**
	 * Parse user input into Nodes
	 * @param input the user input 
	 * @return return a Node
	 */
	
	public static Node parse(String input){
		//Removing all newlines, tabs and whitespace from input string
		String instructions = input.replaceAll("\n", "").replaceAll("\t", "").replaceAll(" ", "").replaceAll("\r", "");

		//Using getArgList to produce a list of the comma separated arguments
		ArrayList<String> argList = getArgList(instructions);

		if(argList==null){
			errList.add("Oops, you must've forgentten to enter an opening/closing bracket !");
		}
		else{
			//Recursively creating the required nodes
			//SHAPES
			if (instructions.startsWith("Square(")){
				if (checkArgSize(argList.size(),1,true)){
					if(checkDouble(argList.get(0)))
						return new Square(Double.parseDouble(argList.get(0)));
					else
						errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
				}
				else
					errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}

			else if (instructions.startsWith("Rectangle(")){
				if (checkArgSize(argList.size(),2,true)){
					if(checkDouble(argList.get(0))&&checkDouble(argList.get(1)))
						return new Rectangle(Double.parseDouble(argList.get(0)), Double.parseDouble(argList.get(1)));
					else
						errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
				}
				else
				errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}

			else if (instructions.startsWith("Triangle(")){		
				if (checkArgSize(argList.size(),1,true)){
					if(checkDouble(argList.get(0)))
						return new Triangle(Double.parseDouble(argList.get(0)));
					else
						errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
				}
				else
					errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}

			else if (instructions.startsWith("Circle(")){
				if (checkArgSize(argList.size(),1,true)){
					if(checkDouble(argList.get(0)))
						return new Circle(Double.parseDouble(argList.get(0)));
					else
						errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
				}
				else
				errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}

			//A curve segment is defined by 4 points, each with an x and y value (8 values total) or 1 point wit an x ad y value (2 values total)
			//A curve is a sequence of one or more curve segments
			else if (instructions.startsWith("Curve(")){

				//Create an empty list of curve segment coordinates
				ArrayList<double[]> curveSegmentList = new ArrayList<double[]>();

				//If this curve consists of more than one curve segment, add each of them to the list 
				if (argList.get(0).startsWith("(")){
					for (int i = 0; i < argList.size(); ++i){

						//Taking the current curve segment string and converting it to a double array
						ArrayList<String> argList2 = getArgList(argList.get(i));
						double[] curvePoints = new double[argList2.size()];
						for (int j = 0; j < argList2.size(); ++j){
							if (checkArgSize(argList2.size(),2,true)||checkArgSize(argList2.size(),8,true)){
								if(checkDouble(argList2.get(j)))
									curvePoints[j] = Double.parseDouble(argList2.get(j));
								else{
									errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
									break;
								}
							}
							else{
								errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
								break;
							}
						}
						//Adding the current curve segment to the list
						curveSegmentList.add(curvePoints);
					}
				}
				//If the curve consists of only one curve segment, add it to the list
				else{
					double[] curvePoints = new double[argList.size()];
					for (int i = 0; i < argList.size(); ++i){
						if (checkArgSize(argList.size(),8,true)){
							if(checkDouble(argList.get(i)))
								curvePoints[i] = Double.parseDouble(argList.get(i));
							else{
								errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
								break;
							}
						}
						else{
							errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
							break;
						}
					}
					curveSegmentList.add(curvePoints);
				}
				return new ClosedCurve(curveSegmentList);
			}


			//TRANSFORMS
			//Repeat transforms take an extra argument (the number of repeats)
			else if (instructions.startsWith("RotateN(")){
				if (checkArgSize(argList.size(),3,true)){
					if (checkDouble(argList.get(1)) && checkInteger(argList.get(2)))
						return new Rotate(parse(argList.get(0)),Double.parseDouble(argList.get(1)),Integer.parseInt(argList.get(2)));
					else
						errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
				}
				else
				errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}

			else if (instructions.startsWith("Rotate(")){
				if (checkArgSize(argList.size(),2,true)){
					if(checkDouble(argList.get(1)))
						return new Rotate(parse(argList.get(0)),Double.parseDouble(argList.get(1)));
					else
						errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
				}
				else
				errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}

			else if (instructions.startsWith("TranslateN(")){
				if (checkArgSize(argList.size(),4,true)){
					if (checkDouble(argList.get(1)) && checkDouble(argList.get(2)) && checkInteger(argList.get(3)))
						return new Translate(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(2)),Integer.parseInt(argList.get(3)));
					else
						errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
				}
				else
				errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}

			else if (instructions.startsWith("Translate(")){
				if (checkArgSize(argList.size(),3,true)){
					if (checkDouble(argList.get(1)) && checkDouble(argList.get(2)))
						return new Translate(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(2)));
					else
						errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
				}
				else
				errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}

			else if (instructions.startsWith("ScaleN(")){
				//Two different calls for Scale because there are two different Scale functions (uniform and non-uniform)
				if (argList.size() == 3){
					if (checkDouble(argList.get(1)) && checkInteger(argList.get(2)))
						return new Scale(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(1)),Integer.parseInt(argList.get(2)));
					else
						errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
				}	
				else if (argList.size() == 4){
					if (checkDouble(argList.get(1)) && checkDouble(argList.get(2)) && checkInteger(argList.get(3)))
						return new Scale(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(2)),Integer.parseInt(argList.get(3)));
					else
						errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
				}
				else
				errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}

			else if (instructions.startsWith("Scale(")){
				if (argList.size() == 2){
					if (checkDouble(argList.get(1)))
						return new Scale(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(1)));
					else
						errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
				}
				else if (argList.size() == 3){
					if (checkDouble(argList.get(1)) && checkDouble(argList.get(2)))
						return new Scale(parse(argList.get(0)),Double.parseDouble(argList.get(1)), Double.parseDouble(argList.get(2)));
					else
						errList.add("You have entered wrong format of arguments for \""+instructions+"\"");
				}
				else
				errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}

			//MIXES
			//Mix nodes take a List of Nodes as their input, so generate a List by parsing each
			//element of argsList into a Node
			else if (instructions.startsWith("Union(")){
				if (checkArgSize(argList.size(),2,false)){
					ArrayList<Node> mixNodes = new ArrayList<Node>();
					for (int i = 0; i < argList.size(); ++i){
						mixNodes.add(parse(argList.get(i)));
					}
					return new Union(mixNodes);
				}
				else
				errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}

			else if (instructions.startsWith("Intersection(")){
				if (checkArgSize(argList.size(),2,false)){
					ArrayList<Node> mixNodes = new ArrayList<Node>();
					for (int i = 0; i < argList.size(); ++i){
						mixNodes.add(parse(argList.get(i)));
					}
					return new Intersection(mixNodes);					
				}
				else
				errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}

			else if (instructions.startsWith("Difference(")){
				if (checkArgSize(argList.size(),2,false)){
					ArrayList<Node> mixNodes = new ArrayList<Node>();
					for (int i = 0; i < argList.size(); ++i){
						mixNodes.add(parse(argList.get(i)));
					}
					return new Difference(mixNodes);
				}
				else
				errList.add("You have entered wrong number of arguments for \""+instructions+"\"");
			}
			else
				errList.add("You have a syntax error near \""+instructions.substring(0, Math.min(12,Math.min(instructions.length(),instructions.indexOf('('))))+"\"");
		}
		return null;
	}
	
	/**
	 * 
	 * Store error occurred when parsing the user input instructions
	 * 
	 */
	
	public static ArrayList<String> returnErrList(){
		ArrayList<String> temp=new ArrayList<String>(errList);
		errList.clear();
		return temp;
	}

	
	/**
	 * Split the user input instructions into a well-formatted ArrayList 
	 * @param instructions the user input instructions
	 * @return return an ArrayList of String for formatted instructions
	 */
	private static ArrayList<String> getArgList(String instructions){
		ArrayList<String> argList = new ArrayList<String>();
		if(checkBrackets(instructions)){
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
		else 
			return null;
	}

	/**
	 * Check if the number of parameters is correct
	 * @param inputSize the number of user input parameters
	 * @param correctSize the correct number of parameter for the Node type
	 * @param fixedArgSize if he number of parameters for the Node type should be fixed
	 * @return return true if the number of parameters is correct
	 */
	
	public static boolean checkArgSize(int inputSize,int correctSize, boolean fixedArgSize){
		if(fixedArgSize){
			if (inputSize!=correctSize)
				return false;
		}
		else
			if(inputSize<correctSize)
				return false;
		return true;		
	}
	
	/**
	 * Check if the parameter is parsable to Integer
	 * @param str the user input parameter
	 * @return return true if the parameter if parsable
	 */
	public static boolean checkInteger(String str){
		try
		{
			Integer.parseInt(str);
			return true;
		}
		catch( Exception e)
		{
			return false;
		}
	}
	
	/**
	 * Check if the parameter is parsable to Double
	 * @param str the user input parameter
	 * @return return true if the parameter if parsable
	 */
	
	public static boolean checkDouble(String str){
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch( Exception e)
		{
			return false;
		}		
	}
	
	/**
	 * Parse user input into Nodes
	 * @param str the formatted input 
	 * @return return true if the number of brackets is correct
	 */
	
	public static boolean checkBrackets(String str){
		if (str.trim().length() == 0){
			return false;
		}
		int brackets = 0;
		boolean anyBracket=false;
		for (int i = 0; i < str.length(); i++)
		{
			char current = str.charAt(i);
			if (current == '('){
				anyBracket=true;
				++brackets;
			}
			if (current == ')'){
				anyBracket=true;
				--brackets;
			}
			if (brackets < 0){
				return false;
			}
		}
		if (brackets != 0){
			return false;
		}
		if(!anyBracket)
			return false;
		return true;
	}
}