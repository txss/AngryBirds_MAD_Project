package fr.univ.angry.birds.personnages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class PicsBirds implements IBird{

	private double posX;
	private double posY;
	private int startPosX;
	private int startPosY;
	private double velocityX;
	private double velocityY;
	private String picName;
	
	
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
		this.picName		= picURL;
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

	public void setStartPosX(int startPosX) {
		this.startPosX = startPosX;
	}

	public double getStartPosY() {
		return startPosY;
	}

	public void setStartPosY(int startPosY) {
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

	public String getPicName() {
		return picName;
	}

	public void setPicURL(String picURL) {
		this.picName = picURL;
	}
	// End Getters and Setters

	
	@Override
	public Graphics build(Graphics graphic, int startPosX, int startPosY) {
		this.startPosX = startPosX;
		this.startPosY = startPosY;
		
		graphic.drawImage(Toolkit.getDefaultToolkit().getImage(picName), (int) startPosX - 20, (int) startPosY - 60, 50, 50, null);
		
		return graphic;
	}

	@Override
	public double getFloorValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
