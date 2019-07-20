import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class phantom extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer wing0;
	private final ModelRenderer wingtip0;
	private final ModelRenderer wing1;
	private final ModelRenderer wingtip1;
	private final ModelRenderer head;
	private final ModelRenderer tail;
	private final ModelRenderer jaw;

	public phantom() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 8, -3.0F, -2.0F, -8.0F, 5, 3, 9, 0.0F, false));

		wing0 = new ModelRenderer(this);
		wing0.setRotationPoint(2.0F, -2.0F, -8.0F);
		body.addChild(wing0);
		wing0.cubeList.add(new ModelBox(wing0, 23, 12, 0.0F, 0.0F, 0.0F, 6, 2, 9, 0.0F, false));

		wingtip0 = new ModelRenderer(this);
		wingtip0.setRotationPoint(6.0F, 0.0F, 0.0F);
		wing0.addChild(wingtip0);
		wingtip0.cubeList.add(new ModelBox(wingtip0, 16, 24, 0.0F, 0.0F, 0.0F, 13, 1, 9, 0.0F, false));

		wing1 = new ModelRenderer(this);
		wing1.setRotationPoint(-3.0F, -2.0F, -8.0F);
		body.addChild(wing1);
		wing1.cubeList.add(new ModelBox(wing1, 23, 12, -6.0F, 0.0F, 0.0F, 6, 2, 9, 0.0F, true));

		wingtip1 = new ModelRenderer(this);
		wingtip1.setRotationPoint(-6.0F, 2.0F, 0.0F);
		wing1.addChild(wingtip1);
		wingtip1.cubeList.add(new ModelBox(wingtip1, 16, 24, -13.0F, -2.0F, 0.0F, 13, 1, 9, 0.0F, true));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 1.0F, -7.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 40, 0, -4.0F, -2.0F, -5.0F, 7, 4, 5, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -2.0F, 1.0F);
		body.addChild(tail);
		tail.cubeList.add(new ModelBox(tail, 0, 8, -3.0F, 0.0F, 0.0F, 5, 3, 9, 0.0F, false));
		tail.cubeList.add(new ModelBox(tail, 16, 39, -7.0F, 1.0F, 9.0F, 13, 1, 10, 0.0F, false));

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(jaw, 0.1745F, 0.0F, 0.0F);
		jaw.cubeList.add(new ModelBox(jaw, 0, 53, -4.0F, -26.0F, -9.0F, 7, 4, 5, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		jaw.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}