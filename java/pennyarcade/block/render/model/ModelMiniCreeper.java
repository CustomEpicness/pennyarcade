package pennyarcade.block.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMiniCreeper extends ModelBase {
	// fields
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer leg1;
	ModelRenderer leg2;

	public ModelMiniCreeper() {
		textureWidth = 32;
		textureHeight = 32;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 4, 4, 4);
		head.setRotationPoint(2F, 19F, 2F);
		head.setTextureSize(32, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 8, 8);
		body.addBox(-4F, 0F, -2F, 4, 6, 2);
		body.setRotationPoint(2F, 15F, 1F);
		body.setTextureSize(32, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 8);
		leg3.addBox(-2F, 0F, 1F, 2, 3, 2);
		leg3.setRotationPoint(0F, 21F, -4F);
		leg3.setTextureSize(32, 32);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 8);
		leg4.addBox(-2F, 0F, -2F, 2, 3, 2);
		leg4.setRotationPoint(2F, 21F, -1F);
		leg4.setTextureSize(32, 32);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 8);
		leg1.addBox(0F, 0F, -3F, 2, 3, 2);
		leg1.setRotationPoint(-2F, 21F, 4F);
		leg1.setTextureSize(32, 32);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 8);
		leg2.addBox(-2F, 0F, -2F, 2, 3, 2);
		leg2.setRotationPoint(2F, 21F, 3F);
		leg2.setTextureSize(32, 32);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		leg1.render(f5);
		leg2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
