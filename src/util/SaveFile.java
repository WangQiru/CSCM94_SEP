package util;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 	@class SaveFile
 *	This SaveFile class initializes a FileDialog for saving existing drawing into a file.
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */

public class SaveFile extends Frame {

	/**
	 * Save the user commands into a .draw file
	 * @param commands the user commands
	 */

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