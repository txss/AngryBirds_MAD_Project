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
	public Bird(Point position, double velocityX, double velocityY, String picName){
		super(position, picName);
		this.startPoint = position;
		this.velocityX 	= velocityX;
		this.velocityY 	= velocityY;
	}

	public Bird() {}

	public Point getStartPoint (){
		return startPoint;
	}

	public void setStartPoint(Point startPoint){
		this.startPoint = startPoint;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	// End Getters and Setters

	
	@Override
	public Graphics build(Graphics graphic) {
		graphic.drawImage(Toolkit.getDefaultToolkit().getImage(picName), (int) startPoint.getX() - 20, (int) startPoint.getY() - 60, 50, 50, null);
		return graphic;
	}


}
