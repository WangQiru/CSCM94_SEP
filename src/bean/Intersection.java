package bean;

import java.util.List;

public class Intersection extends Mix {
	public Intersection(List<Node> inputNodes){
		// Intersection implementation here
	}


	@Override
	public String getNodeType() {
		// TODO Auto-generated method stub
		return "Intersection";
	}


	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public String print() {
		String nodeList = inputNodes.get(1).print();
		for (int i = 1; i < inputNodes.size(); ++i){
			nodeList.concat("," + inputNodes.get(i).print());
		}
		return "Intersection(" + nodeList + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
