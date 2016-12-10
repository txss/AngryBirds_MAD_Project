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
		Point pigPos3 = new Point(600, 480);
		
		Element bird = new Bird(birdPos, "img/red.png");
		bird.setValue(10);
		Element pig  = new Pig(pigPos, "img/pig_1.png");
		Element pig2  = new Pig(pigPos2, "img/pig_1.png");
		Element pig3 = new Pig(pigPos3, "img/pig_1.png");

		easy.addElement(bird);
		easy.addElement(pig);
		easy.addElement(pig2);
		easy.addElement(pig3);
		
		easy.setBackground("img/fond.png");
		easy.setMessageText("La partie commence !");
		easy.setScoreText("0");
		
		return easy;
	}
	
	
	
	public Level createMedium(){
		Level medium = new Level(height, width, scorePos, messagePos);
		
		
		Element bird = new Bird(new Point(100, 400), "img/red.png");
		bird.setValue(10);
		Element birdy = new Bird(new Point(50,480), "img/red.png");
		Element pig   = new Pig(new Point(Math.random() * 500 + 200, 480), "img/pig_1.png");
		Element pig2  = new Pig(new Point(320, 480), "img/pig_1.png");
		Element pig3  = new Pig(new Point(600, 480), "img/pig_1.png");

		medium.addElement(bird);
		medium.addElement(birdy);
		medium.addElement(pig);
		medium.addElement(pig2);
		medium.addElement(pig3);
		
		medium.setBackground("img/fond.png");
		medium.setMessageText("La partie commence !");
		medium.setScoreText("0");
		
		return medium;
	}
	
	
	private Level createHard(){
		Level easy = new Level(height, width, scorePos, messagePos);
		
		//TODO
		
		return easy;
	}
	
}
