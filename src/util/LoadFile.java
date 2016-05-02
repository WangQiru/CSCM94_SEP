package util;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadFile extends Frame {
	private String content;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public LoadFile() {
		FileDialog fc=new FileDialog(this,"Load from a file",0);
		fc.setFile("*.draw");
		fc.setVisible(true);
		if(fc.getFile()!=null){
			try (
					FileReader fileReader = new FileReader(fc.getDirectory()+fc.getFile());
					BufferedReader bufferedReader = new BufferedReader(fileReader);							
					) 
			{
				String line = null;
				String commands = "";
				while ((line = bufferedReader.readLine()) != null) {
					commands+=line;
				}
				bufferedReader.close();
				this.setContent(commands);
			} catch (IOException x) {
				System.err.format("IOException: %s%n", x);
			}
		}
	}

	
	public static void main(String[] args) {
	
	}
}