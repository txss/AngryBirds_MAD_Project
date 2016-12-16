package fr.univ.angrybirds.physics;

import fr.univ.angrybirds.elements.Bird;
import fr.univ.angrybirds.patterns.IStrategy;

public class BirdHitWall implements IStrategy {
	
	private static final double SLOW_DOWN = 0.8;

	@Override
	public void executeBirdAction(Bird bird) {
		bird.setVelocityX(bird.getVelocityX() * -SLOW_DOWN);
	}
	
}//BirdHitWall