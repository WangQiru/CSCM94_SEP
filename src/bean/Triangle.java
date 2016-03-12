package bean;

import java.awt.Polygon;
import java.awt.geom.Area;

public class Triangle extends Shape{

	public Triangle(double radius, String color){
		this.radius=radius;
		this.color=color;
	}
	
	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
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
		return "Triangle(" + this.radius + "," + this.color + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Area draw() {
		int[] xCoords = {(int) (-Math.sqrt(3)*this.radius/2), 0, (int) (Math.sqrt(3)*this.radius/2)};
		int[] yCoords = {(int) -this.radius/2, (int) this.radius, (int)-this.radius/2};
		Polygon shape = new Polygon(xCoords, yCoords, 3);
		return new Area(shape);
	}

}
