import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import bean.Node;
import util.Parser;

public class DrawingFrame extends Frame {
	DrawingPanel canvas = new DrawingPanel();
	CodingPanel textBox = new CodingPanel();
	public DrawingFrame(){
		setSize(900,700);
		setVisible(true);
		setLayout(null);
		setTitle("Drawing");
		canvas.setBounds(50, 50, 380, 480);
		textBox.setBounds(450, 50, 380, 480);
		add(canvas);
		add(textBox);

		Button btnClear=new Button();
		btnClear.setLabel("Clear");
		btnClear.setBounds(80,630,80,30);
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
		btnDraw.setBounds(280,630,80,30);
		add(btnDraw);
		btnDraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Graphics2D g2d = (Graphics2D)canvas.getGraphics();
				g2d.clearRect(0, 0, getWidth(), getHeight());
				//Setting the origin to be in the middle of the canvas
				g2d.translate(canvas.getWidth()/2, canvas.getHeight()/2);
				//Flipping the coordinates vertically so that y increases as you go up, not down
				AffineTransform flipVertical = AffineTransform.getScaleInstance(1, -1);
				g2d.transform(flipVertical);
				g2d.setColor(Color.blue);
				Area finalShape = Parser.parse(textBox.getText()).draw();
				g2d.fill(finalShape);
				canvas.paint(g2d);
			}
		});
		
		Button btnSquare = new Button();
		btnSquare.setLabel("Square");
		btnSquare.setBounds(180,530,80,30);
		add(btnSquare);
		btnSquare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Graphics2D g2d = (Graphics2D)canvas.getGraphics();
				Color color=new Color(13,189,183);
				g2d.setColor(color);
				Rectangle2D.Double r=new Rectangle2D.Double(100, 100,100,100);
				g2d.fill(r);
				canvas.paint(g2d);
			}
		});
		
		Button btnRotate = new Button();
		btnRotate.setLabel("Rotate");
		btnRotate.setBounds(280,530,80,30);
		add(btnRotate);
		btnRotate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Graphics2D g2d = (Graphics2D)canvas.getGraphics();
				g2d.rotate(Math.toRadians(15));
				g2d.draw(new Rectangle2D.Double(100, 100,
						100,
                        100));				
				canvas.paint(g2d);
			}
		});

		Button btnLoad = new Button();
		btnLoad.setLabel("Load");
		btnLoad.setBounds(480,630,80,30);
		add(btnLoad);

		Button btnSave = new Button();
		btnSave.setLabel("Save");
		btnSave.setBounds(680,630,80,30);
		add(btnSave);
		
		Button btnHelp = new Button();
		btnHelp.setLabel("Help");
		btnHelp.setBounds(680,530,80,30);
		add(btnHelp);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				HelpFrame help = new HelpFrame();
			}
		});

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e){
				dispose(); System.exit(0);}
		}
				);
	}
	

	public static void main(String args[])
	{
		DrawingFrame main = new DrawingFrame();
	}

}
