package com.teamabnormals.upgrade_aquatic.api.endimator;

import javax.annotation.Nullable;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * PROPERTY OF ENDERGETIC AND MINECRAFT ABNORMALS MODDING
 * Much like the vanilla RendererModel but can store data of default values and has some more advanced features;
 * Such as setting an individual RendererModel's opacity, scale, and texture position
 * 
 * @author - SmellyModder(Luke Tonon)
 */
@OnlyIn(Dist.CLIENT)
public class EndimatorRendererModel extends RendererModel {
	public float defaultRotationPointX, defaultRotationPointY, defaultRotationPointZ;
	public float defaultRotateAngleX, defaultRotateAngleY, defaultRotateAngleZ;
	public float defaultOffsetX, defaultOffsetY, defaultOffsetZ;
	public int textureOffsetX, textureOffsetY;
	public boolean scaleChildren = true;
	public float[] scales = {1.0F, 1.0F, 1.0F};
	public float opacity = 1.0F;
	private boolean compiled;
	private int displayList;
	@Nullable
	private EndimatorRendererModel parentRendererModel;

	/**
	 * @param entityModel - Entity model this RendererModel belongs to
	 */
	public EndimatorRendererModel(EndimatorEntityModel<? extends Entity> entityModel) {
		super(entityModel);
	}
	
	/**
	 * Basic constructor
	 * @param entityModel - Entity model this RendererModel belongs to
	 * @param name - Name of this RendererModel
	 */
	public EndimatorRendererModel(EndimatorEntityModel<? extends Entity> entityModel, String name) {
		super(entityModel, name);
	}
	
	/**
	 * Texture offset constuctor 
	 * @param entityModel - Entity model this RendererModel belongs to
	 * @param textureOffsetX - X offset on the texture
	 * @param textureOffsetY - Y offset on the texture
	 */
	public EndimatorRendererModel(EndimatorEntityModel<? extends Entity> entityModel, int textureOffsetX, int textureOffsetY) {
		this(entityModel);
		this.setTextureOffset(textureOffsetX, textureOffsetY);
	}
	
	@Override
	public RendererModel func_217178_a(String boxName, float offsetX, float offsetY, float offsetZ, int width, int height, int depth, float delta, int textureOffsetX, int textureOffsetY) {
		boxName = this.boxName + "." + boxName;
		this.setTextureOffset(textureOffsetX, textureOffsetY);
		this.cubeList.add((new ModelBox(this, this.textureOffsetX, this.textureOffsetY, offsetX, offsetY, offsetZ, width, height, depth, 0.0F)).setBoxName(boxName));
		return this;
	}
	
