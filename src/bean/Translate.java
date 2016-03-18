package bean;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class Translate extends Transform {
	public double distanceX;
	public double distanceY;

	public Translate(Node inputNode, double distanceX, double distanceY){
		this.inputNode=inputNode;
		this.distanceX=distanceX;
		this.distanceY=distanceY;
		this.repeat = 1;
	}

	public Translate(Node inputNode, double distanceX, double distanceY, int repeat){
		this.inputNode=inputNode;
		this.distanceX=distanceX;
		this.distanceY=distanceY;
		this.repeat = repeat;
	}
	
	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Node getInputNode() {
		return this.inputNode;
	}

	@Override
	public String print() {
		if (this.repeat == 1){
			return "Translate(" + this.inputNode.print() + "," + this.distanceX + "," + this.distanceY + ")";
		}
		else{
			return "TranslateN(" + this.inputNode.print() + "," + this.distanceX + "," + this.distanceY + "," + this.repeat + ")";
		}
	}

	@Override
	public boolean drawPixel(double x, double y) {
		boolean inShape = false;
		for (int i = 0; i < this.repeat; ++i){
			inShape = inShape || (this.inputNode.drawPixel(x - (i+1)*this.distanceX, y - (i+1)*this.distanceY));
		}
		return inShape;		
	}

	@Override
	public Area draw() {
		AffineTransform translate = AffineTransform.getTranslateInstance(this.distanceX, this.distanceY);
		Area translatedArea = new Area(this.inputNode.draw());
		translatedArea.transform(translate);
		return translatedArea;
	}

}
