package bean;

import java.awt.geom.Area;
import java.util.ArrayList;

public class Difference extends Mix {
	public Difference(ArrayList<Node> inputNodes){
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
	public boolean drawPixel(double x, double y) {
		boolean subtractedNodes = false;
		for (int i = 1; i < this.inputNodes.size(); ++i){
			subtractedNodes = this.inputNodes.get(i).drawPixel(x, y);
		}
		return (this.inputNodes.get(0).drawPixel(x, y) && !subtractedNodes);
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
