package fr.univ.angrybirds.game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import fr.univ.angrybirds.elements.Element;

public class Level {
	
	private List<Element> elementList;
	private String background;
	
	public Level(){
		elementList = new ArrayList<Element>();
	}
	
	public List<Element> getElementList() {
		return elementList;
	}

	public String getBackground(){
		return background;
	}
	
	public void setElementList(List<Element> elementList) {
		this.elementList = elementList;
	}
	
	public void setBackground(String background){
		this.background = background;
	}
	
	public void addElement(Element elem){
		elementList.add(elem);
	}
	
	public Graphics buildLevel(Graphics g){
		for(int k = 0; k < elementList.size(); k++)
			g = elementList.get(k).build(g);
		
		return g;
	}
}