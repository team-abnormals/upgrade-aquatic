package com.teamabnormals.upgrade_aquatic.api.endimator;

/**
 * PROPERTY OF ENDERGETIC AND MINECRAFT ABNORMALS MODDING
 * A class that tells how long an animation will play and if it's looped
 * @author - SmellyModder(Luke Tonon) 
 */
public class Endimation {
	private int tickDuration;
	private int loopCycles;
	private boolean doesLoop = false;
	
	/**
	 * Simple constructor for an Animation
	 * @param tickDuration - Duration of how long the animation plays for; measured in ticks
	 */
	public Endimation(int tickDuration) {
		this.tickDuration = tickDuration;
		this.loopCycles = 0;
	}
	
	/**
	 * Constructor for looped animations
	 * @param tickDuration - Duration of how long the animation plays for; measured in ticks
	 * @param doesLoop - Does this animation loop
	 * @param loopCycles- How many times does this animation loop
	 */
	public Endimation(int tickDuration, boolean doesLoop, int loopCycles) {
		this(tickDuration);
		this.doesLoop = doesLoop;
		this.loopCycles = loopCycles;
	}
	
	/**
	 * Empty constructor; used for making animations that do nothing
	 */
	public Endimation() {
		this(0, false, 0);
	}
	
	/**
	 * @return - The duration of this animation; measured in ticks
	 */
	public int getAnimationTickDuration() {
		return this.tickDuration;
	}
	
	/**
	 * @return - Does this animation loop
	 */
	public boolean doesAnimationLoop() {
		return this.doesLoop;
	}
	
	/**
	 * @return - Loop cycles for this animations
	 */
	public int getLoopCycles() {
		if(!this.doesLoop) return 0;
		return this.loopCycles;
	}
}
