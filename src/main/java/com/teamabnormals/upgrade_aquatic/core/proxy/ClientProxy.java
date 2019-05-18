package com.teamabnormals.upgrade_aquatic.core.proxy;

import com.teamabnormals.upgrade_aquatic.client.render.tileentity.TileEntityElderEyeRenderer;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends ServerProxy {

	@Override
	public void preInit() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityElderEye.class, new TileEntityElderEyeRenderer());
	}
	
}
