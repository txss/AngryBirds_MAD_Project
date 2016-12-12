package fr.univ.angrybirds.elements;

import java.awt.Graphics;
import java.awt.Toolkit;

import fr.univ.angrybirds.utils.Point;

public abstract class Element {

	protected Point 	pos;
	protected String	picName;
	protected double 	gravity = 0.1;
	protected int 		height = 50;
	protected int 		width = 50;
	protected int 		value;


	public Element(){}

	/* Getters */
	public Point 	getPos() 		{ return pos; 	  }
	public String 	getPicName() 	{ return picName; }
	public double 	getGravity() 	{ return gravity; }
	public int 		getHeight() 	{ return height;  }
	public int 		getWidth() 		{ return width;   }
	public int 		getValue() 		{ return value;   }

	/* Setters */
	public void setValue	(int value) 	 { this.value 	= value; 	}
	public void setGravity	(double gravity) { this.gravity = gravity; 	}
	public void setPicName	(String picName) { this.picName = picName; 	}
	public void setPos		(Point pos) 	 { this.pos		= pos; 		}
	public void setHeight	(int height) 	 { this.height 	= height; 	}
	public void setWidth	(int width) 	 { this.width 	= width; 	}


	/* Members Methods */
	public Graphics build(Graphics graphic) {
		graphic.drawImage(Toolkit.getDefaultToolkit().getImage(picName), 
				(int) pos.getX() - width/2, 
				(int) pos.getY() - height/2, 
				height, width, null);
		
		return graphic;
	}//build()

}//Element
