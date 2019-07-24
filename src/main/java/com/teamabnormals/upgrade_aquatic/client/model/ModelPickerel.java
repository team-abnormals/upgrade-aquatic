//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class salmon extends ModelBase {
	private final ModelRenderer body_front;
	private final ModelRenderer body_back;
	private final ModelRenderer dorsal;
	private final ModelRenderer tailfin;
	private final ModelRenderer head;
	private final ModelRenderer leftFin;
	private final ModelRenderer rightFin;

	public salmon() {
		textureWidth = 32;
		textureHeight = 32;

		body_front = new ModelRenderer(this);
		body_front.setRotationPoint(0.0F, 24.0F, -4.0F);
		body_front.cubeList.add(new ModelBox(body_front, 0, 0, -1.5F, -8.5F, 1.0F, 3, 5, 7, 0.0F, false));

		body_back = new ModelRenderer(this);
		body_back.setRotationPoint(0.0F, 0.0F, 8.0F);
		body_front.addChild(body_back);
		body_back.cubeList.add(new ModelBox(body_back, 0, 12, -1.5F, -8.5F, 0.0F, 3, 5, 7, 0.0F, false));

		dorsal = new ModelRenderer(this);
		dorsal.setRotationPoint(0.0F, -5.0F, 0.0F);
		body_back.addChild(dorsal);
		dorsal.cubeList.add(new ModelBox(dorsal, 24, 26, 0.0F, -5.5F, 5.0F, 0, 2, 4, 0.0F, false));

		tailfin = new ModelRenderer(this);
		tailfin.setRotationPoint(0.0F, 0.0F, 8.0F);
		body_back.addChild(tailfin);
		tailfin.cubeList.add(new ModelBox(tailfin, 22, 17, 0.0F, -8.5F, -1.0F, 0, 5, 5, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -3.0F, 0.0F);
		body_front.addChild(head);
		head.cubeList.add(new ModelBox(head, 20, 13, -1.5F, -4.5F, -2.0F, 3, 3, 3, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 28, -1.0F, -3.5F, -4.0F, 2, 2, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 8, 28, -1.5F, -1.5F, -2.0F, 3, 1, 3, 0.0F, false));

		leftFin = new ModelRenderer(this);
		leftFin.setRotationPoint(1.5F, -1.0F, 0.0F);
		setRotationAngle(leftFin, 0.0F, 0.0F, 0.6109F);
		body_front.addChild(leftFin);
		leftFin.cubeList.add(new ModelBox(leftFin, 11, 0, -2.0075F, -1.867F, 2.0F, 2, 0, 2, 0.0F, false));
		leftFin.cubeList.add(new ModelBox(leftFin, 0, 4, -2.0075F, -1.867F, 7.0F, 2, 0, 3, 0.0F, false));
		leftFin.cubeList.add(new ModelBox(leftFin, 13, 3, -2.0075F, -1.867F, 12.0F, 2, 0, 4, 0.0F, false));

		rightFin = new ModelRenderer(this);
		rightFin.setRotationPoint(-1.5F, -1.0F, 0.0F);
		setRotationAngle(rightFin, 0.0F, 0.0F, -0.6109F);
		body_front.addChild(rightFin);
		rightFin.cubeList.add(new ModelBox(rightFin, 15, 0, 0.0074F, -1.867F, 2.0F, 2, 0, 2, 0.0F, false));
		rightFin.cubeList.add(new ModelBox(rightFin, 0, 0, 0.0074F, -1.867F, 7.0F, 2, 0, 3, 0.0F, false));
		rightFin.cubeList.add(new ModelBox(rightFin, 9, 3, 0.0074F, -1.867F, 12.0F, 2, 0, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body_front.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}