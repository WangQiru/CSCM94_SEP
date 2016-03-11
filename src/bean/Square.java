package bean;

public class Square extends Shape {

	public Square(double radius, String color){
		this.radius=radius;
		this.color=color;
		setNoOfSides(4);
	}
	
	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getNodeType() {
		// TODO Auto-generated method stub
		return "Square";
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
		return "Square(" + this.radius + "," + this.color + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
