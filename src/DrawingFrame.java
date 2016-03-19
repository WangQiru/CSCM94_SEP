import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import bean.Node;
import util.Parser;

@SuppressWarnings("serial")
public class DrawingFrame extends Frame {
	public DrawingFrame(){
		
		//Horizontal spacing between elements
		int spacingX = 30;
		
		//Vertical spacing between elements
		int spacingY = 20;
		
		//Horizontal size of a button
		int buttonX = 80;
		
		//Vertical size of a button
		int buttonY = 30;
		
		setSize(900,700);
		setVisible(true);
		setLayout(null);
		setBackground(new Color(225,225,225));

		//The canvas on which shapes will be drawn
		DrawingPanel canvas = new DrawingPanel();
		canvas.setBounds(spacingX, spacingY + 30, this.getWidth()/2 - (3*spacingX/2), this.getHeight() - (4*spacingY + 2*buttonY + 30));
		add(canvas);
		canvas.setBackground(Color.white);

		//Cheating to get a 1-pixel-wide border around the canvas
		DrawingPanel canvasOutline = new DrawingPanel();		
		canvasOutline.setBounds(canvas.getX() - 1, canvas.getY() - 1, canvas.getWidth() + 2, canvas.getHeight() + 2);	
		add(canvasOutline);
		canvasOutline.setBackground(Color.black);
		
		//The text box in which instructions will be entered
		CodingPanel textBox = new CodingPanel();
		textBox.setBounds(canvas.getWidth() + canvas.getX() + spacingX, canvas.getY(), canvas.getWidth(), canvas.getHeight());
		textBox.textArea.setBounds(10, 10, textBox.getWidth() - 10, textBox.getHeight() - 10);
		add(textBox);

		Button btnClear=new Button();
		btnClear.setLabel("Clear");
		btnClear.setBounds(spacingX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
		add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.drawAxes();
			}
		});
		
		Button btnDrawPixel = new Button();
		btnDrawPixel.setLabel("Draw");
		btnDrawPixel.setBounds(canvas.getWidth() + canvas.getX() - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
		add(btnDrawPixel);
		btnDrawPixel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Node rootNode = Parser.parse(textBox.getText());
				if (rootNode == null){
					//Drawing an error message if Parser.parse fails
					canvas.drawError("Syntax Error"); 
				}
				else {
					//Creating an image that can be drawn pixel by pixel that is the size of the canvas
					BufferedImage pixelCanvas = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
					for (int i = 0; i < canvas.getHeight(); ++i){
						for (int j = 0; j < canvas.getWidth(); ++j){
							//Checking if the current pixel should be drawn. Because the origin of pixelCanvas is in the top left
							//corner, pixels are shifted by half the canvas width and height, and the y values are multiplied
							//by -1 to flip the image vertically
							if (rootNode.drawPixel(j - canvas.getWidth()/2, - (i - canvas.getHeight()/2))){
								pixelCanvas.setRGB(j, i, Color.BLUE.getRGB());
							}
							else{
								//If current pixel should not be drawn, draw a transparent pixel
								pixelCanvas.setRGB(j,  i, new Color(0.0f, 0.0f, 0.0f, 0.0f).getRGB());
							}
						}
					}
					canvas.drawPixels(pixelCanvas);
				}
			}
		});

		//Button for drawing using the Area class
//		Button btnDraw = new Button();
//		btnDraw.setLabel("Draw");
//		btnDraw.setBounds(canvas.getWidth() + canvas.getX() - buttonX, canvas.getY() + canvas.getHeight() + 2*spacingY + buttonY, buttonX, buttonY);
//		add(btnDraw);
//		btnDraw.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Node rootNode = Parser.parse(textBox.getText());
//				if (rootNode == null){
//					canvas.drawError("Syntax Error"); 
//				}
//				else {
//					canvas.drawArea(rootNode.draw());
//				}
//			}
//		});

		Button btnSave = new Button();
		btnSave.setLabel("Save");
		btnSave.setBounds(2*spacingX + canvas.getWidth(), canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
		add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//Opening a FileChooser and starting it in the current working directory
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int result = fileChooser.showSaveDialog(canvas.getParent());
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
		});

		Button btnLoad = new Button();
		btnLoad.setLabel("Load");
		btnLoad.setBounds(2*canvas.getWidth() + canvas.getX() + spacingX - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
		add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//Opening a FileChooser, starting it in the current working directory and limiting the files that can be opened to .txt files
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				fileChooser.setFileFilter(filter);
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int result = fileChooser.showOpenDialog(canvas.getParent());
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
		});

		Button btnHelp = new Button();
		btnHelp.setLabel("Help");
		btnHelp.setBounds(2*canvas.getWidth() + canvas.getX() + spacingX - buttonX, canvas.getY() + canvas.getHeight() + 2*spacingY + buttonY, buttonX, buttonY);
		add(btnHelp);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				HelpFrame help = new HelpFrame();
				help.setTitle("Help");
			}
		});
		//Dynamically resizing and repositioning window elements on window resize
		addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e){
				canvas.setBounds(spacingX, spacingY + 30, getWidth()/2 - (3*spacingX/2), getHeight() - (4*spacingY + 2*buttonY + 30));
				canvasOutline.setBounds(canvas.getX() - 1, canvas.getY() - 1, canvas.getWidth() + 2, canvas.getHeight() + 2);
				textBox.setBounds(canvas.getWidth() + canvas.getX() + spacingX, canvas.getY(), canvas.getWidth(), canvas.getHeight());
				textBox.textArea.setBounds(10, 10, textBox.getWidth() - 10, textBox.getHeight() - 10);
				btnClear.setBounds(spacingX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
//				btnDraw.setBounds(canvas.getWidth() + canvas.getX() - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
				btnLoad.setBounds(2*canvas.getWidth() + canvas.getX() + spacingX - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
				btnSave.setBounds(2*spacingX + canvas.getWidth(), canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
				btnDrawPixel.setBounds(canvas.getWidth() + canvas.getX() - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
				btnHelp.setBounds(2*canvas.getWidth() + canvas.getX() + spacingX - buttonX, canvas.getY() + canvas.getHeight() + 2*spacingY + buttonY, buttonX, buttonY);
			}
		});

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
				System.exit(0);
			}
		});
		//Drawing axes to the canvas on window initialization. Doesn't always work for some reason.
		canvas.drawAxes();
	}


	public static void main(String args[])
	{
		DrawingFrame main = new DrawingFrame();
		main.setTitle("Drawing");
	}

}
