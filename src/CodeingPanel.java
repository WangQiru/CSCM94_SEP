import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

public class CodeingPanel extends Panel {

	public CodeingPanel(){
		setLayout(null);
		setBounds(50, 50, 380, 480);
	}
	public void paint( Graphics g ) {
		g.setColor( Color.black );
		g.drawRect( 0, 0, 375, 475);
	}
}
