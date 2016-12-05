package fr.univ.angrybirds.elements;

import java.awt.Graphics;

import fr.univ.angrybirds.utils.Point;

public abstract class Element {
	
	protected Point pos;
	String picName;
	
	
	public Element(){}
	
	public Element(Point pos, String picName){
		this.pos = pos;
		this.picName = picName;
	}

	// Begin getters and setters
	public Point getPos() {
		return pos;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}
	// End getters and setters
	
	public abstract Graphics build(Graphics graphic);
	
}
