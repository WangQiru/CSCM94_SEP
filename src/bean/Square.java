package bean;

import java.awt.Polygon;
import java.awt.geom.Area;

public class Square extends Shape {

	public Square(double radius, String color){
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
		return "Square(" + this.radius + "," + this.color + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Area draw() {
		int[] xCoords = {(int) -this.radius, (int) this.radius, (int) this.radius, (int) -this.radius};
		int[] yCoords = {(int) this.radius, (int) this.radius, (int) -this.radius, (int) -this.radius};
		Polygon shape = new Polygon(xCoords, yCoords, 4);
		return new Area(shape);
	}
}
