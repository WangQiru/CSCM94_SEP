package util;
/**
 * 	@class CubicBezier
 *	This CubicBezier class .....
 *
 *  @author Donal Evans
 *  @author Qiru Wang
 */

import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;
import java.util.Arrays;

//A class for creating and manipulating cubic Bezier curves

public class CubicBezier{
	//The control points for the Bezier curve in the form {{x1, x2, x3, x4}, {y1, y2, y3, y4}}
	private double[][] controlPoints;
	
	public CubicBezier(double[][] controlPoints){
		this.setControlPoints(controlPoints);
	}
	
	//Constructor taking control points in the form {x1, y1, x2, y2, x3, y3, x4, y4}
	public CubicBezier(double[] controlPointList){
		double[][] controlPoints = new double[2][4];
		for (int i = 0; i < controlPoints[0].length; ++i){
			controlPoints[0][i] = controlPointList[2*i];
			controlPoints[1][i] = controlPointList[1 + 2*i];
		}
		this.setControlPoints(controlPoints);
	}
	
	//Converts the Bezier curve into a polygon with nEdges and returns the vertices as an ArrayList of double arrays
	//Currently naive, needs to implement a better algorithm
	public ArrayList<double[]> toPolygon(int nEdges){
		ArrayList<double[]> vertices = new ArrayList<double[]>();
		double stepSize = (double) 1/nEdges;
		for (int i = 0; i < nEdges + 1; ++i){
			vertices.add(this.getPositionAt(i * stepSize));
		}
		return vertices;
	}
	
	//Calculates the intersection points (x,y) between a line segment and a Bezier curve 
	//and outputs points (fx,fy) a small step forwards along the Bezier curve and points(bx, by)
	//a small step backwards along the curve in the form {{bx1, by1}, {fx1, fy1}, {bx2,...},...}
	//linePoints in the form x1, y1, x2, y2
	public ArrayList<double[]> getIntersect(double x1, double y1, double x2, double y2){
		//Calculating coefficients for line segment
		double a = x2 - x1;
		double b = y1 - y2;
		double c = y1*(x1 - x2) + x1*(y2 - y1);
		
		//Calculating coefficients for cubic Bezier curve
		double[][] bezierCoeffs = this.getCoeffs();
		
		double[] cubicCoeffs = new double[4];
		cubicCoeffs[0] = a*bezierCoeffs[1][0] + b*bezierCoeffs[0][0];		//t^3 terms
		cubicCoeffs[1] = a*bezierCoeffs[1][1] + b*bezierCoeffs[0][1];		//t^2 terms
		cubicCoeffs[2] = a*bezierCoeffs[1][2] + b*bezierCoeffs[0][2];		//t terms
		cubicCoeffs[3] = a*bezierCoeffs[1][3] + b*bezierCoeffs[0][3] + c;	//constant terms
		
		//Calculate the roots of the parametric equation
		double[] roots = this.getCubicRoots(cubicCoeffs);
		
		//ArrrayList to hold the final results
		ArrayList<double[]> intersections = new ArrayList<double[]>();
		
		for (int i = 0; i < roots.length; ++i){
			double t = roots[i];
			
			//Only consider valid roots 
			if (t != -1){
				//The (x,y) position of a point a little further forwards along the Bezier curve
				double[] pointPlus = this.getPositionAt(t + 0.001);
				//The (x,y) position of a point a little further backwards along the Bezier curve
				double[] pointMinus = this.getPositionAt(t - 0.001);
				//An array containing a line segment representing the local direction of the Bezier curve at the intersect
				intersections.add(pointMinus);
				intersections.add(pointPlus);
			}
		}
		return intersections;
	}
	//Calculates the coefficients of the cubic equations in t for the Bezier curve
	//output in the form ((cx1, cy1), (cx2, cy2), (cx3, cy3), (cx4, cy4))
	public double[][] getCoeffs(){
		double[][] coeffs = new double[2][4];
		//First do for the x values, then the y values
		for (int i = 0; i < 2; ++i){
			coeffs[i][0] = -this.controlPoints[i][0] + 3*this.controlPoints[i][1] - 3*this.controlPoints[1][2] + this.controlPoints[i][3];
			coeffs[i][1] = 3*this.controlPoints[i][0] - 6*this.controlPoints[i][1] + 3*this.controlPoints[1][2];
			coeffs[i][2] = -3*this.controlPoints[i][0] + 3*this.controlPoints[i][1];
			coeffs[i][3] = this.controlPoints[i][0];
		}
		return coeffs;
	}
	
