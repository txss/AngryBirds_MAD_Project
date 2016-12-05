package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import fr.univ.angrybirds.utils.Point;

public class Angry extends Panel implements Runnable, MouseListener, MouseMotionListener {
    double velocityX, velocityY;  // informations relatives à l'oiseau
    Point bird = new Point();
    Point pig = new Point();
    double gravity;                             // gravité
    int mouseX, mouseY;                         // position de la souris lors de la sélection
    String message;                             // message à afficher en haut de l'écran
    int score;                                  // nombre de fois où le joueur a gagné
    boolean gameOver;                           // vrai lorsque le joueur a touché un bord ou le cochon
    boolean selecting;                          // vrai lorsque le joueur sélectionne l'angle et la vitesse
    Image buffer;                               // image pour le rendu hors écran

    // constructeur
    Angry() {
        gravity = 0.1;
        score = 0;
        addMouseListener(this);
        addMouseMotionListener(this);
        init();
        new Thread(this).start();
    }

    // gestion des événements souris
    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) {
        if(gameOver) {
            init();
        } else if(selecting) {
            velocityX = (bird.getX() - mouseX) / 20.0;
            velocityY = (bird.getY() - mouseY) / 20.0;
            message = "L'oiseau prend sont envol";
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

    // début de partie
    void init() {
        gameOver = false;
        selecting = true;
        bird.setX(100);
        bird.setY(400);
        velocityX = 0;
        velocityY = 0;
        pig.setX(Math.random() * 500 + 200); // position aléatoire pour le cochon
        pig.setY(480);
        message = "Choisissez l'angle et la vitesse.";
    }

    // fin de partie
    void stop() {
        velocityX = 0;
        velocityY = 0;
        gameOver = true;
    }

    // boucle qui calcule la position de l'oiseau en vol, effectue l'affichage et teste les conditions de victoire
    public void run() {
        while(true) {
            // un pas de simulation toutes les 10ms
            try { Thread.currentThread().sleep(10); } catch(InterruptedException e) { }

            if(!gameOver && !selecting) {

                // moteur physique
                bird.setX(bird.getX()+ velocityX);
                bird.setY(bird.getY()+ velocityY);
                velocityY += gravity;
                
                // conditions de victoire
                if(Point.getDistance(bird, pig) < 35) {
                    stop();
                    message = "Gagné : cliquez pour recommencer.";
                    score++;
                } else if(bird.getX() < 20 || bird.getX() > 780 || bird.getY() < 0 || bird.getY() > 480) {
                    stop();
                    message = "Perdu : cliquez pour recommencer.";
                }

                // redessine
                repaint();
            }
        }
    }

    // évite les scintillements
    public void update(Graphics g) {
        paint(g);
    }

    // dessine le contenu de l'écran dans un buffer puis copie le buffer à l'écran
    public void paint(Graphics g2) {
        if(buffer == null) buffer = createImage(800, 600);
        Graphics2D g = (Graphics2D) buffer.getGraphics();

        // fond
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // décor
        g.setColor(Color.BLACK);
        g.drawLine(0, 500, 800, 500);
        g.drawLine(100, 500, 100, 400);

        // oiseau
        g.setColor(Color.RED);
        if(selecting) g.drawLine((int) bird.getX(), (int) bird.getY(), mouseX, mouseY); // montre l'angle et la vitesse
        g.fillOval((int) bird.getX() - 20, (int) bird.getY() - 20, 40, 40);

        // cochon
        g.setColor(Color.GREEN);
        g.fillOval((int) pig.getX() - 20, (int) pig.getY()- 20, 40, 40);

        // messages
        g.setColor(Color.BLACK);
        g.drawString(message, 300, 100);
        g.drawString("score: " + score, 20, 20);

        // affichage à l'écran sans scintillement
        g2.drawImage(buffer, 0, 0, null);
    }

    // taille de la fenêtre
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    // met le jeu dans une fenêtre
    public static void main(String args[]) {
        Frame frame = new Frame("Angry BIRDS");
        final Angry obj = new Angry();
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