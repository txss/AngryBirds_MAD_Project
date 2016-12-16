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
	
	/* Member Fonctions */
	public boolean isOnFloor (double floorPos) {
		return this.getPos().getY() > floorPos && this.velocityY > 0.001;
	}
	
	public boolean isOnSky (double skyPos) {
		return this.getPos().getY() - this.width/2 < skyPos && velocityY < -0.001;
	}
	
	public boolean isHitWalls (double leftWallPos, double rightWallPos) {
		return (this.getPos().getX() + this.width/2 > rightWallPos && velocityX > 0.001)
			|| (this.getPos().getX() - this.width/2 < leftWallPos && velocityX < -0.001);
	}
	
	public boolean isTooSlow (double minSpeed) {
		return velocityY < minSpeed && velocityY > 0.0;
	}
	
	
}//Bird