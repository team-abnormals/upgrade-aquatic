package com.teamabnormals.upgrade_aquatic.api.client;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author - SmellyModder(Luke Tonon)
 * @param <E> - The Entity for the Model; Vanilla needs this by default so it will be used here as well
 */
@OnlyIn(Dist.CLIENT)
public class UAEntityModel<E extends LivingEntity> extends EntityModel<E> {
	// A map of all the boxes in the model and their specific values
	private Map<RendererModel, float[]> defaultBoxValues = Maps.newHashMap();
	
	/*
	 * A method to set the default box values of this model
	 * Used with `resetBoxesToDefaultValues` to make the model revert back to its original values
	 */
	public void setDefaultBoxValues() {
		for(int i = 0; i < this.boxList.size(); i++) {
			defaultBoxValues.put(this.boxList.get(i), new float[] {
				this.boxList.get(i).rotateAngleX, this.boxList.get(i).rotateAngleY, this.boxList.get(i).rotateAngleZ,
				this.boxList.get(i).offsetX, this.boxList.get(i).offsetY, this.boxList.get(i).offsetZ,
				this.boxList.get(i).rotationPointX, this.boxList.get(i).rotationPointY, this.boxList.get(i).rotationPointZ
			});
		}
	}
	
	/*
	 * A method to revert the boxes values to their originals
	 */
	public void resetBoxesToDefaultValues() {
		for(int i = 0; i < this.boxList.size(); i++) {
			float[] boxValues = defaultBoxValues.get(this.boxList.get(i));
			RendererModel curBox = this.boxList.get(i);
			curBox.rotateAngleX = boxValues[0];
			curBox.rotateAngleY = boxValues[1];
			curBox.rotateAngleZ = boxValues[2];
			curBox.offsetX = boxValues[3];
			curBox.offsetY = boxValues[4];
			curBox.offsetZ = boxValues[5];
			curBox.rotationPointX = boxValues[6];
			curBox.rotationPointY = boxValues[7];
			curBox.rotationPointZ = boxValues[8];
		}
	}
	
	/**
	 * An intricate method that adds rotations onto a current box's angle of an axis; useful for complex animations
	 * 
	 * @param box{RendererModel} - The box to rotate
	 * @param doAxis{boolean[]} - An array of booleans used to determine if x, y, and z should be rotated. Array should have an index of [2]
	 * @param reverse{boolean} - Boolean to determine if the rotation should be inverted
	 * @param scale{float} - A multiplier to the rotation
	 * @param speed{float} - The speed of the rotation
	 * @param degree{float} - The degree of the angle
	 * @param offset{float} - Offsets the time of the angle
	 * @param distance{float} - Distance of the angle
	 * @param distanceSpeed{float} - The angle distance speed
	 * 
	 */
	public void advancedRotateAngle(RendererModel box, boolean[] doAxis, boolean reverse, float scale, float speed, float degree, float offset, float weight, float distance, float distanceSpeed) {
		int neg = reverse ? -1 : 1;
		box.rotateAngleX = doAxis[0] ? box.rotateAngleX + neg * MathHelper.cos(distance * (speed * scale) + offset) * (degree * scale) * distanceSpeed : box.rotateAngleX;
		box.rotateAngleY = doAxis[1] ? box.rotateAngleY + neg * MathHelper.cos(distance * (speed * scale) + offset) * (degree * scale) * distanceSpeed : box.rotateAngleY;
		box.rotateAngleZ = doAxis[2] ? box.rotateAngleZ + neg * MathHelper.cos(distance * (speed * scale) + offset) * (degree * scale) * distanceSpeed : box.rotateAngleZ;
	}
	
	/**
	 * Much like `advancedRotateAngle` above but takes in an array of boxes which it will rotate those boxes in correlation with each other making a chain effect; good for tails for example
	 * 
	 * @param box{RendererModel} - The box to rotate
	 * @param doAxis{boolean[]} - An array of booleans used to determine if x, y, and z should be rotated. Array should have an index of [2]
	 * @param reverse{boolean} - Boolean to determine if the rotation should be inverted
	 * @param scale{float} - A multiplier to the rotation
	 * @param speed{float} - The speed of the rotation
	 * @param degree{float} - The degree of the angle
	 * @param chainOffset{float} - Offsets the time of the angle, will chain per index of boxes
	 * @param distance{float} - Distance of the angle
	 * @param distanceSpeed{float} - The angle distance speed\
	 * 
	 */
	public void chainAdvancedRotateAngle(RendererModel[] boxes, boolean[] doAxis, boolean reverse, float scale, float chainOffset, float speed, float degree, float distance, float distanceSpeed) {
		int neg = reverse ? -1 : 1;
		int length = boxes.length;
		for (int i = 0; i < length; i++) {
			boxes[i].rotateAngleX = doAxis[0] ? boxes[i].rotateAngleX + neg * MathHelper.cos((distance * (speed * scale) + (float) ((chainOffset * Math.PI) / (length * 2) * i))) * (degree * scale) * distanceSpeed : boxes[i].rotateAngleX;
			boxes[i].rotateAngleY = doAxis[0] ? boxes[i].rotateAngleY + neg * MathHelper.cos((distance * (speed * scale) + (float) ((chainOffset * Math.PI) / (length * 2) * i))) * (degree * scale) * distanceSpeed : boxes[i].rotateAngleY; 
			boxes[i].rotateAngleZ = doAxis[0] ? boxes[i].rotateAngleZ + neg * MathHelper.cos((distance * (speed * scale) + (float) ((chainOffset * Math.PI) / (length * 2) * i))) * (degree * scale) * distanceSpeed : boxes[i].rotateAngleZ; 
		}
	}
	
}
