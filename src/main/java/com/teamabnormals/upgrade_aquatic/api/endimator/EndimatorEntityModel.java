package com.teamabnormals.upgrade_aquatic.api.endimator;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.renderer.entity.model.EntityModel;

/**
 * PROPERTY OF ENDERGETIC AND MINECRAFT ABNORMALS MODDING
 * @author - SmellyModder(Luke Tonon)
 * @param <E> - The Entity for the Model; Vanilla needs this by default so it will be used here as well
 */
public abstract class EndimatorEntityModel<E extends EndimatedEntity> extends EntityModel<E> {
	protected List<EndimatorModelRenderer> cuboids = Lists.newArrayList();
	private EndimatorModelRenderer scaleController;
	protected Endimator endimator = new Endimator();
	protected E entity;
	
	public void animateModel(E endimatedEntity) {}
	
	@Override
	public void setRotationAngles(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.entity = entity;
	}
	
	public void addCuboid(EndimatorModelRenderer cuboid) {
		this.cuboids.add(cuboid);
	}
	
	public void setDefaultBoxValues() {
		this.cuboids.forEach((rendererModel) -> {
			if(rendererModel instanceof EndimatorModelRenderer) {
				rendererModel.setDefaultBoxValues();
			}
		});
	}
	
	public void revertBoxesToDefaultValues() {
		this.cuboids.forEach((rendererModel) -> {
			if(rendererModel instanceof EndimatorModelRenderer) {
				rendererModel.revertToDefaultBoxValues();
			}
		});
	}
	
	public void createScaleController() {
		this.scaleController = new EndimatorModelRenderer(this, 0, 0);
		this.scaleController.showModel = false;
		this.scaleController.setRotationPoint(1, 1, 1);
	}
	
	public EndimatorModelRenderer getScaleController() {
		return this.scaleController;
	}
}