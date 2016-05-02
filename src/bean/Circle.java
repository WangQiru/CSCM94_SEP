package bean;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Circle extends Shape {
	
	public Circle(double radius, String color){
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
		return "Circle(" + this.radius + "," + this.color + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Area draw() {
		Ellipse2D.Double shape = new Ellipse2D.Double(-this.radius,-this.radius,2*this.radius,2*this.radius);
		return new Area(shape);
	}

}
