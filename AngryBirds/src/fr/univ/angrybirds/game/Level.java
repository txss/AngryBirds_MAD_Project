package fr.univ.angrybirds.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import fr.univ.angrybirds.elements.Element;
import fr.univ.angrybirds.utils.Point;

public class Level {
	
	private List<Element> elementList;
	private String background;
	private String messageText;
	private Point messagePos;
	private Color color;
	private String scoreText;
	private Point scorePos;
	
	private int windHeight;
	private int windWidth;
	
	
	public Level(int windowsHeigth, int windowsWidth, Point scorePos, Point messagePos){
		elementList = new ArrayList<Element>();
		color = Color.BLACK; // default Value
		this.windHeight = windowsHeigth;
		this.windWidth = windowsWidth;
		this.scorePos = scorePos;
		this.messagePos = messagePos;
	}
	
	// Begin getters and setters
	public List<Element> getElementList() {
		return elementList;
	}

	public String getBackground(){
		return background;
	}
	
	public Color geColor() {
		return color;
	}

	public String getMessageText() {
		return messageText;
	}

	public String getScoreText() {
		return scoreText;
	}

	public Point getMessagePos() {
		return messagePos;
	}

	public Point getScorePos() {
		return scorePos;
	}

	public int getWindHeight() {
		return windHeight;
	}

	public int getWindWidth() {
		return windWidth;
	}

	public void setWindHeight(int windHeight) {
		this.windHeight = windHeight;
	}

	public void setWindWidth(int windWidth) {
		this.windWidth = windWidth;
	}

	public void setMessagePos(Point messagePos) {
		this.messagePos = messagePos;
	}

	public void setScorePos(Point scorePos) {
		this.scorePos = scorePos;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public void setcolor(Color color) {
		this.color = color;
	}

	public void setScoreText(String scoreText) {
		this.scoreText = scoreText;
	}

	public void setElementList(List<Element> elementList) {
		this.elementList = elementList;
	}
	
	public void setBackground(String background){
		this.background = background;
	}
	// End getters and setters
	
	
	public void addElement(Element elem){
		elementList.add(elem);
	}
	
	
	public Graphics buildLevel(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(Toolkit.getDefaultToolkit().getImage(background), 0, 0, windHeight, windWidth, null);
		g.setColor(color);
	    g.drawString(messageText, (int)messagePos.getX(), (int)messagePos.getY());
	    g.drawString("score: " + scoreText, (int)scorePos.getX(), (int)scorePos.getY());
		for(int k = 0; k < elementList.size(); k++)
			elementList.get(k).build(g2);
		
		return g2;
	}
}
