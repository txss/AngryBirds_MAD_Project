package fr.univ.angrybirds.elements;


public class Bird extends Element {

	private double 	velocityX = 0;
	private double 	velocityY = 0;
	private boolean	isStopped = false;

	public Bird() { super(); }

	/* Getters */
	public double  getVelocityX() 	{ return velocityX; }
	public double  getVelocityY() 	{ return velocityY; }
	public boolean isStopped() 		{ return isStopped; }

	/* Setters */
	public void setVelocityX(double velocityX)  { this.velocityX = velocityX; }
	public void setVelocityY(double velocityY)  { this.velocityY = velocityY; }
	public void setStopped	(boolean isStopped) { this.isStopped = isStopped; }

}//Bird