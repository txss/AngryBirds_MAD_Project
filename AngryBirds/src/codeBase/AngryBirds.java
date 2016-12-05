package codeBase;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class AngryBirds extends Panel implements Runnable, MouseListener, MouseMotionListener {
    double birdX, birdY, velocityX, velocityY;  // informations relatives � l'oiseau
    double pigX, pigY;                          // informations relatives au cochon
    double gravity;                             // gravit�
    int mouseX, mouseY;                         // position de la souris lors de la s�lection
    String message;                             // message � afficher en haut de l'�cran
    int score;                                  // nombre de fois o� le joueur a gagn�
    boolean gameOver;                           // vrai lorsque le joueur a touch� un bord ou le cochon
    boolean selecting;                          // vrai lorsque le joueur s�lectionne l'angle et la vitesse
    Image buffer;                               // image pour le rendu hors �cran

    // calcule la distance entre deux points
    static double distance(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // constructeur
    AngryBirds() {
        gravity = 0.1;
        score = 0;
        addMouseListener(this);
        addMouseMotionListener(this);
        init();
        new Thread(this).start();
    }

    // gestion des �v�nements souris
    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) {
        if(gameOver) {
            init();
        } else if(selecting) {
            velocityX = (birdX - mouseX) / 20.0;
            velocityY = (birdY - mouseY) / 20.0;
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

    // d�but de partie
    void init() {
        gameOver = false;
        selecting = true;
        birdX = 100;
        birdY = 400;
        velocityX = 0;
        velocityY = 0;
        pigX = Math.random() * 500 + 200; // position al�atoire pour le cochon
        pigY = 480;
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
                birdX += velocityX;
                birdY += velocityY;
                velocityY += gravity;

                // conditions de victoire
                if(distance(birdX, birdY, pigX, pigY) < 35) {
                    stop();
                    message = "Gagn� : cliquez pour recommencer.";
                    score++;
                } else if(birdX < 20 || birdX > 780 || birdY < 0 || birdY > 475+45) {
                    stop();
                    message = "Perdu : cliquez pour recommencer.";
                }

                // redessine
                repaint();
            }
        }
    }

    // �vite les scintillements
    public void update(Graphics g) {
        paint(g);
    }

    // dessine le contenu de l'�cran dans un buffer puis copie le buffer � l'�cran
    public void paint(Graphics g2) {
        if(buffer == null) buffer = createImage(800, 600);
        Graphics2D g = (Graphics2D) buffer.getGraphics();

        
        // fond
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // d�cor
        g.setColor(Color.BLACK);
        g.drawLine(0, 500, 800, 500);
        g.drawLine(100, 500, 100, 400);

        // oiseau
        g.setColor(Color.RED);
        if(selecting) g.drawLine((int) birdX, (int) birdY, mouseX, mouseY); // montre l'angle et la vitesse
        g.drawImage(Toolkit.getDefaultToolkit().getImage("plainicon.com-64851-256px-36c.png"), (int) birdX - 20, (int) birdY - 60, null);
//        g.fillOval((int) birdX - 20, (int) birdY - 80, 40, 40);

        // cochon
        g.setColor(Color.GREEN);
//        g.fillOval((int) pigX - 20, (int) pigY - 20, 40, 40);
        g.drawImage(Toolkit.getDefaultToolkit().getImage("giphy.gif"), (int) pigX - 20, (int) pigY - 60, 40, 40, null);
        
        // messages
        g.setColor(Color.BLACK);
        g.drawString(message, 300, 100);
        g.drawString("score: " + score, 20, 20);

        // affichage � l'�cran sans scintillement
        g2.drawImage(buffer, 0, 0, null);
    }

    // taille de la fen�tre
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    // met le jeu dans une fen�tre
    public static void main(String args[]) {
        Frame frame = new Frame("Oiseau pas content");
        final AngryBirds obj = new AngryBirds();
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