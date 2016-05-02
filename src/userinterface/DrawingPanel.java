package userinterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

import bean.Node;

/**
 * 	@class DrawingPanel
 *	This DrawingPanel class serves as a panel of DrawingFrame.
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */

@SuppressWarnings("serial")
public class DrawingPanel extends Panel {

	public DrawingPanel(){
	}

	//Method for drawing an Area
	public void drawArea(Node rootNode, Color colour){
		Graphics2D canvasGraphics = (Graphics2D)this.getGraphics();

		//Drawing the axes
		drawAxes();

		//Setting the origin to be in the middle of the canvas
		canvasGraphics.translate(this.getWidth()/2, this.getHeight()/2);

		//Flipping the coordinates vertically so that y increases as you go up, not down
		AffineTransform flipVertical = AffineTransform.getScaleInstance(1, -1);
		canvasGraphics.transform(flipVertical);

		canvasGraphics.setColor(colour);
		Area finalArea = rootNode.draw();
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
		axesGraphics.setColor(Color.BLACK);
		paint(axesGraphics);
	}

	//Method for drawing a BufferedImage
	public void drawPixels(Node rootNode, Color colour) {
		//Creating an image that can be drawn pixel by pixel that is the size of the canvas
		BufferedImage pixelCanvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < getHeight(); ++i){
			for (int j = 0; j < getWidth(); ++j){
				//Checking if the current pixel should be drawn. Because the origin of pixelCanvas is in the top left
				//corner, pixels are shifted by half the canvas width and height, and the y values are multiplied
				//by -1 to flip the image vertically
				if (rootNode.drawPixel(j - getWidth()/2, - (i - getHeight()/2))){
					pixelCanvas.setRGB(j, i, colour.getRGB());
				}
				else{
					//If current pixel should not be drawn, draw a transparent pixel
					pixelCanvas.setRGB(j, i, 0);
				}
			}
		}
		Graphics2D pixelGraphics = (Graphics2D)this.getGraphics();
		drawAxes();
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
