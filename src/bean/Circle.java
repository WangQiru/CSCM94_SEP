package bean;

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
	public String getNodeType() {
		// TODO Auto-generated method stub
		return "Circle";
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
