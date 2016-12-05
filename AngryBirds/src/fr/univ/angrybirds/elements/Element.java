package fr.univ.angrybirds.elements;

import java.awt.Graphics;

import fr.univ.angrybirds.utils.Point;

public abstract class Element {
	
	protected Point pos;
	String picName;

	private double gravity;
	private int height;
	private int width;
	
	
	public Element(){}
	
	/**
	 * Construct basic element
	 * @param pos
	 * @param picName
	 */
	public Element(Point pos, String picName){
		this.pos = pos;
		this.picName = picName;
		this.gravity = 0.1; // default value
		this.height = 50;	// default value
		this.width = 50;	// default value
		
	}
	
	public Element(Point pos, String picName, double gravity){
		this.pos = pos;
		this.picName = picName;
		this.gravity = gravity;
		this.height = 50;	// default value
		this.width = 50;	// default value
	}

	public Element(Point pos, String picName, double gravity, int picHeigth, int picWidth){
		this.pos = pos;
		this.picName = picName;
		this.gravity = gravity;
		this.height = picHeigth;
		this.width = picWidth;
	}
	
	public Element(Point pos, String picName, int picHeigth, int picWidth){
		this.pos = pos;
		this.picName = picName;
		this.gravity = 0.1;
		this.height = picHeigth;
		this.width = picWidth;
	}
	
	// Begin getters and setters
	public Point getPos() {
		return pos;
	}

	public String getPicName() {
		return picName;
	}

	public double getGravity() {
		return gravity;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	// End getters and setters
	
	
	public abstract Graphics build(Graphics graphic);
	
}
