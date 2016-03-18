package bean;

public abstract class Shape implements Node {
	
	public double radius;

	public abstract boolean setRadius(double radius);
	
	public abstract double getRadius();
}
