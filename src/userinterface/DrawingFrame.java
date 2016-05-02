package userinterface;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import util.LoadFile;
import util.Parser;
import util.SaveFile;
import bean.Node;
/**
 * 	@class DrawingFrame
 *	This DrawingFrame class serves as the main frame of this program.
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */

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
		canvas.setBackground(Color.white);
		add(canvas);

		//Cheating to get a 1-pixel-wide border around the canvas by creating an all-black canvas behind it
		DrawingPanel canvasOutline = new DrawingPanel();		
		canvasOutline.setBounds(canvas.getX() - 1, canvas.getY() - 1, canvas.getWidth() + 2, canvas.getHeight() + 2);
		canvasOutline.setBackground(Color.black);
		add(canvasOutline);

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

		//Dropdown for choosing drawing colour
		Choice colourChooser = new Choice();
		colourChooser.add("Black");
		colourChooser.add("Blue");
		colourChooser.add("Cyan");
		colourChooser.add("Dark Gray");
		colourChooser.add("Gray");
		colourChooser.add("Green");
		colourChooser.add("Light Gray");
		colourChooser.add("Magenta");
		colourChooser.add("Orange");
		colourChooser.add("Pink");
		colourChooser.add("Red");
		colourChooser.add("Yellow");
		colourChooser.setBounds(spacingX + canvas.getWidth()/2 - buttonX/2, canvas.getY() + canvas.getHeight() + spacingY, buttonX, 20);
		colourChooser.select("Blue");
		add(colourChooser);

		//Array storing the built-in Java colours as Color objects, for use with colour chooser dropdown
		Color[] colourList = {Color.BLACK,Color.BLUE,Color.CYAN,Color.DARK_GRAY,Color.GRAY,Color.GREEN,Color.LIGHT_GRAY,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.YELLOW};

		//Button for drawing pixel-by-pixel
		Button btnDrawPixel = new Button();
		btnDrawPixel.setLabel("Draw");
		btnDrawPixel.setBounds(canvas.getWidth() + canvas.getX() - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
		add(btnDrawPixel);
		btnDrawPixel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Node rootNode = Parser.parse(textBox.getText());
				ArrayList<String> errList=Parser.returnErrList();

				if(errList.size()>0){
					ErrorFrame error = new ErrorFrame();
					error.setTitle("Error");

					String helpText = "";

					for(int i=0;i<errList.size();i++){
						helpText+= (i+1) + ". " + errList.get(i)+" \n\n";
					}


					error.init(helpText);

				}
				else {
					canvas.drawPixels(rootNode, colourList[colourChooser.getSelectedIndex()]);
				}
			}
		});

		//Button for drawing using the Area class
		Button btnDraw = new Button();
		btnDraw.setLabel("Draw");
		btnDraw.setBounds(canvas.getWidth() + canvas.getX() - buttonX, canvas.getY() + canvas.getHeight() + 2*spacingY + buttonY, buttonX, buttonY);
		add(btnDraw);
		btnDraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Node rootNode = Parser.parse(textBox.getText());
				ArrayList<String> errList=Parser.returnErrList();

				if(errList.size()>0){
					ErrorFrame error = new ErrorFrame();
					error.setTitle("Error");

					String helpText = "";

					for(int i=0;i<errList.size();i++){
						helpText+= (i+1) + ". " + errList.get(i)+" \n\n";
					}


					error.init(helpText);

				}
				else
					canvas.drawArea(rootNode, colourList[colourChooser.getSelectedIndex()]);
				//				}
			}
		});

		Button btnSave = new Button();
		btnSave.setLabel("Save");
		btnSave.setBounds(2*spacingX + canvas.getWidth(), canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
		add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				SaveFile sf = new SaveFile(textBox.getText());
			}
		});

		Button btnLoad = new Button();
		btnLoad.setLabel("Load");
		btnLoad.setBounds(2*canvas.getWidth() + canvas.getX() + spacingX - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
		add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				LoadFile lf = new LoadFile();
				if (lf.getContent() != null){
					textBox.setText(lf.getContent());
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

		Checkbox cbAutoComplete=new Checkbox("Auto-Complete brackets");
		cbAutoComplete.setBounds(2*spacingX + canvas.getWidth(),  canvas.getY() + canvas.getHeight() + 2*spacingY + buttonY, 2*buttonX, buttonY);
		cbAutoComplete.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(cbAutoComplete.getState()){
					textBox.textArea.addKeyListener(new KeyAdapter() {
						public void keyTyped(KeyEvent e) {
							if (e.getKeyChar()=='(') {
								int newlines = 0;
								String text = textBox.textArea.getText();
								for (int i = 0; i < textBox.textArea.getCaretPosition() ; ++i){
									if (text.charAt(i) == '\r'){
										++newlines;				
									}
								}
								e.consume();
								textBox.textArea.insert("()", textBox.textArea.getCaretPosition() - newlines);
								textBox.textArea.setCaretPosition(textBox.textArea.getCaretPosition() - (newlines + 1));
							}
						}
					});
				}
				else{
					textBox.textArea.removeKeyListener(textBox.textArea.getKeyListeners()[0]);
				}

			}
		});
		add(cbAutoComplete);


		//Dynamically resizing and repositioning window elements on window resize
		addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e){
				canvas.setBounds(spacingX, spacingY + 30, getWidth()/2 - (3*spacingX/2), getHeight() - (4*spacingY + 2*buttonY + 30));
				canvasOutline.setBounds(canvas.getX() - 1, canvas.getY() - 1, canvas.getWidth() + 2, canvas.getHeight() + 2);
				textBox.setBounds(canvas.getWidth() + canvas.getX() + spacingX, canvas.getY(), canvas.getWidth(), canvas.getHeight());
				textBox.textArea.setBounds(10, 10, textBox.getWidth() - 10, textBox.getHeight() - 10);
				btnClear.setBounds(spacingX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
				colourChooser.setBounds(spacingX + canvas.getWidth()/2 - buttonX/2, canvas.getY() + canvas.getHeight() + spacingY, buttonX, 20);
				btnDrawPixel.setBounds(canvas.getWidth() + canvas.getX() - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
				btnDraw.setBounds(canvas.getWidth() + canvas.getX() - buttonX, canvas.getY() + canvas.getHeight() + 2*spacingY + buttonY, buttonX, buttonY);
				btnLoad.setBounds(2*canvas.getWidth() + canvas.getX() + spacingX - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
				btnSave.setBounds(2*spacingX + canvas.getWidth(), canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
				btnHelp.setBounds(2*canvas.getWidth() + canvas.getX() + spacingX - buttonX, canvas.getY() + canvas.getHeight() + 2*spacingY + buttonY, buttonX, buttonY);
				cbAutoComplete.setBounds(2*spacingX + canvas.getWidth(),  canvas.getY() + canvas.getHeight() + 2*spacingY + buttonY, 2*buttonX, buttonY);
			}
		});




		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
				System.exit(0);
			}
		});
		//Drawing axes to the canvas on window initialization. Done last to avoid axes being cleared/overwritten during initialization
		canvas.drawAxes();
	}


	public static void main(String args[])
	{
		DrawingFrame main = new DrawingFrame();
		main.setTitle("Drawing");
	}

}
