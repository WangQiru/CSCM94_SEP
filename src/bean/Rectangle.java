package bean;

import java.awt.Polygon;
import java.awt.geom.Area;

public class Rectangle extends Shape {
	
	public double yRadius;
	
	public Rectangle(double radius, double yRadius) {
		super();
		this.radius = radius;
		this.yRadius = yRadius;
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
		return "Rectangle(" + this.radius + "," + this.yRadius + ")";
	}


	@Override
	public boolean drawPixel(double x, double y) {
		if (x >= -this.radius && x < this.radius && y >= -this.yRadius && y < this.yRadius){
			return true;
		}
		return false;
		
	}


	@Override
	public Area draw() {
		int[] xCoords = {(int) -this.radius, (int) this.radius, (int) this.radius, (int) -this.radius};
		int[] yCoords = {(int) this.yRadius, (int) this.yRadius, (int) -this.yRadius, (int) -this.yRadius};
		Polygon shape = new Polygon(xCoords, yCoords, 4);
		return new Area(shape);
	}
}
