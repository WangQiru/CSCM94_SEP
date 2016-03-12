package bean;

public class Rotate extends Transform {
	public double angle;

	public Rotate(Node inputNode, double angle){
		this.inputNode = inputNode;
		this.angle = angle;
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
	


	@Override
	public String print() {
		return "Rotate(" + this.inputNode.print() + "," + this.angle + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
