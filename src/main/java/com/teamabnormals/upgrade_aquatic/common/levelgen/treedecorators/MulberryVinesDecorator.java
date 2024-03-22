package com.teamabnormals.upgrade_aquatic.common.levelgen.treedecorators;

import com.mojang.serialization.Codec;
import com.teamabnormals.upgrade_aquatic.common.block.MulberryVineBlock;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class MulberryVinesDecorator extends TreeDecorator {
	public static final Codec<MulberryVinesDecorator> CODEC;
	public static final MulberryVinesDecorator INSTANCE = new MulberryVinesDecorator();

	@Override
	protected TreeDecoratorType<?> type() {
		return UAFeatures.MULBERRY_VINES.get();
	}

	@Override
	public void place(Context context) {
		RandomSource random = context.random();
		LevelSimulatedReader level = context.level();
		for (BlockPos pos : context.leaves()) {
			BlockPos belowPos = pos.below();
			if (level.isStateAtPosition(belowPos, BlockState::isAir)) {
				BlockState state = UABlocks.MULBERRY_VINE.get().defaultBlockState().setValue(MulberryVineBlock.AGE, 4).setValue(MulberryVineBlock.DOUBLE, random.nextBoolean());
				if (level instanceof LevelReader levelReader && state.canSurvive(levelReader, belowPos) && random.nextInt(5) == 0) {
					context.setBlock(belowPos, state);
				}
			}
		}
	}

	static {
		CODEC = Codec.unit(() -> INSTANCE);
	}
}