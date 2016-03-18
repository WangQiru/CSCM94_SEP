package bean;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class Rotate extends Transform {
	public double angle;

	public Rotate(Node inputNode, double angle){
		this.inputNode = inputNode;
		this.angle = Math.toRadians(angle);
		this.repeat = 1;
	}
	
	public Rotate(Node inputNode, double angle, int repeat){
		this.inputNode = inputNode;
		this.angle = Math.toRadians(angle);
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
			return "Rotate(" + this.inputNode.print() + "," + Math.toDegrees(this.angle) + ")";
		}
		else {
			return "RotateN(" + this.inputNode.print() + "," + Math.toDegrees(this.angle) + "," + this.repeat + ")";
		}
	}

	@Override
	public boolean drawPixel(double x, double y) {
		boolean inShape = false;
		for (int i = 0; i < this.repeat; ++i){
			inShape = inShape || (this.inputNode.drawPixel(x*Math.cos((i + 1)*this.angle) + y*Math.sin((i + 1)*this.angle), -x*Math.sin((i + 1)*this.angle) + y*Math.cos((i + 1)*this.angle)));
		}
		return inShape;
	}

	@Override
	public Area draw() {
		AffineTransform rotate = AffineTransform.getRotateInstance(this.angle);
		Area rotatedArea = new Area(this.inputNode.draw());
		rotatedArea.transform(rotate);
		return rotatedArea;
	}

}
