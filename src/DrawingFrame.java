import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import bean.Node;
import util.LoadFile;
import util.Parser;
import util.SaveFile;

public class DrawingFrame extends Frame {
	DrawingPanel canvas = new DrawingPanel();
	DrawingPanel canvasOutline = new DrawingPanel();
	CodingPanel textBox = new CodingPanel();
	Frame thisFrame;
	public DrawingFrame(){
		thisFrame=this;
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
		setTitle("Drawing");
		canvas.setBounds(spacingX, spacingY + 30, this.getWidth()/2 - (3*spacingX/2), this.getHeight() - (4*spacingY + 2*buttonY + 30));
		canvasOutline.setBounds(canvas.getX() - 1, canvas.getY() - 1, canvas.getWidth() + 2, canvas.getHeight() + 2);
		textBox.setBounds(canvas.getWidth() + canvas.getX() + spacingX, canvas.getY(), canvas.getWidth(), canvas.getHeight());
		textBox.textArea.setBounds(10, 10, textBox.getWidth() - 10, textBox.getHeight() - 10);
		add(canvas);
		add(canvasOutline);
		canvasOutline.setBackground(Color.black);

		add(textBox);

		Button btnClear=new Button();
		btnClear.setLabel("Clear");
		btnClear.setBounds(spacingX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
		add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub			
				canvas.repaint();
			}
		});

		Button btnDraw = new Button();
		btnDraw.setLabel("Draw");
		btnDraw.setBounds(canvas.getWidth() + canvas.getX() - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
		add(btnDraw);
		btnDraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(textBox.getText().trim().length() > 0){
					if(Parser.check(textBox.getText())){
						// TODO Auto-generated method stub
						Graphics2D g2d = (Graphics2D)canvas.getGraphics();
						g2d.clearRect(0, 0, getWidth(), getHeight());
						//Setting the origin to be in the middle of the canvas
						g2d.translate(canvas.getWidth()/2, canvas.getHeight()/2);
						//Flipping the coordinates vertically so that y increases as you go up, not down
						AffineTransform flipVertical = AffineTransform.getScaleInstance(1, -1);
						g2d.transform(flipVertical);
						g2d.setColor(Color.blue);

						Node node=Parser.parse(textBox.getText());
						if(node!=null){						
							Area finalShape = node.draw();
							g2d.fill(finalShape);
							canvas.paint(g2d);
						}
					}
				}
			}
		});

		Button btnLoad = new Button();
		btnLoad.setLabel("Load");
		btnLoad.setBounds(2*canvas.getWidth() + canvas.getX() + spacingX - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
		add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){				
				LoadFile lf=new LoadFile();
				textBox.setText(lf.getContent());
			}
		});

		Button btnSave = new Button();
		btnSave.setLabel("Save");
		btnSave.setBounds(2*spacingX + canvas.getWidth(), canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
		add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){				
				SaveFile sf=new SaveFile(textBox.getText());
			}
		});

		Button btnHelp = new Button();
		btnHelp.setLabel("Help");
		btnHelp.setBounds(2*canvas.getWidth() + canvas.getX() + spacingX - buttonX, canvas.getY() + canvas.getHeight() + 2*spacingY + buttonY, buttonX, buttonY);
		add(btnHelp);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				HelpFrame help = new HelpFrame();
			}
		});





		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose(); System.exit(0);
			}
		});

		addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e){
				canvas.setBounds(spacingX, spacingY + 30, getWidth()/2 - (3*spacingX/2), getHeight() - (4*spacingY + 2*buttonY + 30));
				canvasOutline.setBounds(canvas.getX() - 1, canvas.getY() - 1, canvas.getWidth() + 2, canvas.getHeight() + 2);
				textBox.setBounds(canvas.getWidth() + canvas.getX() + spacingX, canvas.getY(), canvas.getWidth(), canvas.getHeight());
				textBox.textArea.setBounds(10, 10, textBox.getWidth() - 10, textBox.getHeight() - 10);
				btnClear.setBounds(spacingX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
				btnDraw.setBounds(canvas.getWidth() + canvas.getX() - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
				btnLoad.setBounds(2*canvas.getWidth() + canvas.getX() + spacingX - buttonX, canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
				btnSave.setBounds(2*spacingX + canvas.getWidth(), canvas.getY() + canvas.getHeight() + spacingY, buttonX, buttonY);
				btnHelp.setBounds(2*canvas.getWidth() + canvas.getX() + spacingX - buttonX, canvas.getY() + canvas.getHeight() + 2*spacingY + buttonY, buttonX, buttonY);
			}
		});
	}


	public static void main(String args[])
	{
		DrawingFrame main = new DrawingFrame();
	}

}
