package bean;

import java.awt.Polygon;
import java.awt.geom.Area;

public class Rectangle extends Shape {
	
	public double yRadius;
	
	public Rectangle(double radius, double yRadius, String color) {
		super();
		this.radius = radius;
		this.yRadius = yRadius;
		this.color = color;
	}


	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNodeType() {
		// TODO Auto-generated method stub
		return "Rectangle";
	}


	@Override
	public boolean setNoOfSides(int noOfSides) {
		return false;
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean setColor(String color) {
		return false;
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean setRadius(double radius) {
		// TODO Auto-generated method stub
		this.radius=radius;
		return false;
	}

	@Override
	public double getRadius() {
		// TODO Auto-generated method stub
		return this.radius;
	}
	


	@Override
	public String print() {
		return "Rectangle(" + this.radius + "," + this.yRadius + "," + this.color + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Area draw() {
		int[] xCoords = {(int) -this.radius, (int) this.radius, (int) this.radius, (int) -this.radius};
		int[] yCoords = {(int) this.yRadius, (int) this.yRadius, (int) -this.yRadius, (int) -this.yRadius};
		Polygon shape = new Polygon(xCoords, yCoords, 4);
		return new Area(shape);
	}
}
