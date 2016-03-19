package bean;

import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Arrays;

public class ClosedCurve implements Node{
	
	private ArrayList<double[]> curveList;
	
	public ClosedCurve(ArrayList<double[]> curveSegmentList){
		this.curveList = curveSegmentList;
	}


	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String print() {
		String curveList = Arrays.toString(this.curveList.get(0));
		for (int i = 1; i < this.curveList.size(); ++i){
			curveList = curveList.concat("," + Arrays.toString(this.curveList.get(i)));
		}
		//Converting the square brackets from Arrays.toString() into curved brackets
		curveList = curveList.replace("[", "(").replace("]", ")");
		return "ClosedCurve(" + curveList + ")";
	}

	@Override
	public Area draw() {
		double[] args = this.curveList.get(0);
		CubicCurve2D curve = new CubicCurve2D.Double(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7]);
		Path2D path = new Path2D.Double(curve);
		for (int i = 1; i < this.curveList.size(); ++i){
			args = this.curveList.get(i);
			curve.setCurve(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7]);
			path.append(curve, true);
		}
		path.closePath();
		return new Area(path);
	}

	@Override
	public boolean drawPixel(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}
	
}