	//Calculating the real roots of a cubic equation in t, returning -1 if root is out of bounds
	public double[] getCubicRoots(double[] cubicCoeffs){
//		//If the first coefficient is zero, solve the quadratic given by the remaining coefficients
//		if (cubicCoeffs[0] == 0){
//			double[] quadCoeffs = Arrays.copyOfRange(cubicCoeffs, 1, cubicCoeffs.length-1);
//			return getQuadraticRoots(quadCoeffs);
//		}
//		double[] roots = new double[3];
//		//Normalizing the cubic equation so that the first coefficient = 1 to allow easier solving
//		double a = cubicCoeffs[1]/cubicCoeffs[0];
//		double b = cubicCoeffs[2]/cubicCoeffs[0];
//		double c = cubicCoeffs[3]/cubicCoeffs[0];
//		
//		double q = (3*b - a*a)/9;
//		double r = (9*a*b - 27*c - 2*a*a*a)/54;
//		double d = q*q*q + r*r;
//		
//		//Duplicate roots or complex roots
//		if (d >= 0){
//			double s = Math.signum(r + Math.sqrt(d))*Math.pow(Math.abs(r + Math.sqrt(d)), 1/3);
//			double t = Math.signum(r - Math.sqrt(d))*Math.pow(Math.abs(r - Math.sqrt(d)), 1/3);
//			roots[0] = -a/3 + (s + t);
//			roots[1] = -a/3 + (s + t)/2;
//			roots[2] = roots[1];
//			double imaginary = Math.abs(Math.sqrt(3) * (s - t)/2);
//			
//			if (imaginary != 0){
//				roots[1] = -1;
//				roots[2] = -1;
//			}
//		}
//		//Three distinct real roots
//		else{
//			double theta = Math.acos(r/Math.sqrt(-q*q*q));
//			roots[0] = 2*Math.sqrt(-q)*Math.cos(theta/3) - a/3;
//			roots[1] = 2*Math.sqrt(-q)*Math.cos((theta + 2*Math.PI)/3) - a/3;
//			roots[2] = 2*Math.sqrt(-q)*Math.cos((theta + 4*Math.PI)/3) - a/3;
//		}

		//Reversing the order of the cubic coefficients as CubicCurve2D.solveCubic() takes them in the opposite
		//order to the order in which they're generated by getCoeffs()
		double[] eqn = new double[]{cubicCoeffs[3],cubicCoeffs[2],cubicCoeffs[1],cubicCoeffs[0]};
		//Calculate the number of roots and store any roots found back into eqn. Returns -1 if the equation is a constant
		int numOfRoots = CubicCurve2D.solveCubic(eqn);
		
		//If the equation is a constant, return an empty array
		if (numOfRoots == -1){
			return new double[]{};
		}
		//Create a new array with as many elements as there are roots and store any roots found in it. 
		//This is done to prevent empty elements in eqn being mistaken for valid roots with value = 0
		double[] roots = Arrays.copyOf(eqn, numOfRoots);
		//Discard roots with values of t outside the Bezier curve (0 <= t <= 1)
		for (int i = 0; i < roots.length; ++i){
			if (roots[i] < 0 || roots[i] > 1){
				roots[i] = -1;
			}
		}
		return roots;
	}
	
	//Calculating the roots of a quadratic equation in t, returning an empty array if roots are imaginary
	public double[] getQuadraticRoots (double[] quadCoeffs){
		if (quadCoeffs[0] == 0){
			double[] linearCoeffs = Arrays.copyOfRange(quadCoeffs, 1, quadCoeffs.length-1);
			return getLinearRoots(linearCoeffs);
		}
		//The discriminant of the quadratic function
		double discriminant = quadCoeffs[1]*quadCoeffs[1] - 4 * quadCoeffs[0]*quadCoeffs[2];
		//One real root
		if (discriminant == 0){
			double root = -quadCoeffs[1]/2*quadCoeffs[0];
			return new double[]{root};
		}
		//Two real roots
		else if (discriminant > 0){
			double sqrtD = Math.sqrt(discriminant);
			double root1 = (-quadCoeffs[1] - sqrtD)/2*quadCoeffs[0];
			double root2 = (-quadCoeffs[1] + sqrtD)/2*quadCoeffs[0];
			return new double[]{root1, root2};
		}
		//Complex roots
		else{
			return new double[]{};
		}
	}

	//Calculating the roots of a linear equation in t, returning an empty array if root is imaginary
	public double[] getLinearRoots (double[] linearCoeffs){
		double t = -linearCoeffs[1]/linearCoeffs[0];
		if (linearCoeffs[0] == 0){
			return new double[]{};
		}
		return new double[]{t};
	}
	
	//Calculates the (x,y) position of the point at position t on the Bezier curve
	//given by a*(1 - t)^3 + 3*b*(1 - t)^2*t + 3*c*(1 - t)*t^2 + d*t^3 where a,b,c,d are control points
	public double[] getPositionAt(double t){
		if (t == 0){
			//Return the start position
			return new double[] {this.controlPoints[0][0], this.controlPoints[1][0]};
		}
		if (t == 1){
			//Return the end position
			return new double[] {this.controlPoints[0][3], this.controlPoints[1][3]};
		}
		double[] coordinates = new double[2];
		for (int i = 0; i < coordinates.length; ++i){
			coordinates[i] = this.controlPoints[i][0]*Math.pow((1-t),3) + 3*this.controlPoints[i][1]*(1-t)*(1-t)*t 
							 + 3*this.controlPoints[i][2]*(1-t)*t*t + this.controlPoints[i][3]*t*t*t;
		}
		return coordinates;
	}

	public double[][] getControlPoints() {
		return controlPoints;
	}

	public void setControlPoints(double[][] controlPoints) {
		this.controlPoints = controlPoints;
	}
	
}