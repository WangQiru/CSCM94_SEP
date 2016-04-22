package bean;

import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Arrays;

import util.CubicBezier;

public class ClosedCurve implements Node{

	private ArrayList<double[]> curvePoints;

	public ClosedCurve(ArrayList<double[]> curveSegmentList){
		this.curvePoints = curveSegmentList;
	}


	@Override
	public boolean deleteNode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String print() {
		String curveList = Arrays.toString(this.curvePoints.get(0));
		for (int i = 1; i < this.curvePoints.size(); ++i){
			curveList = curveList.concat("," + Arrays.toString(this.curvePoints.get(i)));
		}
		//Converting the square brackets from Arrays.toString() into curved brackets
		curveList = curveList.replace("[", "(").replace("]", ")");
		return "Curve(" + curveList + ")";
	}

	@Override
	public Area draw() {
		//Create an empty Path2D
		Path2D path = new Path2D.Double();
		//For every double array in curveList
		for (int i = 0; i < this.curvePoints.size(); ++i){
			double[] segment = this.curvePoints.get(i);
			//If the current segment is a Bezier curve, add the curve to the path
			if (segment.length == 8){
				//Create a cubic Bezier curve using the arguments in args[]
				CubicCurve2D curve = new CubicCurve2D.Double(segment[0],segment[1],segment[2],segment[3],segment[4],segment[5],segment[6],segment[7]);
				//Add that curve to the existing path
				path.append(curve, true);
			}
			//If the current segment is the first point in the curve, add a line to that point to the path
			else if (i == 0){
				path.moveTo(segment[0], segment[1]);
			}
			//If the current segment is a point, add a line to that point to the path
			else {
				path.lineTo(segment[0], segment[1]);
			}
		}
		//Draw the path
		return new Area(path);
	}

	@Override
	public boolean drawPixel(double x, double y) {		
		//First generate a list of vertices from the points
		ArrayList<double[]> vertexList = new ArrayList<double[]>();

		for (int i = 0; i < this.curvePoints.size(); ++i){
			double[] segment = this.curvePoints.get(i);
			//If segment is a Bezier curve, calculate appropriate line segments and add to vertexList
			if (segment.length == 8){
				//Create a new CubicBezier
				CubicBezier bezier = new CubicBezier(segment);
				//Calculate the line segments local to the intersection points
				ArrayList<double[]> bezierIntersects = bezier.getIntersect(x, y , x + 1, y );
				//If the ray intersects the bezier
				if (bezierIntersects.size() != 0){
					//Add the start vertex to the vertex list
					vertexList.add(new double[]{segment[0],segment[1]});
					//Add the intersect vertices to the list
					vertexList.addAll(bezierIntersects);
					//Add the end vertex to the vertex list
					vertexList.add(new double[]{segment[6],segment[7]});
				}
				else{
					//Add the start vertex to the vertex list
					vertexList.add(new double[]{segment[0],segment[1]});
					//Add the end vertex to the vertex list
					vertexList.add(new double[]{segment[6],segment[7]});
				}
			}
			//If the segment is a lone vertex, add it to the list
			else{
				vertexList.add(segment);
			}
		}

		//Close the curve by setting the final vertex to be equal to the first
		vertexList.add(vertexList.get(0));

		//Calculate winding number for a point wrt. the curve
		//This code adapted from http://geomalgorithms.com/a03-_inclusion.html
		int winding = 0;
		for (int i = 0; i < vertexList.size() - 1; ++i){
			double[] thisVertex = vertexList.get(i);
			double[] nextVertex = vertexList.get(i + 1);
			if (thisVertex[1] <= y){
				if (nextVertex[1] > y){
					if (isLeft(thisVertex, nextVertex, x, y) > 0){
						++winding;
					}
				}
			}
			else if (thisVertex[1] > y){
				if (nextVertex[1] <= y){
					if(isLeft(thisVertex, nextVertex, x, y) < 0){
						--winding;
					}
				}
			}
		}
		if (winding == 0){
			return false;
		}
		else{
			return true;
		}
	}

	//Tests if a point (x,y) is to the left of an infinite line passing through points[]
	//Returns >0 if the point is on the left, <0 if the point is on the right and 0 if the point is on the line
	private double isLeft(double[] thisVertex, double[] nextVertex, double x, double y){
		return ((thisVertex[0] - x) * (nextVertex[1] - y) - (nextVertex[0] - x)*(thisVertex[1] - y));
	}
}