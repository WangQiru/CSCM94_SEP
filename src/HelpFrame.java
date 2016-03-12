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
		
		String helpText = "Syntax for inputting instructions:\n"
				+ "SHAPES\n"
				+ "Square(radius, colour)\n"
				+ "Rectangle(width, height, colour)\n"
				+ "Circle(radius, colour)\n"
				+ "Triangle(radius, colour)\n \n"
				+ "TRANSFORMS\n"
				+ "Rotate(Node, angle)\n"
				+ "Translate(Node, X ammount, Y ammount)\n"
				+ "Scale(Node, ammount) OR Scale(Node, X ammount, Y ammount)\n \n"
				+ "MIXES\n"
				+ "Union(Node, Node, Node,...)\n"
				+ "Intersection(Node, Node, Node,...)\n"
				+ "Difference(Node, Node, Node,...)\n \n"
				+ "Example: Union(Rotate(Square(20,Blue),45),Triangle(15,Blue))\n"
				+ "Produces the Union of a Square that has been rotated 45 degrees and a Triangle.";
		
		TextArea textArea = new TextArea(helpText, 0, 0, TextArea.SCROLLBARS_NONE);
		textArea.setEditable(false);
		textArea.setBounds(20,40,390,310);
		add(textArea);
		
		Button btnClose=new Button();
		btnClose.setLabel("Close");
		btnClose.setBounds(175,360,80,30);
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
