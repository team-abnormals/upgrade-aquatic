package com.teamabnormals.upgrade_aquatic.api.endimator;

import java.util.Map;

import com.google.common.collect.Maps;
import com.teamabnormals.upgrade_aquatic.api.UpgradeAquaticAPI.ClientInfo;

import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * PROPERTY OF ENDERGETIC AND MINECRAFT ABNORMALS MODDING
 * A class that can use animations
 * @author SmellyModder(Luke Tonon)
 */
@OnlyIn(Dist.CLIENT)
public class Endimator {
	private int tempTick;
	private int prevTempTick;
	private boolean correctAnimation;
	public EndimatedEntity endimatedEntity;
	private Map<EndimatorRendererModel, float[]> boxValues;
	private Map<EndimatorRendererModel, float[]> prevBoxValues;
    
	public Endimator() {
		this.tempTick = 0;
		this.prevTempTick = 0;
		this.correctAnimation = false;
		this.boxValues = Maps.newHashMap();
		this.prevBoxValues = Maps.newHashMap();
	}
    
	/**
	 * Sets the animation for this endimator instance to play
	 * @param animationToPlay - The animation to play
	 * @return - Is this the correct animation to play
	 */
	public boolean setAnimationToPlay(Endimation animationToPlay) {
		this.tempTick = this.prevTempTick = 0;
		this.correctAnimation = this.endimatedEntity.getPlayingAnimation() == animationToPlay;
		this.prevBoxValues.clear();
	    this.prevBoxValues.putAll(this.boxValues);
	    this.boxValues.clear();
		return this.correctAnimation;
	}
    
	/**
	 * Updates the entity for this endimator instance
	 * @param endimatedEntity - The entity to update
	 */
	public void updateAnimations(EndimatedEntity endimatedEntity) {
		this.endimatedEntity = endimatedEntity;
	}
	
	/**
	 * Starts a Keyframe for a set amount of ticks
	 * @param tickDuration - The duration of the keyframe; measured in ticks
	 */
	public void startKeyframe(int tickDuration) {
		if(!this.correctAnimation) return;
		this.prevTempTick = this.tempTick;
		this.tempTick += tickDuration;
	}
    
	/**
	 * Ends the current Keyframe
	 */
	public void endKeyframe() {
		this.endKeyframe(false);
	}
	
	/**
	 * Starts a Keyframe that holds the most recent box values for a set duration
	 * @param tickDuration - The duration of the Keyframe; measured in ticks
	 */
	public void setStaticKeyframe(int tickDuration) {
		this.startKeyframe(tickDuration);
		this.endKeyframe(true);
	}
    
	/**
	 * Resets the current Keyframe to its default values
	 * @param tickDuration - The duration of the Keyframe; measured in ticks
	 */
	public void resetKeyframe(int tickDuration) {
		this.startKeyframe(tickDuration);
		this.endKeyframe();
	}
	
	/**
	 * Gets the values of a box stored from a map
	 * @param model - The EndimatorRendererModel to look up in the box values map
	 * @return - The EndimatorRendererModel's float array of box values from the box values map
	 */
	public float[] getBoxValues(EndimatorRendererModel model) {
		float[] empty = {0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
		return this.boxValues.computeIfAbsent(model, a -> empty);
	}
	
	/**
	 * Moves an EndimatorRendererModel in the current Keyframe
	 * @param model - EndimatorRendererModel to move
	 * @param x - The x point
	 * @param y - The y point
	 * @param z - The z point
	 */
	public void move(EndimatorRendererModel model, float x, float y, float z) {
		if(!this.correctAnimation) return;
		this.getBoxValues(model)[0] = x;
		this.getBoxValues(model)[1] = y;
		this.getBoxValues(model)[2] = z;
	}
	
	/**
	 * Rotates an EndimatorRendererModel in the current Keyframe
	 * @param model - EndimatorRendererModel to rotate
	 * @param x - The x rotation
	 * @param y - The y rotation
	 * @param z - The z rotation
	 */
	public void rotate(EndimatorRendererModel model, float x, float y, float z) {
		if(!this.correctAnimation) return;
		this.getBoxValues(model)[3] = x;
		this.getBoxValues(model)[4] = y;
		this.getBoxValues(model)[5] = z;
	}
	
	private void endKeyframe(boolean stationary) {
		if(!this.correctAnimation) return;
        
		int animationTick = this.endimatedEntity.getAnimationTick();
		
		if(animationTick >= this.prevTempTick && animationTick < this.tempTick) {
			if(stationary) {
				for(EndimatorRendererModel box : this.prevBoxValues.keySet()) {
					float[] transform = this.prevBoxValues.get(box);
					box.rotationPointX += transform[0];
					box.rotationPointY += transform[1];
					box.rotationPointZ += transform[2];
					box.rotateAngleX += transform[3];
					box.rotateAngleY += transform[4];
					box.rotateAngleZ += transform[5];
				}
			} else {
				float tick = (animationTick - this.prevTempTick + ClientInfo.getPartialTicks()) / (this.tempTick - this.prevTempTick);
				float increment = MathHelper.sin((float) (tick * Math.PI / 2.0F));
				float decrement = 1.0F - increment;	
				for(EndimatorRendererModel box : this.prevBoxValues.keySet()) {
					float[] transform = this.prevBoxValues.get(box);
					box.rotationPointX += decrement * transform[0];
					box.rotationPointY += decrement * transform[1];
					box.rotationPointZ += decrement * transform[2];
					box.rotateAngleX += decrement * transform[3];
					box.rotateAngleY += decrement * transform[4];
					box.rotateAngleZ += decrement * transform[5];
				}
				for(EndimatorRendererModel box : this.boxValues.keySet()) {
					float[] transform = this.boxValues.get(box);       
					box.rotationPointX += increment * transform[0];
					box.rotationPointY += increment * transform[1];
					box.rotationPointZ += increment * transform[2];
					box.rotateAngleX += increment * transform[3];
					box.rotateAngleY += increment * transform[4];
					box.rotateAngleZ += increment * transform[5];
				}
			}
		}
		if(!stationary) {
			this.prevBoxValues.clear();
			this.prevBoxValues.putAll(this.boxValues);
			this.boxValues.clear();
		}
	}
}
