package fr.univ.angry.birds.personnages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class PicsBirds implements IBird{

	private double posX;
	private double posY;
	private double startPosX;
	private double startPosY;
	private double velocityX;
	private double velocityY;
	private String picURL;
	
	
	/**
	 * Default constructor
	 */
	public PicsBirds(){}
	
	
	/**
	 * Constructor with params
	 * @param posX X position of the bird
	 * @param posY Y position of the bird
	 * @param velocityX X velocity of the bird
	 * @param velocityY Y velocity of the bird
	 */
	public PicsBirds(double posX, double posY, double velocityX, double velocityY, String picURL){
		this.posX 		= posX;
		this.posY 		= posY;
		this.velocityX 	= velocityX;
		this.velocityY 	= velocityY;
		this.picURL		= picURL;
	}
	
	
	// Begin Getters and Setters
	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getStartPosX() {
		return startPosX;
	}

	public void setStartPosX(double startPosX) {
		this.startPosX = startPosX;
	}

	public double getStartPosY() {
		return startPosY;
	}

	public void setStartPosY(double startPosY) {
		this.startPosY = startPosY;
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

	public String getPicURL() {
		return picURL;
	}

	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}
	// End Getters and Setters
	

	
	@Override
	public Graphics build(Graphics graphic, int startPosX, int StartPosY) {
		this.startPosX = startPosX;
		this.startPosY = startPosY;
//		graphic.setColor(Color.RED);
//        graphic.fillOval((int) posX - startPosX, (int) posY - startPosY, 40, 40);
//        graphic.drawImage(img, x, y, null);
		return graphic;
	}

	@Override
	public double getFloorValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
