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
		//Create an empty Path2D
		Path2D path = new Path2D.Double();
		//For every double array in curveList
		for (int i = 0; i < this.curveList.size(); ++i){
			double[] args = this.curveList.get(i);
			//Create a cubic Bezier curve using the arguments in args[]
			CubicCurve2D curve = new CubicCurve2D.Double(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7]);
			//Add that curve to the existing path
			path.append(curve, true);
		}
		//Draw the path
		return new Area(path);
	}

	@Override
	public boolean drawPixel(double x, double y) {
		//Create an empty Path2D
		Path2D path = new Path2D.Double();
		//For every double array in curveList
		for (int i = 0; i < this.curveList.size(); ++i){
			double[] args = this.curveList.get(i);
			//Create a cubic Bezier curve using the arguments in args[]
			CubicCurve2D curve = new CubicCurve2D.Double(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7]);
			//Add that curve to the existing path
			path.append(curve, true);
		}
		//Check if the given pixel is contained by the path
		return path.contains(x, y);
	}
	
}