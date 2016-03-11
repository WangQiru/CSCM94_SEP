package bean;

public class Scale extends Transform {
	public double factorX;
	public double factorY;

	public Scale(Node inputNode, double factorX, double factorY){
		this.inputNode=inputNode;
		this.factorX=factorX;
		this.factorY=factorY;
	}
	
	public Scale(Node inputNode, double factor){
		this.inputNode=inputNode;
		this.factorX=factor;
		this.factorY=factor;
	}

	
	@Override
	public String getNodeType() {
		// TODO Auto-generated method stub
		return "Scale";
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
	public void print() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
