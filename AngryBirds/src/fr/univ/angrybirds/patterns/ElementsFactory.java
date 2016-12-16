package fr.univ.angrybirds.patterns;

import fr.univ.angrybirds.elements.Bird;
import fr.univ.angrybirds.elements.Element;
import fr.univ.angrybirds.elements.Pig;

public class ElementsFactory {
	
	public ElementsFactory (){}

	public Element getElement(String elementType){
		
		/****************/
		/* Create Birds */
		/****************/
		if(elementType.equalsIgnoreCase("BIRD"))
			return new Bird();
		
		if(elementType.equalsIgnoreCase("LITTLE_RED_BIRD")) {
			Bird bird = new Bird();
			bird.setPicName("img/red.png");
			bird.setValue(10);
			
			return bird;
		}
		if(elementType.equalsIgnoreCase("BIG_RED_BIRD")) {
			Bird bird = new Bird();
			bird.setPicName("img/terence.png");
			bird.setValue(10);
			
			return bird;
		}
		if(elementType.equalsIgnoreCase("YELLOW_BIRD")) {
			Bird bird = new Bird();
			bird.setPicName("img/chuck.png");
			bird.setValue(10);
			
			return bird;
		}
		if(elementType.equalsIgnoreCase("BLACK_BOMB_BIRD")) {
			Bird bird = new Bird();
			bird.setPicName("img/bomb.png");
			bird.setValue(10);
			
			return bird;
		}
		
		/***************/
		/* Create Pigs */
		/***************/
		if(elementType.equalsIgnoreCase("PIG"))
			return new Pig();
		
		if(elementType.equalsIgnoreCase("NORMAL_PIG")) {
			Pig pig = new Pig();
			pig.setPicName("img/pig_1.png");
			pig.setValue(50);
			pig.setLife(1);
			
			return pig;
		}
		if(elementType.equalsIgnoreCase("KING_PIG")) {
			Pig pig = new Pig();
			pig.setPicName("img/king_pig1.png");
			pig.setValue(100);
			pig.setLife(1);
			
			return pig;
		}
		if(elementType.equalsIgnoreCase("ARMOR_PIG")) {
			Pig pig = new Pig();
			pig.setPicName("img/armor_pig1.png");
			pig.setValue(80);
			pig.setLife(3);
			
			return pig;
		}
		
		/*******************/
		/* Create Obstacle */
		/*******************/
		
		
		return null;
	}//getElement()
	
}//ElementsFactory()
