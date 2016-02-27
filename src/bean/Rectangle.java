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
	public void setNoOfSides(int noOfSides) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setColor(String color) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setRadius(double radius) {
		// TODO Auto-generated method stub
		
	}
}
