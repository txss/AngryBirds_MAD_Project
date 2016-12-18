package fr.univ.angrybirds.patterns;

import java.util.ArrayList;
import java.util.List;

import fr.univ.angrybirds.elements.Element;
import fr.univ.angrybirds.game.Level;
import fr.univ.angrybirds.utils.Point;

public class LevelBuilder {
	private final Point SCORE_POS 	= new Point(20, 40);
	private final Point MESSAGE_POS = new Point(300, 100);
	private final int 	HEIGHT 		= 800;
	private final int 	WITDH 		= 600;

	/* Default Constructor */
	public LevelBuilder(){}
	
	/* Building Methods */
	public Level createEasy(){
		Level easy = new Level(HEIGHT, WITDH, SCORE_POS, MESSAGE_POS);
		List<Element> birds = new ArrayList<Element>();
		List<Element> pigs  = new ArrayList<Element>();
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
		easy.setMessageText("LEVEL 1, Let's Start !");
		easy.setScoreText("0");
		
		return easy;
	}//createEasy()
	
	
	public Level createMedium(){
		Level medium = new Level(HEIGHT, WITDH, SCORE_POS, MESSAGE_POS);
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
		bird2.setGravity(0.3);
		
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
		medium.setMessageText("LEVEL 2, Let's Start !");
		medium.setScoreText("0");
		
		return medium;
	}//createMedium()

	
	public Level createHard(){
		Level hard = new Level(HEIGHT, WITDH, SCORE_POS, MESSAGE_POS);
		
		List<Element> birds = new ArrayList<Element>();
		List<Element> pigs  = new ArrayList<Element>();
		
		ElementsFactory elemFactory = new ElementsFactory();
		
		Element bird  = elemFactory.getElement("LITTLE_RED_BIRD");
		Element bird2 = elemFactory.getElement("BIG_RED_BIRD");
		Element pig   = elemFactory.getElement("NORMAL_PIG");
		Element pig2  = elemFactory.getElement("KING_PIG");
		Element pig3  = elemFactory.getElement("ARMOR_PIG");
		
		bird.setPos(new Point(100, 400));
		bird2.setPos(new Point(50, 480));
		bird2.setGravity(0.3);
		pig.setPos(new Point(Math.random() * 500 + 200, 480));
		pig2.setPos(new Point(320, 480));
		pig3.setPos(new Point(600, 480));
		
		birds.add(bird);
		birds.add(bird2);
		
		pigs.add(pig);
		pigs.add(pig2);
		pigs.add(pig3);

		hard.addBirds(birds);
		hard.addPigs(pigs);
		
		hard.setBackground("img/fond.png");
		hard.setMessageText("LEVEL 3, Let's Start !");
		hard.setScoreText("0");
		
		return hard;
	}//createHard
	
}//LevelBuilder
