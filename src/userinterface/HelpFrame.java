package userinterface;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class HelpFrame extends Frame {
	public HelpFrame(){
		setSize(520,600);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setBackground(new Color(225,225,225));
		int spacingX = 20;
		int spacingY = 20;
		int buttonX = 80;
		int buttonY = 30;
		
		String helpText = "Syntax for inputting instructions:\n \n"
				+ "SHAPES\n"
				+ "Square(radius)\n"
				+ "Rectangle(width, height)\n"
				+ "Circle(radius)\n"
				+ "Triangle(radius)\n"
				+ "Curve(curve segment 1, curve segment 2,...) \n"
				+ "Curve segments are either Bezier curves defined as: (x1, y1, x2, y2, x3, y3, x4, y4) \n"
				+ "OR individual points defined as: (x, y).\n"
				+ "The Curve command can also be used to draw polygons containing only straight lines between vertices "
				+ "if desired. \n"
				+ "If two consecutive Bezier curves don't share a start/end point, a line is drawn between them "
				+ "to form a continuous shape. \n \n"
				+ "TRANSFORMS\n"
				+ "Rotate(Node, angle) - Rotation is anticlockwise\n"
				+ "Translate(Node, X distance, Y distance)\n"
				+ "Scale(Node, factor) OR Scale(Node, X factor, Y factor)\n \n"
				+ "REPEAT TRANSFORMS\n"
				+ "RotateN(Node, angle, repeats)\n"
				+ "TranslateN(Node, X distance, Y distance, repeats)\n"
				+ "ScaleN(Node, factor, repeats) OR Scale(Node, X factor, Y factor, repeats)\n \n"
				+ "MIXES\n"
				+ "Union(Node, Node, Node,...)\n"
				+ "Intersection(Node, Node, Node,...)\n"
				+ "Difference(Node, Node, Node,...)\n \n"
				+ "Example: Difference(Rotate(Square(50),45),Triangle(45))\n"
				+ "Produces the Difference of a Square that has been rotated 45 degrees and a Triangle.";
		
		TextArea textArea = new TextArea(helpText, 0, 0, TextArea.SCROLLBARS_NONE);
		textArea.setEditable(false);
		textArea.setBounds(spacingX, 2*spacingY, this.getWidth() - 2*spacingX, this.getHeight() - (4*spacingY + buttonY));
		add(textArea);
		
		Button btnClose=new Button();
		btnClose.setLabel("Close");
		btnClose.setBounds(this.getWidth()/2 - buttonX/2,this.getHeight() - (spacingY + buttonY), buttonX, buttonY);
		add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e){
				dispose();}
		}
				);
	}
}
