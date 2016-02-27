package bean;

public abstract class Shape implements Node {
	
	public String nodeType;
	public String color;
	public double radius;
	public int noOfSides;
	
	
	public abstract void setNoOfSides(int noOfSides);


	public abstract void setColor(String color);

	public abstract void setRadius(double radius);
}
