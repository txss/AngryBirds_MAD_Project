package fr.univ.angrybirds.physics;

import fr.univ.angrybirds.elements.Bird;
import fr.univ.angrybirds.patterns.IStrategy;

public class BirdMove implements IStrategy {

	@Override
	public void executeBirdAction(Bird bird) {
		bird.getPos().setX(bird.getPos().getX() + bird.getVelocityX());
		bird.getPos().setY(bird.getPos().getY() + bird.getVelocityY());
		bird.setVelocityY(bird.getVelocityY() + bird.getGravity());
	}
	
}//BirdMove