package bean;

public class Rotate extends Transform {
	public double angle;

	public Rotate(Node inputNode, double angle){
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
		return "Rotate(" + inputNode.print() + "," + this.angle + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
