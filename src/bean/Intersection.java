package bean;

import java.awt.geom.Area;
import java.util.List;

public class Intersection extends Mix {
	public Intersection(List<Node> inputNodes){
		this.inputNodes = inputNodes;
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
		String nodeList = inputNodes.get(0).print();
		for (int i = 1; i < inputNodes.size(); ++i){
			nodeList = nodeList.concat("," + inputNodes.get(i).print());
		}
		return "Intersection(" + nodeList + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Area draw() {
		Area intersection = new Area(this.inputNodes.get(0).draw());
		for (int i = 0; i < this.inputNodes.size(); ++i){
			intersection.intersect(this.inputNodes.get(i).draw());
		}
		return intersection;
	}

}
