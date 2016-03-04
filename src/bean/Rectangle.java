package bean;

public class Rectangle extends Shape {
	
	public double length;
	
	public Rectangle(double height, double length, String color) {
		super();
		this.radius = height;
		this.length = length;
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
	public void print() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
