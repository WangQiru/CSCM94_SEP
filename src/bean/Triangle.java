package bean;

import java.awt.Polygon;
import java.awt.geom.Area;

public class Triangle extends Shape{

	public Triangle(double radius){
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
		return "Triangle(" + this.radius + ")";
	}


	@Override
	public boolean drawPixel(double x, double y) {
		if((y < this.radius + Math.sqrt(3)*x) && (y > -this.radius/2) && (y < this.radius - Math.sqrt(3)*x)){
			return true;
		}
		return false;
	}

	@Override
	public Area draw() {
		int[] xCoords = {(int) (-Math.sqrt(3)*this.radius/2), 0, (int) (Math.sqrt(3)*this.radius/2)};
		int[] yCoords = {(int) -this.radius/2, (int) this.radius, (int)-this.radius/2};
		Polygon shape = new Polygon(xCoords, yCoords, 3);
		return new Area(shape);
	}

}
