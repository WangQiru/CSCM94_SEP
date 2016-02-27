package bean;

public class Rotate extends Transform {
	public double angle;

	public Rotate(Node inputNode, double angle){
		this.inputNode=inputNode;
		this.angle=angle;
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

}
