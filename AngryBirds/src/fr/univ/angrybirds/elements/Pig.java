package fr.univ.angrybirds.elements;

import java.awt.Graphics;
import java.awt.Toolkit;

import fr.univ.angrybirds.utils.Point;

public class Pig extends Element{

	private Point startPoint;
	private int height;
	private int width;
	
	
	public Pig(Point position, String picName, int picHeight, int picWidth){
		super(position, picName);
		this.startPoint = position;
		this.height = picHeight;
		this.width = picWidth;
	}

	public Pig() {}

	
	// Begin getters and setters
	public Point getStartPoint() {
		return startPoint;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	//End getters and setters

	public Graphics build(Graphics graphic) {
		graphic.drawImage(Toolkit.getDefaultToolkit().getImage(picName), (int) startPoint.getX() - width/2, (int) startPoint.getY() - height/2, height, width, null);
		return graphic;
	}	
	
}
