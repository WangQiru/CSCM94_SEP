import java.awt.*;
import java.awt.event.*;

public class test extends Frame{

	public static void main(String args[]) {
		new test();
	}

	public test(){
		super("Java 2D Example01");
		setSize(400,300);
		setVisible(true);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e){
				dispose(); System.exit(0);}
		}
				);
	}

	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(50,50,200,200);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.blue);
		g2d.drawRect(75,75,300,200);
	}
}

