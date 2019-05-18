package com.teamabnormals.upgrade_aquatic.core.registry;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

public class UATileEntities {

	@ObjectHolder(Reference.ELDER_EYE)
	public static TileEntityType<?> ELDER_EYE;
	
	@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegisterTileEntities {
		
        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
            registerTileEntityType(event.getRegistry(), register("iron_chest", TileEntityType.Builder.create(TileEntityElderEye::new)), Reference.ELDER_EYE);
        }
        
    }

    private static <T extends TileEntityType<?>> T registerTileEntityType(IForgeRegistry<TileEntityType<?>> registry, T tileEntityType, String name) {
        register(registry, tileEntityType, new ResourceLocation(name));
        return tileEntityType;
    }

    private static <T extends IForgeRegistryEntry<T>> T register(IForgeRegistry<T> registry, T thing, ResourceLocation name) {
        thing.setRegistryName(name);
        registry.register(thing);
        return thing;
    }

    private static <T extends TileEntity> TileEntityType<T> register(String id, TileEntityType.Builder<T> builder) {
        Type<?> type = null;
        try {
        	type = DataFixesManager.getDataFixer().getSchema(DataFixUtils.makeKey(1519)).getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        } catch (IllegalArgumentException illegalstateexception) {
            
        	if (SharedConstants.developmentMode) {
                throw illegalstateexception;
            }
        	
        }
        TileEntityType<T> tileEntityType = builder.build(type);
        return tileEntityType;
    }
}
