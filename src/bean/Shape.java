package bean;

public abstract class Shape implements Node {
	
	public String nodeType;
	public String color;
	public double radius;
	public int noOfSides;
	
	
	public abstract boolean setNoOfSides(int noOfSides);

	public abstract boolean setColor(String color);

	public abstract boolean setRadius(double radius);
	
	public abstract double getRadius();
}
