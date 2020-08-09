package com.minecraftabnormals.upgrade_aquatic.core.other;

import java.util.Map;
import java.util.function.Supplier;

import com.google.common.collect.Maps;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author SmellyModder(Luke Tonon)
 * Used to call methods to modify core game code with ASM
 */
public class UAHooks {
	private static final Map<Supplier<Block>, Supplier<Block>> FALLABLES = Util.make(Maps.newHashMap(), (fallables) -> {
		fallables.put(() -> Blocks.SANDSTONE, () -> Blocks.SAND);
		fallables.put(() -> Blocks.RED_SANDSTONE, () -> Blocks.RED_SAND);
		fallables.put(() -> Blocks.COBBLESTONE, () -> Blocks.GRAVEL);
	});
	private static final Map<Supplier<Block>, Supplier<Block>> ATMOSHPERIC_FALLABLES = ModList.get().isLoaded("atmospheric") ? Util.make(Maps.newHashMap(), (fallables) -> {
		fallables.put(() -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:arid_sandstone")), () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:arid_sand")));
		fallables.put(() -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:red_arid_sandstone")), () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:red_arid_sand")));
	}) : null;

	public static void addBubbleColumnBehaviors(BlockState state, ServerWorld world, BlockPos pos) {
		BlockPos abovePos = pos.up();
		Block aboveBlock = world.getBlockState(abovePos).getBlock();
		boolean noFallingBlockAbove = world.getEntitiesWithinAABB(FallingBlockEntity.class, new AxisAlignedBB(pos)).isEmpty();
		
		if(noFallingBlockAbove) {
			FALLABLES.forEach((inputBlock, outputBlock) -> {
				if(inputBlock.get() == aboveBlock) {
					spawnFallingBlock(world, pos, outputBlock.get());
				}
			});
			
			if(ATMOSHPERIC_FALLABLES != null) {
				ATMOSHPERIC_FALLABLES.forEach((inputBlock, outputBlock) -> {
					if(inputBlock.get() == aboveBlock) {
						spawnFallingBlock(world, pos, outputBlock.get());
					}
				});
			}
		}
	}
	
	private static void spawnFallingBlock(ServerWorld world, BlockPos pos, Block block) {
		FallingBlockEntity fallingblockentity = new FallingBlockEntity(world, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, block.getDefaultState());
		fallingblockentity.fallTime = 1;
		world.addEntity(fallingblockentity);
	}
	
	public static void makeBubbleColumnTickRandomly() {
		ObfuscationReflectionHelper.setPrivateValue(AbstractBlock.class, Blocks.BUBBLE_COLUMN, true, "field_149789_z");
	}
}