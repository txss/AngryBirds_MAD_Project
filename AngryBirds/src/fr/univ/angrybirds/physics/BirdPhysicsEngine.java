package fr.univ.angrybirds.physics;

import fr.univ.angrybirds.elements.Bird;
import fr.univ.angrybirds.patterns.IStrategy;

public class BirdPhysicsEngine {
	
	private IStrategy strategy;

	public BirdPhysicsEngine (IStrategy strategy) {
		this.strategy = strategy;
	}//PhysicsEngine()

	public void executeBirdAction(Bird bird) {
		strategy.executeBirdAction(bird);
	}//execute()
	
}//PhysicsEngine
