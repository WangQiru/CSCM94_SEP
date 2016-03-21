package bean;

import java.awt.Polygon;
import java.awt.geom.Area;

public class Square extends Shape {

	public Square(double radius){
		this.radius=radius;
	}
	
	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean setRadius(double radius) {
		this.radius=radius;
		return false;
	}

	@Override
	public double getRadius() {
		return this.radius;
	}
	

	@Override
	public String print() {
		return "Square(" + this.radius + ")";
	}


	@Override
	public boolean drawPixel(double x, double y) {
		if (x >= -this.radius/Math.sqrt(2) && x < this.radius/Math.sqrt(2) && y > -this.radius/Math.sqrt(2) && y <= this.radius/Math.sqrt(2)){
			return true;
		}
		return false;
	}

	@Override
	public Area draw() {
		int[] xCoords = {(int) (-this.radius/Math.sqrt(2)), (int) (this.radius/Math.sqrt(2)),  (int) (this.radius/Math.sqrt(2)),  (int) (-this.radius/Math.sqrt(2))};
		int[] yCoords = {(int) (this.radius/Math.sqrt(2)), (int) (this.radius/Math.sqrt(2)),  (int) (-this.radius/Math.sqrt(2)),  (int) (-this.radius/Math.sqrt(2))};
		Polygon shape = new Polygon(xCoords, yCoords, 4);
		return new Area(shape);
	}
}
