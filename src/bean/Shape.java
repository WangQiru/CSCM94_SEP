package bean;

public abstract class Shape implements Node {
	
	public String color;
	public double radius;

	public abstract boolean setColor(String color);

	public abstract boolean setRadius(double radius);
	
	public abstract double getRadius();
}
