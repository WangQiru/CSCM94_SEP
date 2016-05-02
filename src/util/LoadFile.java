package util;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 	@class LoadFile
 *	This LoadFile class initializes a FileDialog for loading existing drawing from a file.
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */

@SuppressWarnings("serial")
public class LoadFile extends Frame {
	private String content;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Load the user commands from a .draw file.
	 */


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
					commands+=line + "\n";
				}
				bufferedReader.close();
				this.setContent(commands);
			} catch (IOException x) {
				System.err.format("IOException: %s%n", x);
			}
		}
		else {
			this.setContent(null);
		}
	}
}