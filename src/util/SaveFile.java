package util;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFile extends Frame {
	public SaveFile(String commands) {
		FileDialog fc=new FileDialog(this,"Save to a file",1);
		fc.setVisible(true);
		if(fc.getFile()!=null){
			try (
					FileWriter fileWriter = new FileWriter(fc.getDirectory()+fc.getFile()+".draw");
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);						
					) 
			{
				bufferedWriter.write(commands);
				bufferedWriter.close();
			} catch (IOException x) {
				System.err.format("IOException: %s%n", x);
			}
		}
	}
}