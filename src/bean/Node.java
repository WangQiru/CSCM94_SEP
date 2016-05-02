package bean;
import java.awt.geom.Area;
/**
 * 	@class Node
 *	This Node class serves as an interface.
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */

public interface Node{
	public boolean deleteNode();

	public String print();

	public Area draw();

	public boolean drawPixel(double x, double y);
}
