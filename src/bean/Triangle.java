package bean;

public class Triangle extends Shape{

	public Triangle(double radius, String color){
		this.radius=radius;
		this.color=color;
		setNoOfSides(3);
	}
	
	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getNodeType() {
		// TODO Auto-generated method stub
		return "Triangle";
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
