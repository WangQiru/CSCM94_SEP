package bean;

public abstract class Transform implements Node {
	
	public Node inputNode;

	public int repeat;
	
	public abstract void repeat();
	
	public abstract Node getInputNode();

	@Override
	public String getNodeType() {
		// TODO Auto-generated method stub
		return "Transform";
	}

}
