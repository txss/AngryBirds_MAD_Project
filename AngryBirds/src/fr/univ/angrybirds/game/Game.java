package fr.univ.angrybirds.game;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import fr.univ.angrybirds.elements.Bird;
import fr.univ.angrybirds.elements.Element;
import fr.univ.angrybirds.utils.Point;

public class Game extends JPanel implements Runnable, MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 5937904086840376212L;

	private int 			mouseX;
	private int 			mouseY; 
	private boolean 		gameOver 	 = false;
	private boolean 		selecting 	 = true;
	private int 			currentLevel = 0;
	private Image 			buffer;
	
	private List<Level> 	levels		 = new ArrayList<Level>();
	
	private List<Element>	birdsList	 = new ArrayList<Element>();
	private List<Element>	pigsList	 = new ArrayList<Element>();
	private List<Element>	obstaclList	 = new ArrayList<Element>();
	
	
	public Game (){
		addMouseListener(this);
		addMouseMotionListener(this);
		
		gameLoader();
		
		new Thread(this).start();
	}//Game()
	
	private void loadElements () {
		birdsList 	= levels.get(currentLevel).getBirdsList();
		pigsList 	= levels.get(currentLevel).getPigsList();
		obstaclList = levels.get(currentLevel).getObstaclesList();
	}//loadElements()
	
	public void gameLoader () {
		LevelBuilder lvlBuilder = new LevelBuilder();
		levels.add(lvlBuilder.createEasy());
		levels.add(lvlBuilder.createMedium());
		
		loadElements();
		
	}//gameLoader()

	// dessine le contenu de l'ecran dans un buffer puis copie le buffer a l'ecran
	public void paint (Graphics g2) {
		if(buffer == null) 
			buffer = createImage(800, 600);
		
		Graphics2D g = (Graphics2D) buffer.getGraphics();
		
		levels.get(currentLevel).buildLevel(g);
		
		if(selecting) 
			g.drawLine((int) birdsList.get(0).getPos().getX(), 
					   (int) birdsList.get(0).getPos().getY(), 
					   mouseX, mouseY); // montre l'angle et la vitesse
		
		// affichage a l'ecran sans scintillement
		g2.drawImage(buffer, 0, 0, null);
	}//paint()
	
	// boucle qui calcule la position de l'oiseau en vol, effectue l'affichage et teste les conditions de victoire
	public void run() {
		while(true) {
			// un pas de simulation toutes les 10ms
			try { 
				Thread.currentThread();
				Thread.sleep(10); 
			} catch(InterruptedException e) { 
				System.out.println(e);
			}
			
			if(!selecting && !gameOver){
					Bird currentBird = (Bird) birdsList.get(0);
					
					currentBird.getPos().setX(currentBird.getPos().getX() + currentBird.getVelocityX());
					currentBird.getPos().setY(currentBird.getPos().getY() + currentBird.getVelocityY());
					currentBird.setVelocityY(currentBird.getVelocityY() + currentBird.getGravity());
					
					Iterator<Element> itr = pigsList.iterator();
					while(itr.hasNext()) {
						Element concernedPig = itr.next();
						
						if(Point.getDistance(currentBird.getPos(), concernedPig.getPos()) <= 45){
							//b.setVelocityX(0);
							currentBird.setVelocityY(0);
							
							levels.get(currentLevel).calculScore(concernedPig);
							
							itr.remove();
							
							currentBird.setVelocityX(currentBird.getVelocityX() * 0.8);
							
							levels.get(currentLevel).setMessageText("Pig Killer !");

							if(currentBird.getVelocityX() < 0.6 ){
								currentBird.setVelocityX(0);
								currentBird.setVelocityY(0);
								currentBird.setGravity(0);
								gameOver = true;
							}
							
							if(pigsList.size() <= 0){
								levels.get(currentLevel).setMessageText("You Win !");
								gameOver = true;
								
								if(currentLevel < levels.size())
									currentLevel++;
								else
									currentLevel = 0;
							}
						}
					}

					if((currentBird.getPos().getY()+25 > 480 && currentBird.getVelocityY() > 0.001) 
							|| (currentBird.getPos().getY()-25 < 0 && currentBird.getVelocityY() < -0.001 ))
						currentBird.setVelocityY(currentBird.getVelocityY()*-0.8);
	
					if((currentBird.getPos().getX()+25 > 800 && currentBird.getVelocityX() > 0.001)
							|| (currentBird.getPos().getX()-25 < 0 && currentBird.getVelocityX() < -0.001 ))
						currentBird.setVelocityX(currentBird.getVelocityX()*-0.8);
			
			}
			
			//                // moteur physique
			//                birdX += velocityX;
			//                birdY += velocityY;
			//                velocityY += gravity;

			// redessine
			repaint();
		}
	}

	public void update(Graphics g) {
		paint(g);
	}//update()


	// gestion des evenements souris
	public void mouseClicked  (MouseEvent e) {} //mouseClicked()
	public void mouseEntered  (MouseEvent e) {} //mouseEntered()
	public void mouseExited   (MouseEvent e) {} //mouseExited()
	public void mousePressed  (MouseEvent e) {} //mousePressed()
	public void mouseReleased (MouseEvent e) {
		if(gameOver) {
			gameLoader();
			gameOver = false;
			selecting = true;
		} else if(selecting) {
			Bird currentBird = (Bird) birdsList.get(0);
			currentBird.setVelocityX((currentBird.getPos().getX() - mouseX) / 20.0);
			currentBird.setVelocityY((currentBird.getPos().getY() - mouseY) / 20.0);
			selecting = false;
		}
		repaint();
	}//mouseReleased()
	
	public void mouseMoved(MouseEvent e) { 
		mouseX = e.getX();
		mouseY = e.getY();
		repaint();
	}//mouseMoved()

	public void mouseDragged(MouseEvent e) { mouseMoved(e); } //mouseDragged()
	
	// taille de la fenetre
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}//getPreferredSize()

	
	public static void main(String args[]) {
		Frame frame = new Frame("BIRDS");
		Game obj = new Game();
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		frame.add(obj);
		frame.pack();
		frame.setVisible(true);
	}//main()
	
}//Game