	@Override
	public RendererModel addBox(float offsetX, float offsetY, float offsetZ, int width, int height, int depth) {
		this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, offsetX, offsetY, offsetZ, width, height, depth, 0.0F));
		return this;
	}
	
	@Override
	public RendererModel addBox(float offsetX, float offsetY, float offsetZ, int width, int height, int depth, boolean mirrored) {
		this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, offsetX, offsetY, offsetZ, width, height, depth, 0.0F, mirrored));
		return this;
	}
	
	/**
	 * Creates a simple textured box
	 * @see RendererModel#addBox(float, float, float, int, int, int, float)
	 */
	@Override
    public void addBox(float offsetX, float offsetY, float offsetZ, int width, int height, int depth, float scaleFactor) {
		this.cubeList.add(new ModelBox(this, this.textureOffsetX, this.textureOffsetY, offsetX, offsetY, offsetZ, width, height, depth, scaleFactor));
    }

	/**
	 * A method that sets the default box's values
	 * 
	 * Should be called after all the boxes in an entity model have been initialized
	 * 
	 * @see ModelAdolescentBooflo
	 */
	public void setDefaultBoxValues() {
		this.defaultRotationPointX = this.rotationPointX;
		this.defaultRotationPointY = this.rotationPointY;
		this.defaultRotationPointZ = this.rotationPointZ;
		
		this.defaultRotateAngleX = this.rotateAngleX;
		this.defaultRotateAngleY = this.rotateAngleY;
		this.defaultRotateAngleZ = this.rotateAngleZ;
		
		this.defaultOffsetX = this.offsetX;
		this.defaultOffsetY = this.offsetY;
		this.defaultOffsetZ = this.offsetZ;
	}
	
	/**
	 * A method that reverts the current box's values back to the default values
	 * 
	 * Should be called before applying further rotations and/or animations
	 * 
	 * @see ModelAdolescentBooflo#setRotationAngles()
	 */
	public void revertToDefaultBoxValues() {
		this.rotationPointX = this.defaultRotationPointX;
		this.rotationPointY = this.defaultRotationPointY;
		this.rotationPointZ = this.defaultRotationPointZ;
		
		this.rotateAngleX = this.defaultRotateAngleX;
		this.rotateAngleY = this.defaultRotateAngleY;
		this.rotateAngleZ = this.defaultRotateAngleZ;
		
		this.offsetX = this.defaultOffsetX;
		this.offsetY = this.defaultOffsetY;
		this.offsetZ = this.defaultOffsetZ;
	}
	
	/**
	 * @param 
	 */
	public void setScale(float x, float y, float z) {
		this.scales[0] = x;
		this.scales[1] = y;
		this.scales[2] = z;
	}
	
	/**
	 * Sets the scale of the X axis on this RendererModel
	 * @param scaleX - Value of scale
	 */
	public void setScaleX(float scaleX) {
		this.scales[0] = scaleX;
	}
	
	/**
	 * Sets the scale of the Y axis on this RendererModel
	 * @param scaleY - Value of scale
	 */
	public void setScaleY(float scaleY) {
		this.scales[1] = scaleY;
	}
	
	/**
	 * Sets the scale of the Z axis on this RendererModel
	 * @param scaleZ - Value of scale
	 */
	public void setScaleZ(float scaleZ) {
		this.scales[2] = scaleZ;
	}
	
	/**
	 * Sets the opacity of this RendererModel
	 * @param opacity - Value of opacity; shouldn't exceed 1.0
	 */
	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}
	
	/**
	 * Sets the parent RendererModel of this RendererModel
	 * @param parentRendererModel - The parent RendererModel
	 */
	public void setParentRendererModel(@Nullable EndimatorRendererModel parentRendererModel) {
		this.parentRendererModel = parentRendererModel;
	}
	
	public void setShouldScaleChildren(boolean scaleChildren) {
		this.scaleChildren = scaleChildren;
	}
	
	/**
	 * Performs the same function as vanilla's setTextureOffset
	 */
	@Override
	public EndimatorRendererModel setTextureOffset(int x, int y) {
		this.textureOffsetX = x;
		this.textureOffsetY = y;
		return this;
	}
	
	/**
	 * Performs the same function as vanilla's addChild but adjusted to fit EndimatorRendererModel
	 */
	@Override
	public void addChild(RendererModel rendererModel) {
		super.addChild(rendererModel);
		EndimatorRendererModel rendererModelChild = (EndimatorRendererModel) rendererModel;
		rendererModelChild.setParentRendererModel(this);
	}
	
	@Override
	public void render(float scale) {
		if(!this.isHidden) {
			if(this.showModel) {
				GlStateManager.pushMatrix();
				if(!this.compiled) {
					this.compileDisplayList(scale);
				}
				GlStateManager.translatef(this.offsetX, this.offsetY, this.offsetZ);
				GlStateManager.translatef(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);
				
				if(this.rotateAngleZ != 0.0F) {
					GlStateManager.rotatef((float) Math.toDegrees(this.rotateAngleZ), 0.0F, 0.0F, 1.0F);
				}
				if(this.rotateAngleY != 0.0F) {
	                GlStateManager.rotatef((float) Math.toDegrees(this.rotateAngleY), 0.0F, 1.0F, 0.0F);
				}
				if(this.rotateAngleX != 0.0F) {
					GlStateManager.rotatef((float) Math.toDegrees(this.rotateAngleX), 1.0F, 0.0F, 0.0F);
				}
				if(this.scales[0] != 1.0F || this.scales[1] != 1.0F || this.scales[2] != 1.0F) {
					GlStateManager.scalef(this.scales[0], this.scales[1], this.scales[2]);
				}
				
				if(this.opacity < 1.0F) {
					GlStateManager.enableBlend();
					GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
					GlStateManager.color4f(1F, 1F, 1F, this.opacity);
				}
				
				GlStateManager.callList(this.displayList);
				
				if(this.opacity < 1.0F) {
					GlStateManager.disableBlend();
					GlStateManager.color4f(1F, 1F, 1F, 1F);
				}
				
				if(!this.scaleChildren && (this.scales[0] != 1.0F || this.scales[1] != 1.0F || this.scales[2] != 1.0F)) {
					GlStateManager.popMatrix();
					GlStateManager.pushMatrix();
					GlStateManager.translatef(this.offsetX, this.offsetY, this.offsetZ);
					GlStateManager.translatef(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);
					if(this.rotateAngleZ != 0.0F) {
						GlStateManager.rotatef((float) Math.toDegrees(this.rotateAngleZ), 0.0F, 0.0F, 1.0F);
					}
					if(this.rotateAngleY != 0.0F) {
						GlStateManager.rotatef((float) Math.toDegrees(this.rotateAngleY), 0.0F, 1.0F, 0.0F);
					}
					if(this.rotateAngleX != 0.0F) {
						GlStateManager.rotatef((float) Math.toDegrees(this.rotateAngleX), 1.0F, 0.0F, 0.0F);
					}
				}
				
				if(this.childModels != null) {
					for(RendererModel childModel : this.childModels) {
						childModel.render(scale);
					}
				}
				GlStateManager.popMatrix();
			}
		}
	}
	
	private void compileDisplayList(float scale) {
		this.displayList = GLAllocation.generateDisplayLists(1);
		GlStateManager.newList(this.displayList, 4864);
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();

		for(int i = 0; i < this.cubeList.size(); ++i) {
			this.cubeList.get(i).render(bufferbuilder, scale);
		}

		GlStateManager.endList();
		this.compiled = true;
	}
}
