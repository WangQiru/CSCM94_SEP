package bean;

public class Square extends Shape {

	public Square(double radius, String color, int noOfSides){
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
