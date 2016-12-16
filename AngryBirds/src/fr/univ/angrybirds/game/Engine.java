package fr.univ.angrybirds.game;

import fr.univ.angrybirds.elements.Bird;
import fr.univ.angrybirds.physics.BirdHitPig;
import fr.univ.angrybirds.physics.BirdHitTopOrBot;
import fr.univ.angrybirds.physics.BirdHitWall;
import fr.univ.angrybirds.physics.BirdMove;
import fr.univ.angrybirds.physics.BirdPhysicsEngine;
import fr.univ.angrybirds.physics.BirdToSlow;

public class Engine {
	BirdPhysicsEngine birdEngine;
	
	public Engine () {}
	
	public void birdMove(Bird bird) {
		BirdMove birdMove = new BirdMove();
		birdEngine = new BirdPhysicsEngine(birdMove);
		
		birdEngine.executeBirdAction(bird);
	}//birdMove()
	
	public void birdHitTopOrBot(Bird bird) {
		BirdHitTopOrBot birdHitTopOrBot = new BirdHitTopOrBot();
		birdEngine = new BirdPhysicsEngine(birdHitTopOrBot);
		
		birdEngine.executeBirdAction(bird);
	}//birdHitWall()

	public void birdHitWall(Bird bird) {
		BirdHitWall birdHitWall = new BirdHitWall();
		birdEngine = new BirdPhysicsEngine(birdHitWall);
		
		birdEngine.executeBirdAction(bird);
	}//birdHitWall()
	
	public void birdHitPig(Bird bird) {
		BirdHitPig birdHitPig = new BirdHitPig();
		birdEngine = new BirdPhysicsEngine(birdHitPig);
		
		birdEngine.executeBirdAction(bird);
	}//birdHitWall()
	
	public void birdToSlow(Bird bird) {
		BirdToSlow	birdToSlow = new BirdToSlow();
		birdEngine = new BirdPhysicsEngine(birdToSlow);
		
		birdEngine.executeBirdAction(bird);
	}//birdToSlow()
	
}//Engine