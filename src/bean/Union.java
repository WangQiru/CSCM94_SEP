package bean;

import java.util.List;

public class Union extends Mix {
	public Union(List<Node> inputNodes){
		// Union implementation here
	}


	@Override
	public String getNodeType() {
		// TODO Auto-generated method stub
		return "Union";
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
		return "Union(" + nodeList + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
