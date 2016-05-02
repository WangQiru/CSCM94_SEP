package bean;

import java.awt.geom.Area;
import java.util.List;

public class Difference extends Mix {
	public Difference(List<Node> inputNodes){
		this.inputNodes = inputNodes;
	}


	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public String print() {
		String nodeList = inputNodes.get(0).print();
		for (int i = 1; i < inputNodes.size(); ++i){
			nodeList = nodeList.concat("," + inputNodes.get(i).print());
		}
		return "Difference(" + nodeList + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Area draw() {
		Area difference = new Area(this.inputNodes.get(0).draw());
		for (int i = 1; i < this.inputNodes.size(); ++i){
			difference.subtract(this.inputNodes.get(i).draw());
		}
		return difference;
	}

}
