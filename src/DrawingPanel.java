import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class DrawingPanel extends Panel {

	public DrawingPanel(){
	}
	
	//Method for drawing an Area
	public void drawArea(Area finalArea){
		Graphics2D canvasGraphics = (Graphics2D)this.getGraphics();
		
		//Drawing the axes
		drawAxes();
		//Setting the origin to be in the middle of the canvas
		canvasGraphics.translate(this.getWidth()/2, this.getHeight()/2);
		
		//Flipping the coordinates vertically so that y increases as you go up, not down
		AffineTransform flipVertical = AffineTransform.getScaleInstance(1, -1);
		canvasGraphics.transform(flipVertical);

		canvasGraphics.setColor(Color.blue);
		canvasGraphics.fill(finalArea);
		paint(canvasGraphics);
	}
	
	public void drawAxes(){
		Graphics2D axesGraphics = (Graphics2D)this.getGraphics();
		//Clearing the canvas
		axesGraphics.clearRect(0, 0, getWidth(), getHeight());
		//Drawing horizontal and vertical lines
		axesGraphics.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
		axesGraphics.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
		axesGraphics.setColor(Color.black);
		paint(axesGraphics);
	}

	//Method for drawing a BufferedImage
	public void drawPixels(BufferedImage pixelCanvas) {
		Graphics2D pixelGraphics = (Graphics2D)this.getGraphics();
		drawAxes();
		pixelGraphics.setColor(Color.blue);
		pixelGraphics.drawImage(pixelCanvas, null, null);
		paint(pixelGraphics);
	}
	
	public void drawError(String error){
		Graphics2D errorGraphics = (Graphics2D)this.getGraphics();
		errorGraphics.clearRect(0, 0, getWidth(), getHeight());
	    Font font = new Font("Courier", Font.PLAIN, 40);		
	    errorGraphics.setColor(Color.red);
	    errorGraphics.setFont(font);
	    errorGraphics.drawString(error, 0, this.getHeight()/2);
		paint(errorGraphics);
		return;
		
	}
}
