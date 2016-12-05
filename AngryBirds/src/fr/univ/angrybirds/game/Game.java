package fr.univ.angrybirds.game;

import java.awt.Color;
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
import java.util.List;

import javax.swing.JPanel;

import fr.univ.angrybirds.elements.Bird;
import fr.univ.angrybirds.elements.Element;
import fr.univ.angrybirds.elements.Pig;
import fr.univ.angrybirds.utils.Point;

public class Game extends JPanel implements Runnable, MouseListener, MouseMotionListener{

	private static final long serialVersionUID = -5937904086840376212L;

	ArrayList<Level> levels;
	Image buffer;

	private boolean gameOver=false;
	private boolean selecting=true;
	private int mouseX;
	private int mouseY; 
	double gravite = 0.1;

	public void init (){
		LevelBuilder lvl = new LevelBuilder();
		levels = new ArrayList<Level>();
		levels.add(lvl.createEasy());
		
	}

	public Game(){
		addMouseListener(this);
		addMouseMotionListener(this);
		init();
		new Thread(this).start();
	}




	// dessine le contenu de l'écran dans un buffer puis copie le buffer à l'écran
	public void paint(Graphics g2) {
		if(buffer == null) buffer = createImage(800, 600);
		Graphics2D g = (Graphics2D) buffer.getGraphics();

		Bird b = (Bird) (levels.get(0).getElementList().get(0));
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.RED);

		levels.get(0).buildLevel(g);

		if(selecting) g.drawLine((int) b.getPos().getX(), (int)  b.getPos().getY(), mouseX, mouseY); // montre l'angle et la vitesse

		// affichage à l'écran sans scintillement
		g2.drawImage(buffer, 0, 0, null);
	}

	// boucle qui calcule la position de l'oiseau en vol, effectue l'affichage et teste les conditions de victoire
	public void run() {
		while(true) {
			// un pas de simulation toutes les 10ms
			try { Thread.currentThread().sleep(10); } catch(InterruptedException e) { }

			if(!selecting && !gameOver){
				
				List<Element> elements = levels.get(0).getElementList();
				Bird b = (Bird) (levels.get(0).getElementList().get(0));
				b.getPos().setX(b.getPos().getX()+b.getVelocityX());
				b.getPos().setY(b.getPos().getY()+b.getVelocityY());
				b.setVelocityY(b.getVelocityY()+gravite);

				for(Element e :levels.get(0).getElementList()){
					if(e instanceof Pig && Point.getDistance(b.getPos(), e.getPos()) <= 45){
						//b.setVelocityX(0);
						b.setVelocityY(0);
						levels.get(0).getElementList().remove(e);
						//gameOver=true;
					}
				}


				if((b.getPos().getY()+25 > 480 && b.getVelocityY() > 0.001) || (b.getPos().getY()-25 < 0 && b.getVelocityY() < -0.001 ))
					b.setVelocityY(b.getVelocityY()*-0.8);

				if((b.getPos().getX()+25 > 800 && b.getVelocityX() > 0.001) || (b.getPos().getX()-25 < 0 && b.getVelocityX() < -0.001 ))
					b.setVelocityX(b.getVelocityX()*-0.8);


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
	}


	// gestion des événements souris
	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) {
		if(gameOver) {
			init();
			gameOver=false;
			selecting=true;
		} else if(selecting) {
			Bird b = (Bird) (levels.get(0).getElementList().get(0));
			b.setVelocityX((b.getPos().getX() - mouseX) / 20.0);
			b.setVelocityY((b.getPos().getY() - mouseY) / 20.0);
			selecting = false;
		}
		repaint();
	}
	public void mouseDragged(MouseEvent e) { mouseMoved(e); }
	public void mouseMoved(MouseEvent e) { 
		mouseX = e.getX();
		mouseY = e.getY();
		repaint();
	}

	// taille de la fenêtre
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

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
	}


}
