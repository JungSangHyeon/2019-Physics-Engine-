package Shape;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class SSNCircularSaw{

	private static int nSpikes = 10, smallFactor =2;
	private static double evenAngleFactor = 0, oddAngleFactor =1;
	private static double Spikiness = 0.5;
	
	public static void setEvenAngleFactor(double evenAngleFactor) {SSNCircularSaw.evenAngleFactor = evenAngleFactor;}
	public static void setOddAngleFactor(double oddAngleFactor) {SSNCircularSaw.oddAngleFactor = oddAngleFactor;}
	public static void setNSpike(int nSpikes) {SSNCircularSaw.nSpikes=nSpikes;}
	public static void setSpikiness(double Spikiness) {SSNCircularSaw.Spikiness=Spikiness;}

	public static Shape getShape(Point2D.Double p1, Point2D.Double p2) {
		return makeShape(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y));
	}
	
	public static Shape getShape(double x, double y, double w, double h) {
		return makeShape(x, y, w, h);
	}
	
	public static Shape makeShape(double x, double y, double w, double h) {
		double ctrX = x + w/2, ctrY = y + h/ 2;
		int nPoints = nSpikes * 2 + 1;
		double xPoint[] = new double[nPoints];
		double yPoint[] = new double[nPoints];

		for (int i = 0; i < nPoints; i++) {
			double wRadius = (i % 2 == 0) ? w : (w * Spikiness);
			double hRadius = (i % 2 == 0) ? h : (h * Spikiness);
			double diffAngle = (2 * 360.0) / (2 * nSpikes) - (1 * 360.0) / (2 * nSpikes);
			double angle =  (i % 2 == 0) ? (i * 360.0) / (2 * nSpikes)+diffAngle*evenAngleFactor : (i * 360.0) / (2 * nSpikes) +diffAngle*oddAngleFactor;
			xPoint[i] = ctrX + wRadius * Math.cos(Math.toRadians(angle - 90))/smallFactor;
			yPoint[i] = ctrY + hRadius * Math.sin(Math.toRadians(angle - 90))/smallFactor;
		}

		GeneralPath path = new GeneralPath();
		path.moveTo(xPoint[0], yPoint[0]);
		for(int i=1; i<nPoints; i++) {path.lineTo(xPoint[i], yPoint[i]);}
		path.closePath();
		return path;
	}
}
