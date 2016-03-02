package bean;

import java.util.List;

public abstract class Mix implements Node {
	
	public List<Node> inputNodes;

	@Override
	public String getNodeType() {
		// TODO Auto-generated method stub
		return "Mix";
	}

}
