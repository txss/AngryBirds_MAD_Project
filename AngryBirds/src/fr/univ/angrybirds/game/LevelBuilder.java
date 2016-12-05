package fr.univ.angrybirds.game;

import fr.univ.angrybirds.elements.Bird;
import fr.univ.angrybirds.elements.Element;
import fr.univ.angrybirds.elements.Pig;
import fr.univ.angrybirds.utils.Point;

public class LevelBuilder {

	public LevelBuilder(){}
	

	private Level createEasy(){
		Level easy = new Level();
		Point birdPos = new Point(100, 400);
		Point pigPos = new Point(Math.random() * 500 + 200, 480);
		
		Element bird = new Bird(birdPos, 0, 0, "Angry_Birds.png");
		Element pig  = new Pig(pigPos, "106596436.png");
		
		easy.addElement(bird);
		easy.addElement(pig);
		
		return easy;
	}
	
	
	
	private Level createMeduim(){
		Level easy = new Level();
		
		// TODO 
		
		return easy;
	}
	
	
	private Level createHard(){
		Level easy = new Level();
		
		//TODO
		
		return easy;
	}
	
}
