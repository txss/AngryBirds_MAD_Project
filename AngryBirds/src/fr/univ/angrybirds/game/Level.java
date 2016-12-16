package fr.univ.angrybirds.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ.angrybirds.elements.Element;
import fr.univ.angrybirds.utils.Point;

public class Level {
	
	private Map<String, List<Element>> 	elementsMap;
	
	private String 	background;
	private String 	messageText;
	private Point 	messagePos;
	private Color 	color = Color.BLACK;
	private String 	scoreText;
	private Point 	scorePos;
	private int 	score = 0;
	
	private int windowsHeight;
	private int windowsWidth;
	
	
	public Level(int windowsHeigth, int windowsWidth, Point scorePos, Point messagePos){
		elementsMap = new HashMap<String, List<Element>>();
		
		elementsMap.put("BIRDS", new ArrayList<Element>());
		elementsMap.put("PIGS", new ArrayList<Element>());
		elementsMap.put("OBSTACLES", new ArrayList<Element>());
		
		this.windowsHeight = windowsHeigth;
		this.windowsWidth = windowsWidth;
		this.scorePos = scorePos;
		this.messagePos = messagePos;
	}
	
	/* Getters */
	public Map<String, List<Element>> getElementList() 	{ return elementsMap; }
	
	public List<Element>	getBirdsList()		{ return elementsMap.get("BIRDS"); 		}
	public List<Element>	getPigsList()		{ return elementsMap.get("PIGS"); 		}
	public List<Element>	getObstaclesList()	{ return elementsMap.get("OBSTACLES"); 	}
	
	public String 			getBackground()		{ return background;	}
	public Color 			getColor() 			{ return color; 		}
	public String 			getMessageText() 	{ return messageText; 	}
	public String 			getScoreText() 		{ return scoreText; 	}
	public Point 			getMessagePos() 	{ return messagePos; 	}
	public Point 			getScorePos() 		{ return scorePos; 		}
	public int 				getWindHeight() 	{ return windowsHeight; }
	public int 				getWindWidth() 		{ return windowsWidth; 	}
	public int 				getScore() 			{ return score; 		}
	
	
	/* Setters */
	public void setScore		(int score) 			{ this.score = score; 				}
	public void setWindHeight	(int windHeight) 		{ this.windowsHeight = windHeight; 	}
	public void setWindWidth	(int windWidth) 		{ this.windowsWidth = windWidth; 	}
	public void setMessagePos	(Point messagePos) 		{ this.messagePos = messagePos; 	}
	public void setScorePos		(Point scorePos) 		{ this.scorePos = scorePos; 		}
	public void setMessageText	(String messageText) 	{ this.messageText = messageText; 	}
	public void setcolor		(Color color) 			{ this.color = color; 				}
	public void setScoreText	(String scoreText) 		{ this.scoreText = scoreText; 		}
	public void setBackground	(String background)		{ this.background = background; 	}
	
	public void addBirds (List<Element> birds){
		elementsMap.put("BIRDS", birds);
	}//addBirds()
	
	public void addPigs (List<Element> pigs){
		elementsMap.put("PIGS", pigs);
	}//addPigs()
	
	public void addObstacles (List<Element> obstacles){
		elementsMap.put("OBSTACLES", obstacles);
	}//addObstacles()
	
	
	
	/* Members Methods */
	public void calculScore (Element e){
		this.score += e.getValue();
		this.setScoreText("" + score);
	}
	
	
	public Graphics buildLevel(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(Toolkit.getDefaultToolkit().getImage(background), 0, 0, windowsHeight, windowsWidth, null);
		g.setColor(color);
	    g.drawString(messageText, (int)messagePos.getX(), (int)messagePos.getY());
	    
	    g.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 30));
	    g.setColor(Color.DARK_GRAY);
	    g.drawString("score: " + scoreText, (int)scorePos.getX(), (int)scorePos.getY());
	    g.setColor(Color.RED);
	    
	    for(int i = 0; i < elementsMap.get("BIRDS").size(); i++)
	    	elementsMap.get("BIRDS").get(i).build(g2);

	    for(int i = 0; i < elementsMap.get("PIGS").size(); i++)
	    	elementsMap.get("PIGS").get(i).build(g2);

	    for(int i = 0; i < elementsMap.get("OBSTACLES").size(); i++)
	    	elementsMap.get("OBSTACLES").get(i).build(g2);
		
		return g2;
	}//buildLevel()
	
}//Level
