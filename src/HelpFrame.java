import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HelpFrame extends Frame {
	public HelpFrame(){
		setSize(430,420);
		setVisible(true);
		setLayout(null);
		setTitle("Help");
		setResizable(false);
		int spacingX = 20;
		int spacingY = 20;
		int buttonX = 80;
		int buttonY = 30;
		
		String helpText = "Syntax for inputting instructions:\n"
				+ "SHAPES\n"
				+ "Square(radius, colour)\n"
				+ "Rectangle(X radius, Y radius, colour)\n"
				+ "Circle(radius, colour)\n"
				+ "Triangle(radius, colour)\n \n"
				+ "TRANSFORMS\n"
				+ "Rotate(Node, angle) - Rotation is anticlockwise\n"
				+ "Translate(Node, X distance, Y distance)\n"
				+ "Scale(Node, factor) OR Scale(Node, X factor, Y factor)\n \n"
				+ "MIXES\n"
				+ "Union(Node, Node, Node,...)\n"
				+ "Intersection(Node, Node, Node,...)\n"
				+ "Difference(Node, Node, Node,...)\n \n"
				+ "Example: Difference(Rotate(Square(20,Blue),45),Triangle(15,Blue))\n"
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
