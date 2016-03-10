import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class DrawingFrame extends Frame {
	DrawingPanel left=new DrawingPanel();
	CodeingPanel right=new CodeingPanel();
	public DrawingFrame(){
		setSize(900,700);
		setVisible(true);
		setLayout(null);
		setTitle("Drawing");
		left.setBounds(50, 50, 380, 480);
		right.setBounds(450, 50, 380, 480);
		add(left);
		add(right);

		Button btnClear=new Button();
		btnClear.setLabel("Clear");
		btnClear.setBounds(80,630,80,30);
		add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub			
				left.repaint();
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
				Graphics2D g2d = (Graphics2D)left.getGraphics();
				g2d.rotate(Math.toRadians(45));
				g2d.setColor(Color.blue);
				g2d.clearRect(0, 0, 375, 475);
				Ellipse2D.Double e=new Ellipse2D.Double(200,100,40,170);
				g2d.draw(e);
				
				left.paint(g2d);
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
				Graphics2D g2d = (Graphics2D)left.getGraphics();
				Color color=new Color(13,189,183);
				g2d.setColor(color);
				Rectangle2D.Double r=new Rectangle2D.Double(100, 100,100,100);
				g2d.fill(r);
				left.paint(g2d);
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
				Graphics2D g2d = (Graphics2D)left.getGraphics();
				g2d.rotate(Math.toRadians(15));
				g2d.draw(new Rectangle2D.Double(100, 100,
						100,
                        100));				
				left.paint(g2d);
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
