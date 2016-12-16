package fr.univ.angrybirds.physics;

import fr.univ.angrybirds.elements.Bird;
import fr.univ.angrybirds.patterns.IStrategy;

public class BirdToSlow implements IStrategy {

	@Override
	public void executeBirdAction(Bird bird) {
		bird.setVelocityX(0);
		bird.setVelocityY(0);
		bird.setGravity(0);
		
		bird.setStopped(true);
	}

}//BirdToSlow
