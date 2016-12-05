package fr.univ.angrybirds.elements;

import java.awt.Graphics;
import java.awt.Toolkit;

import fr.univ.angrybirds.utils.Point;

public class Bird extends Element{

	private Point startPoint;
	private double velocityX;
	private double velocityY;
	
	
	/**
	 * Constructor with params
	 * @param posX X position of the bird
	 * @param posY Y position of the bird
	 * @param velocityX X velocity of the bird
	 * @param velocityY Y velocity of the bird
	 */
	public Bird(Point position, String picName){
		super(position, picName);
		this.startPoint = position;
		this.velocityX 	= 0;
		this.velocityY 	= 0;
	}

	public Bird() {}

	// Begin getters and setters
	public Point getStartPoint (){
		return startPoint;
	}
	
	public double getVelocityX() {
		return velocityX;
	}
	
	public double getVelocityY() {
		return velocityY;
	}

	public void setStartPoint(Point startPoint){
		this.startPoint = startPoint;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}


	// End Getters and Setters
	
	
	@Override
	public Graphics build(Graphics graphic) {
		graphic.drawImage(Toolkit.getDefaultToolkit().getImage(picName), (int) startPoint.getX() - super.getHeight()/2, (int) startPoint.getY() - super.getWidth()/2, super.getHeight(), super.getWidth(), null);
		return graphic;
	}


}
