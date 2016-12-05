package fr.univ.angrybirds.elements;

import java.awt.Graphics;
import java.awt.Toolkit;

import fr.univ.angrybirds.utils.Point;

public class Pig extends Element{

	private Point startPoint;
	
	public Pig(Point position, String picName){
		super(position, picName);
		this.startPoint = position;
	}

	public Pig() {}

	public Graphics build(Graphics graphic) {
		graphic.drawImage(Toolkit.getDefaultToolkit().getImage(picName), (int) startPoint.getX() - 20, (int) startPoint.getY() - 60, 50, 50, null);
		
		return graphic;
	}	
	
}
