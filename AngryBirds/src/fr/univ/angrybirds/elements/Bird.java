package fr.univ.angrybirds.elements;

import java.awt.Graphics;
import java.awt.Toolkit;

import fr.univ.angrybirds.utils.Point;

public class Bird extends Element{

	private Point startPoint;
	private double velocityX;
	private double velocityY;
	private int height;
	private int width;
	
	
	/**
	 * Constructor with params
	 * @param posX X position of the bird
	 * @param posY Y position of the bird
	 * @param velocityX X velocity of the bird
	 * @param velocityY Y velocity of the bird
	 */
	public Bird(Point position, double velocityX, double velocityY, String picName, int picHeigth, int picWidth){
		super(position, picName);
		this.startPoint = position;
		this.velocityX 	= velocityX;
		this.velocityY 	= velocityY;
		this.height = picHeigth;
		this.width = picWidth;
	}

	public Bird() {}

	// Begin getters and setters
	public Point getStartPoint (){
		return startPoint;
	}
	
	public double getVelocityX() {
		return velocityX;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
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

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	// End Getters and Setters
	
	
	@Override
	public Graphics build(Graphics graphic) {
		graphic.drawImage(Toolkit.getDefaultToolkit().getImage(picName), (int) startPoint.getX() - height/2, (int) startPoint.getY() - width/2, height, width, null);
		return graphic;
	}


}
