package bean;
/**
 * 	@class Shape
 *	This Shape class serves as an implementation of Node.
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */
public abstract class Shape implements Node {
	
	public double radius;

	public abstract boolean setRadius(double radius);
	
	public abstract double getRadius();
}
