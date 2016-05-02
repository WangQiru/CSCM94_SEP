package bean;
/**
 * 	@class Transform
 *	This Transform class serves as an implementation of Node.
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */

public abstract class Transform implements Node {
	
	public Node inputNode;

	public int repeat;
	
	public abstract Node getInputNode();

}
