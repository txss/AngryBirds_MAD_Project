package fr.univ.angrybirds.elements;

import java.awt.Graphics;
import java.awt.Toolkit;

import fr.univ.angrybirds.utils.Point;

public class Pig extends Element{

	private Point startPoint;
	
	
	public Pig(Point position, String picName, int picHeight, int picWidth){
		super(position, picName, 50, 50);
		this.startPoint = position;
	}

	public Pig(Point position, String picName){
		super(position, picName);
		this.startPoint = position;
	}
	
	public Pig() {}

	
	// Begin getters and setters
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	//End getters and setters

	public Graphics build(Graphics graphic) {
		graphic.drawImage(Toolkit.getDefaultToolkit().getImage(picName), (int) startPoint.getX() - super.getWidth()/2, (int) startPoint.getY() - super.getHeight()/2, super.getHeight(), super.getWidth(), null);
		return graphic;
	}	
	
}
