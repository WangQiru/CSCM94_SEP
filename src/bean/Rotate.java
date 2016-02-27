package bean;

public class Rotate extends Transform {
	public double angle;

	public Rotate(Shape inputNode, double angle){
		inputNode.setRadius(angle);
	}

	
	@Override
	public String getNodeType() {
		// TODO Auto-generated method stub
		return "Translate";
	}


	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void repeat() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Node getInputNode() {
		// TODO Auto-generated method stub
		return this.inputNode;
	}

}
