package com.teamabnormals.upgrade_aquatic.api.endimator;

import com.teamabnormals.upgrade_aquatic.api.UpgradeAquaticAPI.ClientInfo;

import net.minecraft.client.renderer.entity.model.EntityModel;

/**
 * PROPERTY OF ENDERGETIC AND MINECRAFT ABNORMALS MODDING
 * @author - SmellyModder(Luke Tonon)
 * @param <E> - The Entity for the Model; Vanilla needs this by default so it will be used here as well
 */
public class EndimatorEntityModel<E extends EndimatedEntity> extends EntityModel<E> {
	private EndimatorRendererModel scaleController;
	
	public void setDefaultBoxValues() {
		for(int i = 0; i < this.boxList.size(); i++) {
			((EndimatorRendererModel)this.boxList.get(i)).setDefaultBoxValues();
		}
	}
	
	public void revertBoxesToDefaultValues() {
		for(int i = 0; i < this.boxList.size(); i++) {
			((EndimatorRendererModel)this.boxList.get(i)).revertToDefaultBoxValues();
		}
	}
	
	public void createScaleController() {
		this.scaleController = new EndimatorRendererModel(this, 0, 0);
		this.scaleController.showModel = false;
		this.scaleController.setRotationPoint(1, 1, 1);
	}
	
	public EndimatorRendererModel getScaleController() {
		return this.scaleController;
	}
	
	public void animateModel(E endimatedEntity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(endimatedEntity, f, f1, f2, f3, f4, f5);
	}
	
	public float getAnimationFrame(E endimatedEntity) {
		return endimatedEntity.frame + ClientInfo.getPartialTicks();
	}
}
