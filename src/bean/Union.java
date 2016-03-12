package bean;

import java.awt.geom.Area;
import java.util.List;

public class Union extends Mix {
	public Union(List<Node> inputNodes){
		this.inputNodes = inputNodes;
	}


	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public String print() {
		String nodeList = this.inputNodes.get(0).print();
		for (int i = 1; i < this.inputNodes.size(); ++i){
			nodeList = nodeList.concat("," + this.inputNodes.get(i).print());
		}
		return "Union(" + nodeList + ")";
	}


	@Override
	public void drawPixel(int x, int y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Area draw() {
		Area union = new Area(this.inputNodes.get(0).draw());
		for (int i = 0; i < this.inputNodes.size(); ++i){
			union.add(this.inputNodes.get(i).draw());
		}
		return union;
	}
}
