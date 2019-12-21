package com.teamabnormals.upgrade_aquatic.core.proxy;

import com.teamabnormals.upgrade_aquatic.client.render.RenderFlare;
import com.teamabnormals.upgrade_aquatic.client.render.RenderGreatThrasher;
import com.teamabnormals.upgrade_aquatic.client.render.RenderLionfish;
import com.teamabnormals.upgrade_aquatic.client.render.RenderNautilus;
import com.teamabnormals.upgrade_aquatic.client.render.RenderPike;
import com.teamabnormals.upgrade_aquatic.client.render.RenderSonarWave;
import com.teamabnormals.upgrade_aquatic.client.render.RenderThrasher;
import com.teamabnormals.upgrade_aquatic.client.render.RenderUABoat;
import com.teamabnormals.upgrade_aquatic.client.tileentity.TileEntityElderEyeRenderer;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityFlare;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityLionfish;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityUABoat;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntityGreatThrasher;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntitySonarWave;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy {

	@Override
	public void preInit() {
		//Tile Entities
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityElderEye.class, new TileEntityElderEyeRenderer());
		
		//Entities
		RenderingRegistry.registerEntityRenderingHandler(EntityNautilus.class, RenderNautilus::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPike.class, RenderPike::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityLionfish.class, RenderLionfish::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityThrasher.class, RenderThrasher::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGreatThrasher.class, RenderGreatThrasher::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityUABoat.class, RenderUABoat::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityFlare.class, RenderFlare::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySonarWave.class, RenderSonarWave::new);
	}
	
}
