package fr.univ.angrybirds.game;

import fr.univ.angrybirds.elements.Bird;
import fr.univ.angrybirds.elements.Element;
import fr.univ.angrybirds.elements.Pig;
import fr.univ.angrybirds.utils.Point;

public class LevelBuilder {

	private Point scorePos =  new Point(20, 20); // default values
	private Point messagePos = new Point(300, 100); // default values
	private int height = 800;
	private int width = 600;
	
	
	public LevelBuilder(){}
	

	public Level createEasy(){
		Level easy = new Level(height, width, scorePos, messagePos);
		Point birdPos = new Point(100, 400);
		Point pigPos = new Point(Math.random() * 500 + 200, 480);
		Point pigPos2 = new Point(320, 480);
		
		Element bird = new Bird(birdPos, 0, 0, "Angry_Birds.png", 50, 50);
		Element pig  = new Pig(pigPos, "106596436.png", 50, 50);
		Element pig2  = new Pig(pigPos2, "giphy.gif", 50, 50);
		
		easy.addElement(bird);
		easy.addElement(pig);
		easy.addElement(pig2);
		
		easy.setBackground("fond.png");
		easy.setMessageText("L'oiseau prend son envole");
		easy.setScoreText("0");
		
		return easy;
	}
	
	
	
	public Level createMeduim(){
		Level easy = new Level(height, width, scorePos, messagePos);
		
		// TODO 
		
		return easy;
	}
	
	
	private Level createHard(){
		Level easy = new Level(height, width, scorePos, messagePos);
		
		//TODO
		
		return easy;
	}
	
}
