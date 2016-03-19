package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

import userinterface.DrawingPanel;
import userinterface.CodingPanel;

public class FileIO{
	
	public DrawingPanel canvas;
	public CodingPanel textBox;
	
	public FileIO(DrawingPanel canvas, CodingPanel textBox){
		this.canvas = canvas;
		this.textBox = textBox;
	}
	
	public void fileOperation(String operation){
		//Opening a FileChooser and starting it in the current working directory
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		if (operation.equals("save")){
			int result = fileChooser.showSaveDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File savedFile = fileChooser.getSelectedFile();
			    try{
			    	PrintWriter out = new PrintWriter(savedFile);
			    	out.println(Parser.parse(textBox.getText()).print());
			    	out.close();
			    } catch(Exception ex){
					canvas.drawError("File Save Error"); 
			    }
			}
		}
		if(operation.equals("load")){
			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    try{
			    	BufferedReader fileReader = new BufferedReader(new FileReader(selectedFile));
			    	textBox.setText(fileReader.readLine());
				    fileReader.close();
			    } catch(Exception ex){
					canvas.drawError("File Read Error"); 
			    }
			}
		}
	}
	
}