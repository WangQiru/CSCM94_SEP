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
		setSize(480,510);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setBackground(new Color(225,225,225));
		int spacingX = 20;
		int spacingY = 20;
		int buttonX = 80;
		int buttonY = 30;
		
		String helpText = "Syntax for inputting instructions:\n"
				+ "SHAPES\n"
				+ "Square(radius)\n"
				+ "Rectangle(X radius, Y radius)\n"
				+ "Circle(radius)\n"
				+ "Triangle(radius)\n \n"
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
