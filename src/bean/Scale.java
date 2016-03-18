package bean;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class Scale extends Transform {
	public double factorX;
	public double factorY;

	public Scale(Node inputNode, double factorX, double factorY){
		this.inputNode=inputNode;
		this.factorX=factorX;
		this.factorY=factorY;
		this.repeat = 1;
	}

	public Scale(Node inputNode, double factorX, double factorY, int repeat){
		this.inputNode=inputNode;
		this.factorX=factorX;
		this.factorY=factorY;
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
		String output = "Scale(" + this.inputNode.print() + "," + this.factorX;
		if (this.factorX != this.factorY){
			output.concat("," + this.factorY);
		}
		if (this.repeat != 1){
			output.concat("," + this.repeat);
			output.replaceFirst("Scale", "ScaleN");
		}
		return output.concat(")");
	}

	@Override
	public boolean drawPixel(double x, double y) {
		boolean inShape = false;
		for (int i = 0; i < this.repeat; ++i){
			inShape = inShape || (this.inputNode.drawPixel(x/Math.pow(this.factorX,i+1), y/Math.pow(this.factorY,i+1)));
		}
		return inShape;
	}

	@Override
	public Area draw() {
		AffineTransform scale = AffineTransform.getScaleInstance(this.factorX, this.factorY);
		Area scaledArea = new Area(this.inputNode.draw());
		scaledArea.transform(scale);
		return scaledArea;
	}

}
