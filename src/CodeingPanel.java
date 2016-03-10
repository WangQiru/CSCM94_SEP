import java.awt.Button;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.TextArea;

public class CodeingPanel extends Panel {

	public CodeingPanel(){
		setLayout(null);
		setBounds(50, 50, 380, 480);

		TextArea textArea = new TextArea("This is an AWT textArea");

		textArea.setBounds(30,30,350,330);
		add(textArea);
	}
	public void paint( Graphics g ) {
		//		g.setColor( Color.black );
		//		g.drawRect( 0, 0, 375, 475);
	}
}
