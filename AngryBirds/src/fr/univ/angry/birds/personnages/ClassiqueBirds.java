package fr.univ.angry.birds.personnages;

import java.awt.Color;
import java.awt.Graphics;

public class ClassiqueBirds implements IBird{

	private double posX;
	private double posY;
	private double startPosX;
	private double startPosY;
	private double velocityX;
	private double velocityY;
	private Color color;
	
	
	/**
	 * Default constructor
	 */
	public ClassiqueBirds(){}
	
	
	/**
	 * Constructor with params
	 * @param posX X position of the bird
	 * @param posY Y position of the bird
	 * @param velocityX X velocity of the bird
	 * @param velocityY Y velocity of the bird
	 */
	public ClassiqueBirds(double posX, double posY, double velocityX, double velocityY, Color color){
		this.posX 		= posX;
		this.posY 		= posY;
		this.velocityX 	= velocityX;
		this.velocityY 	= velocityY;
		this.color		= color;
	}
	
	
	// Begin Getters and Setters
	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}
	
	public Color getColor(){
		return color;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	// End Getters and Setters
	
	
	@Override
	public Graphics build(Graphics graphic, int startPosX, int startPosY) {
		this.startPosX = startPosX;
		this.startPosY = startPosY;
		graphic.setColor(Color.RED);
        graphic.fillOval((int) posX - startPosX, (int) posY - startPosY, 40, 40);
		return graphic;
	}


	@Override
	public double getFloorValue() {
		return startPosY + 45;
	}

}
