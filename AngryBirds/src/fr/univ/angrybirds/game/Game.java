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
	
	private final int 	 SKY		= 0;
	private final int 	 FLOOR 		= 480;
	private final int 	 LEFT_WALL 	= 0;
	private final int 	 RIGHT_WALL = 800;
	private final double WALL_DESC 	= 0.8;
	
	private final int BIRD_PATTERN 	= 50;

	private int 			mouseX;
	private int 			mouseY; 
	private boolean 		gameOver;
	private boolean 		gameWin;
	private boolean 		selecting;
	private Image 			buffer;

	private int 			currentLevel = 0;
	private int 			currentBird;

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
		selecting = true;
		gameOver  = false;
		gameWin   = false;
		currentBird = 0;

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

			if(!selecting && !gameOver && !gameWin) {

				Bird bird = (Bird) birdsList.get(currentBird);

				bird.getPos().setX(bird.getPos().getX() + bird.getVelocityX());
				bird.getPos().setY(bird.getPos().getY() + bird.getVelocityY());
				bird.setVelocityY(bird.getVelocityY() + bird.getGravity());

				Iterator<Element> itr = pigsList.iterator();
				while(itr.hasNext()) {
					Element concernedPig = itr.next();

					if(Point.getDistance(bird.getPos(), concernedPig.getPos()) <= 45){
						//b.setVelocityX(0);
						bird.setVelocityY(0);

						levels.get(currentLevel).calculScore(concernedPig);

						itr.remove();

						bird.setVelocityX(bird.getVelocityX() * WALL_DESC);
						bird.setVelocityY(bird.getVelocityY() - 4);

						levels.get(currentLevel).setMessageText("Pig Killer !");

						//Si il reste des cochons
						if(!pigsList.isEmpty()) {
							
							//Arrêt de l'oiseau s'il touche un cochon à faible vitesse
							if(bird.isStopped()) {
								
								//Nous avons encore des oiseaux
								if(currentBird < birdsList.size() - 1) {
									++currentBird;
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
					}
				}

				if((bird.getPos().getY() > FLOOR && bird.getVelocityY() > 0.001)
						|| (bird.getPos().getY() - BIRD_PATTERN/2 < SKY && bird.getVelocityY() < -0.001 ))
					bird.setVelocityY(bird.getVelocityY() * -WALL_DESC);

				if((bird.getPos().getX() + BIRD_PATTERN/2 > RIGHT_WALL && bird.getVelocityX() > 0.001)
						|| (bird.getPos().getX() - BIRD_PATTERN/2 < SKY && bird.getVelocityX() < -0.001 ))
					bird.setVelocityX(bird.getVelocityX() * -WALL_DESC);
				
				if(bird.getVelocityY() < 0.6 && bird.getVelocityY() > 0.0 && bird.getPos().getY() + 2 > FLOOR){
					bird.setVelocityX(0);
					bird.setVelocityY(0);
					bird.setGravity(0);
					
					bird.setStopped(true);
				}

			}
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
			if(gameWin)
				if(currentLevel < levels.size())
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
