package fr.univ.angrybirds.factorys;

import fr.univ.angrybirds.elements.Bird;
import fr.univ.angrybirds.elements.Element;
import fr.univ.angrybirds.elements.Pig;

public class ElementsFactory {
	
	
	
	public ElementsFactory (){}

	
	public Element getElement(String elementType){
		
		if(elementType.equalsIgnoreCase("BIRD"))
			return new Bird();
		if(elementType.equalsIgnoreCase("PIG"))
			return new Pig();
		
		return null;
	}
	
}
