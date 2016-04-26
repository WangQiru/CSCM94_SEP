package bean;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
/**
 * 	@class Scale
 *	This Scale class serves as an object.
 *	Is inherited from Transform.
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */

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
		Area initialArea = new Area(this.inputNode.draw());
		Area scaledArea = new Area();
		for (int i = 0; i < this.repeat; ++i){
			AffineTransform scale = AffineTransform.getScaleInstance(this.factorX, this.factorY);
			initialArea.transform(scale);
			scaledArea.add(initialArea);
		}
		return scaledArea;
	}

}
