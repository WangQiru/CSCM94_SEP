package bean;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
/**
 * 	@class Circle
 *	This Circle class serves as an object.
 *	Is inherited from Shape.
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */

public class Circle extends Shape {
	
	public Circle(double radius){
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
		return "Circle(" + this.radius + ")";
	}


	@Override
	public boolean drawPixel(double x, double y) {
		if ((x*x) + (y*y) < this.radius*this.radius){
			return true;
		}
		return false;
		
	}


	@Override
	public Area draw() {
		Ellipse2D.Double shape = new Ellipse2D.Double(-this.radius,-this.radius,2*this.radius,2*this.radius);
		return new Area(shape);
	}

}
