package bean;

public class Translate extends Transform {
	public double distanceX;
	public double distanceY;

	public Translate(Node inputNode, double distanceX, double distanceY){
		this.inputNode=inputNode;
		this.distanceX=distanceX;
		this.distanceY=distanceY;
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
		return "Translate(" + this.inputNode.print() + "," + this.distanceX + "," + this.distanceY + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
