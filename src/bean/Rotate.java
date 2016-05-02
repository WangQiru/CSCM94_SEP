package bean;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class Rotate extends Transform {
	public double angle;

	public Rotate(Node inputNode, double angle){
		this.inputNode = inputNode;
		this.angle = angle;
	}

	
	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void repeat() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Node getInputNode() {
		// TODO Auto-generated method stub
		return this.inputNode;
	}
	


	@Override
	public String print() {
		return "Rotate(" + this.inputNode.print() + "," + this.angle + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Area draw() {
		//Might need to change to getRotateInstance(double angle, double anchorx, double anchory)
		//to rotate about the origin of the drawing area.
		AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(this.angle));
		Area rotatedArea = new Area(this.inputNode.draw());
		rotatedArea.transform(rotate);
		return rotatedArea;
	}

}
