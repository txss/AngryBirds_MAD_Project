package fr.univ.angrybirds.game;

import java.util.ArrayList;
import java.util.List;

import fr.univ.angrybirds.elements.Element;
import fr.univ.angrybirds.factorys.ElementsFactory;
import fr.univ.angrybirds.utils.Point;

public class LevelBuilder {
	private final Point scorePos 	= new Point(20, 20); // default values
	private final Point messagePos 	= new Point(300, 100); // default values
	private final int 	height 		= 800;
	private final int 	width 		= 600;

	/* Default Constructor */
	public LevelBuilder(){}
	
	/* Building Methods */
	public Level createEasy(){
		Level easy = new Level(height, width, scorePos, messagePos);
		List<Element> birds = new ArrayList<Element>();
		List<Element> pigs = new ArrayList<Element>();
		ElementsFactory elemFactory = new ElementsFactory();
		
		Element bird = elemFactory.getElement("LITTLE_RED_BIRD");
		Element pig  = elemFactory.getElement("NORMAL_PIG");
		Element pig2 = elemFactory.getElement("NORMAL_PIG");
		Element pig3 = elemFactory.getElement("NORMAL_PIG");
		
		bird.setPos(new Point(100, 400));
		pig.setPos(new Point(Math.random() * 500 + 200, 480));
		pig2.setPos(new Point(320, 480));
		pig3.setPos(new Point(600, 480));

		birds.add(bird);
		
		pigs.add(pig);
		pigs.add(pig2);
		pigs.add(pig3);
		
		
		easy.addBirds(birds);
		easy.addPigs(pigs);
		
		easy.setBackground("img/fond.png");
		easy.setMessageText("Let's Start !");
		easy.setScoreText("0");
		
		return easy;
	}//createEasy()
	
	
	public Level createMedium(){
		Level medium = new Level(height, width, scorePos, messagePos);
		List<Element> birds = new ArrayList<Element>();
		List<Element> pigs = new ArrayList<Element>();
		
		ElementsFactory elemFactory = new ElementsFactory();
		
		Element bird  = elemFactory.getElement("LITTLE_RED_BIRD");
		Element bird2 = elemFactory.getElement("BIG_RED_BIRD");
		Element pig   = elemFactory.getElement("NORMAL_PIG");
		Element pig2  = elemFactory.getElement("KING_PIG");
		Element pig3  = elemFactory.getElement("ARMOR_PIG");
		
		bird.setPos(new Point(100, 400));
		bird2.setPos(new Point(50, 480));
		pig.setPos(new Point(Math.random() * 500 + 200, 480));
		pig2.setPos(new Point(320, 480));
		pig3.setPos(new Point(600, 480));
		
		birds.add(bird);
		birds.add(bird2);
		
		pigs.add(pig);
		pigs.add(pig2);
		pigs.add(pig3);

		medium.addBirds(birds);
		medium.addPigs(pigs);
		
		medium.setBackground("img/fond.png");
		medium.setMessageText("La partie commence !");
		medium.setScoreText("0");
		
		return medium;
	}//createMedium()

	
	private Level createHard(){
		Level hard = new Level(height, width, scorePos, messagePos);
		
		//TODO
		
		return hard;
	}//createHard
	
}//LevelBuilder
