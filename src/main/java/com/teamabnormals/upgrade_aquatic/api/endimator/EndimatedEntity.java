package com.teamabnormals.upgrade_aquatic.api.endimator;

import javax.annotation.Nullable;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * PROPERTY OF ENDERGETIC AND MINECRAFT ABNORMALS MODDING
 * Skeleton class for Endimator entities
 * @author - SmellyModder(Luke Tonon)
 */
public class EndimatedEntity extends CreatureEntity {
	public int frame;
	private static final DataParameter<Integer> ANIMATION_TICK = EntityDataManager.createKey(EndimatedEntity.class, DataSerializers.VARINT);
	public static final Endimation BLANK_ANIMATION = new Endimation();
	private Endimation animation = BLANK_ANIMATION;

	public EndimatedEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(ANIMATION_TICK, 0);
	}
	
	@Override
	public void tick() {
		super.tick();
		this.frame++;
		if(!this.isAnimationPlaying(BLANK_ANIMATION)) {
			this.setAnimationTick(this.getAnimationTick() + 1);
			if(this.getAnimationTick() >= this.getPlayingAnimation().getAnimationTickDuration()) {
				this.resetPlayingAnimationToDefault();
			}
		}
	}
	
	/**
	 * @param animation - The animation to check
	 * @return - Is the animation playing
	 */
	public boolean isAnimationPlaying(Endimation animation) {
		return this.getPlayingAnimation() == animation;
	}
	
	/**
	 * @return - Is no animation playing
	 */
	public boolean isNoAnimationPlaying() {
		return this.getPlayingAnimation() == BLANK_ANIMATION;
	}
	
	/**
	 * @return - Is the world remote; if true: client
	 */
	public boolean isWorldRemote() {
		return this.getEntityWorld().isRemote;
	}
	
	/**
	 * @return - Gets the playing animation
	 */
	public Endimation getPlayingAnimation() {
		return this.animation;
	}
	
	/**
	 * @return - Gets this entity's animations
	 */
	@Nullable
	public Endimation[] getAnimations() {
		return null;
	}
	
	/**
	 * @return - The progress; measured in ticks, of the current playing animation
	 */
	public int getAnimationTick() {
		return this.dataManager.get(ANIMATION_TICK);
	}
	
	/**
	 * Sets the progress of the current playing animation
	 * @param animationTick - Progress; measured in ticks
	 */
	public void setAnimationTick(int animationTick) {
		this.dataManager.set(ANIMATION_TICK, animationTick);
	}
	
	/**
	 * Sets an animation to play
	 * @param animationToPlay - The animation to play
	 */
	public void setPlayingAnimation(Endimation animationToPlay) {
		this.animation = animationToPlay;
		this.onAnimationStart(animationToPlay);
		this.setAnimationTick(0);
	}
	
	/**
	 * Resets the current animation to a blank one
	 */
	public void resetPlayingAnimationToDefault() {
		this.onAnimationEnd(this.animation);
		this.animation = BLANK_ANIMATION;
	}
	
	protected void onAnimationStart(Endimation animationStarted) {}
	
	protected void onAnimationEnd(Endimation animationEnded) {}
		
	/**
	 * Used in movement controllers to get the distance between the entity's desired path location and its current position
	 * @param pathX - x location of the path
	 * @param pathY - y location of the path
	 * @param pathZ - z location of the path
	 * @return - A vector containing the mid-position of the entity's path end location and its current location
	 */
	public Vec3d getMoveControllerPathDistance(double pathX, double pathY, double pathZ) {
		return new Vec3d(pathX - this.getPosX(), pathY - this.getPosY(), pathY - this.getPosZ());
	}
	
	/**
	 * Used for rotationYaw in movement controllers
	 * @param vec3d - The distance vector
	 * @return - A vector that gets the target angle for a path's distance
	 */
	public float getTargetAngleForPathDistance(Vec3d vec3d) {
		return (float) (MathHelper.atan2(vec3d.z, vec3d.x) * (double) (180F / (float) Math.PI)) - 90F;
	}
}
