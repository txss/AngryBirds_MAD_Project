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
import fr.univ.angrybirds.patterns.LevelBuilder;
import fr.univ.angrybirds.utils.Point;

public class Game extends JPanel implements Runnable, MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 5937904086840376212L;
	
	private static final int 	 	SKY				= 0;
	private static final int 	 	FLOOR 			= 480;
	private static final int 	 	LEFT_WALL 		= 0;
	private static final int 	 	RIGHT_WALL 		= 800;
	private static final int 		BIRD_PATTERN	= 50;
	private static final double 	VELOCITYY_MIN 	= 0.6;

	private int 			mouseX;
	private int 			mouseY; 
	private boolean 		gameOver;
	private boolean 		gameWin;
	private boolean 		selecting;
	private Image 			buffer;

	private int 			currentLevel 	= 0;
	private int 			currentBird;

	private List<Level> 	levels		 	= new ArrayList<Level>();

	private List<Element>	birdsList	 	= new ArrayList<Element>();
	private List<Element>	pigsList	 	= new ArrayList<Element>();
	private List<Element>	obstaclList	 	= new ArrayList<Element>();
	
	private Engine 			engine 			= new Engine();


	/**
	 * Constructeur de la classe
	 */
	public Game (){
		addMouseListener(this);
		addMouseMotionListener(this);

		gameLoader();

		new Thread(this).start();
	}//Game()

	/**
	 * Chargement des différents éléments présent dans le niveau courant
	 */
	private void loadElements () {
		birdsList 	= levels.get(currentLevel).getBirdsList();
		pigsList 	= levels.get(currentLevel).getPigsList();
		obstaclList = levels.get(currentLevel).getObstaclesList();
	}//loadElements()
	

	/**
	 * Chargement du jeu dans son intégralité.
	 * L'appel de cette fonction se fait également pour recharger un niveau
	 */
	public void gameLoader () {
		LevelBuilder lvlBuilder = new LevelBuilder();
		
		selecting = true;
		gameOver  = false;
		gameWin   = false;
		currentBird = 0;
		
		mouseX = 100;
		mouseY = 400;

		if (levels.isEmpty()) {
			levels.add(lvlBuilder.createEasy());
			levels.add(lvlBuilder.createMedium());
			levels.add(lvlBuilder.createHard());
		}
		else {
			levels.set(0, lvlBuilder.createEasy());
			levels.set(1, lvlBuilder.createMedium());
			levels.set(2, lvlBuilder.createHard());
		}

		loadElements();

	}//gameLoader()

	
	
	/**
	 * Dessine le contenu de l'ecran dans un buffer puis copie le buffer a l'ecran
	 * @param graphic
	 */
	public void paint (Graphics graphic) {
		if(buffer == null) 
			buffer = createImage(800, 600);

		Graphics2D g = (Graphics2D) buffer.getGraphics();

		levels.get(currentLevel).buildLevel(g);

		if(selecting) 
			g.drawLine((int) birdsList.get(currentBird).getPos().getX(), 
					   (int) birdsList.get(currentBird).getPos().getY(), 
						mouseX, mouseY); // montre l'angle et la vitesse

		// affichage a l'ecran sans scintillement
		graphic.drawImage(buffer, 0, 0, null);
	}//paint()

	public void update(Graphics g) {
		paint(g);
	}//update()
	
	/**
	 * Arrêt d'un niveau en fonction de l'état des différents éléments présents
	 * @param bird
	 */
	private void checkGameState(Bird bird) {
		//Si il reste des cochons
		if(!pigsList.isEmpty()) {
			if(bird.isStopped()) {
				//Nous avons encore des oiseaux
				if(currentBird < birdsList.size() - 1) {
					++currentBird;
					selecting = true;
					birdsList.get(currentBird).setPos(new Point(100.0, 400.0));
				}
				//Toujours des cochons en jeu, oiseaux épuisés, PERDU !
				else {
					levels.get(currentLevel).setMessageText("You Lose ! Retry ?");
					gameOver = true;
				}
			}
		}
		// Si tous les cochons ont été tué, GAGNE !
		else {
			levels.get(currentLevel).setMessageText("You Win !");
			gameOver = true;
			gameWin = true;
		}
	}//checkGameState()

	/**
	 * Calcule la position de l'oiseau en vol, met à jour l'affichage
	 */
	public void run() {
		while(true) {
			// un pas de simulation toutes les 8ms
			try { 
				Thread.sleep(8); 
			} catch(InterruptedException e) { 
				System.out.println(e);
			}

			// Si l'oiseau est en mouvement dans les airs
			if(!selecting && !gameOver && !gameWin) {
				Bird bird = (Bird) birdsList.get(currentBird);
				engine.birdMove(bird);
				
				//Tant qu'il y a encore des cochons
				Iterator<Element> itr = pigsList.iterator();
				while(itr.hasNext()) { 
					Element concernedPig = itr.next();

					//Si l'oiseau touche un cochon
					if(Point.getDistance(bird.getPos(), concernedPig.getPos()) <= 45) {
						
						itr.remove();

						engine.birdHitPig(bird);
						
						levels.get(currentLevel).calculScore(concernedPig);
						levels.get(currentLevel).setMessageText("Pig Killer !");
						
						checkGameState(bird);
					}
				}

				// Si l'oiseau touche le sol ou le ciel il repart dans l'autre sens sur Y
				if((bird.getPos().getY() > FLOOR && bird.getVelocityY() > 0.001)
						|| (bird.getPos().getY() - BIRD_PATTERN/2 < SKY && bird.getVelocityY() < -0.001 ))
					engine.birdHitTopOrBot(bird);

				//Si l'oiseau touche un le mur droit ou gauche il repart dans l'autre sens sur X
				if((bird.getPos().getX() + BIRD_PATTERN/2 > RIGHT_WALL && bird.getVelocityX() > 0.001)
						|| (bird.getPos().getX() - BIRD_PATTERN/2 < LEFT_WALL && bird.getVelocityX() < -0.001 ))
					engine.birdHitWall(bird);
				
				//Si l'oiseau perd trop de vitesse il s'arrête
				if(bird.getVelocityY() < VELOCITYY_MIN && bird.getVelocityY() > 0.0 
						&& bird.getPos().getY() + 2 > FLOOR) {
					engine.birdToSlow(bird);
					checkGameState(bird);
				}

			}
			repaint();
		}
	}


	// gestion des evenements souris
	public void mouseClicked  (MouseEvent e) {} //mouseClicked()
	public void mouseEntered  (MouseEvent e) {} //mouseEntered()
	public void mouseExited   (MouseEvent e) {} //mouseExited()
	public void mousePressed  (MouseEvent e) {} //mousePressed()
	public void mouseMoved	  (MouseEvent e) {} //mouseMoved()
	public void mouseReleased (MouseEvent e) {
		if(gameOver) {
			if(gameWin)
				if(currentLevel < levels.size() -1)
					currentLevel++;
				else
					currentLevel = 0;

			gameLoader();
		}
		else if(selecting) {
			Bird bird = (Bird) birdsList.get(currentBird);
			bird.setVelocityX((bird.getPos().getX() - mouseX) / 20.0);
			bird.setVelocityY((bird.getPos().getY() - mouseY) / 20.0);
			selecting = false;
		}
		repaint();
	}//mouseReleased()


	public void mouseDragged(MouseEvent e) { 
		mouseX = e.getX();
		mouseY = e.getY();
		repaint(); 
	} //mouseDragged()

	// taille de la fenetre
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}//getPreferredSize()


	public static void main(String args[]) {
		Frame frame = new Frame("BOING BIRDS");
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
