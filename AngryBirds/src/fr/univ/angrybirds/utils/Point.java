package fr.univ.angrybirds.utils;

public class Point {

	private double x;
	private double y;
	
	
	public Point() {}
	
	public Point (double x, double y){
		this.x = x;
		this.y = y;
	}

	// Begin getters and setters
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	// End getters and setters
	
	public static double getDistance(Point p1, Point p2){
		double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
	}
}
