import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

public class DrawingPanel extends Panel {

	public DrawingPanel(){
		setLayout(null);
		setBounds(50, 50, 380, 480);
	}

//	public void paint(Graphics g) {
//		g.setColor(Color.red);
//		Graphics2D g2d = (Graphics2D)g;
//		g2d.rotate(Math.toRadians(45));
//		g2d.setColor(Color.blue);
//
//		int xPoly[] = {150,215,132,135,140,125,100};
//		int yPoly[] = {150,110,115,125,150,137,200};
//		Polygon p=new Polygon(xPoly,yPoly,7);
//		// New polygon
//		Ellipse2D.Double e=new Ellipse2D.Double(200,100,40,170);
//		// New ellipse
//
//
//		Area a=new Area(p);
//		Area ae=new Area(e);
//		// Convert shapes into Areas
//		ae.subtract(a);
//		/*
//		  Area:
//		   add
//		   subtract
//		   intersect
//		   transform
//
//		 */
//
//
//		g2d.draw(ae);
//		//		g2d.fill(ae);
//
//
//
//	}

	
    public void paint( Graphics g ) {
        g.setColor( Color.black );
        g.drawRect( 0, 0, 375, 475);
    }
}
