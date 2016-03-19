package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

public class FileIO{
	
	//FileIO reads/writes text from/to a file
	public FileIO(){
	}
	
	public static boolean fileSave(String instructions){
		//Opening a FileChooser and starting it in the current working directory
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = fileChooser.showSaveDialog(null);
		
		//If the user selects a file, write the given instructions to it
		if (result == JFileChooser.APPROVE_OPTION) {
		    File savedFile = fileChooser.getSelectedFile();
		    try{
		    	PrintWriter out = new PrintWriter(savedFile);
		    	out.println(instructions);
		    	out.close();
		    } catch(Exception e){
		    	return false;
		    }
		}
		return true;
	}
	
	public static String fileLoad(){
		//Opening a FileChooser and starting it in the current working directory
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = fileChooser.showOpenDialog(null);
		
		//Initializing the String to hold the contents of the file as empty
	    String fileContents = "";
	    
	    //If the user selects a file, open it, read it and store the contents in fileContents
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    try{
		    	BufferedReader fileReader = new BufferedReader(new FileReader(selectedFile));
		    	fileContents = fileReader.readLine();
			    fileReader.close();
		    } catch(Exception e){
		    	//If an error occurs, return null
		    	return null;
		    }
		}
		return fileContents;
	}
}