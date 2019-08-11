package com.teamabnormals.upgrade_aquatic.core.proxy;

import com.teamabnormals.upgrade_aquatic.client.render.RenderLionfish;
import com.teamabnormals.upgrade_aquatic.client.render.RenderNautilus;
import com.teamabnormals.upgrade_aquatic.client.render.RenderPike;
import com.teamabnormals.upgrade_aquatic.client.render.RenderThrasher;
import com.teamabnormals.upgrade_aquatic.client.tileentity.TileEntityElderEyeRenderer;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityLionfish;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityThrasher;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy {

	@Override
	public void preInit() {
		//Tile Entities
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityElderEye.class, new TileEntityElderEyeRenderer());
		
		//Entities
		RenderingRegistry.registerEntityRenderingHandler(EntityNautilus.class, manager -> new RenderNautilus(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityPike.class, manager -> new RenderPike(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityLionfish.class, manager -> new RenderLionfish(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityThrasher.class, manager -> new RenderThrasher(manager));
	}
	
}
