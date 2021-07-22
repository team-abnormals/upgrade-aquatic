package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.upgrade_aquatic.common.advancement.UACriteriaTriggers;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

@SuppressWarnings("deprecation")
public class MulberryVineBlock extends Block implements IForgeShearable, IGrowable {
	protected static final VoxelShape SHAPE = Block.box(5.0, 4.0, 5.0, 13.0, 16.0, 13.0);

	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);
	public static final BooleanProperty DOUBLE = BooleanProperty.create("double");

	
	public MulberryVineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(DOUBLE, false));
	}
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE, DOUBLE);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Vector3d Vector3d = state.getOffset(worldIn, pos);
		return SHAPE.move(Vector3d.x, Vector3d.y, Vector3d.z);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
	    return VoxelShapes.empty();
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockItemUseContext useContext) {
		return useContext.getItemInHand().getItem() == this.asItem() && !state.getValue(DOUBLE) && state.getValue(AGE) < 2 || super.canBeReplaced(state, useContext);
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean moving) {
		if (world.getBlockState(pos.above()) == Blocks.AIR.defaultBlockState()) {
			world.removeBlock(pos, false);
		}
	}    

	public int getLightBlock(BlockState state, IBlockReader worldIn, BlockPos pos) {
	    return 1;
	}
	
	@Override
	public boolean isValidBonemealTarget(IBlockReader arg0, BlockPos arg1, BlockState state, boolean arg3) {
		return state.getValue(AGE) < 4;
	}

	@Override
	public boolean isBonemealSuccess(World arg0, Random arg1, BlockPos arg2, BlockState state) {
		return state.getValue(AGE) < 4;
	}
	
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		int i = state.getValue(AGE);
		boolean flag = i == 4;
		if (!flag && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL) {
			return ActionResultType.PASS;
		} else if (player.getItemInHand(handIn).getItem() == Items.SHEARS && state.getValue(DOUBLE)) {
			player.getItemInHand(handIn).hurtAndBreak(1, player, (onBroken) -> { onBroken.broadcastBreakEvent(handIn); });
			worldIn.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			if (state.getValue(AGE) == 4) popResource(worldIn, pos, new ItemStack(UAItems.MULBERRY.get(), 1));
			worldIn.setBlock(pos, state.setValue(DOUBLE, false), 2);
			return ActionResultType.SUCCESS;
		} else if (flag) {
			popResource(worldIn, pos, new ItemStack(UAItems.MULBERRY.get(), state.getValue(DOUBLE) ? 2 : 1));
			worldIn.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlock(pos, state.setValue(AGE, 1), 2);
			
			if (player instanceof ServerPlayerEntity && player.isAlive()) {
    			ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
    			if(!player.level.isClientSide()) {
    				UACriteriaTriggers.PICK_MULBERRIES.trigger(serverPlayer); 
    			}
    		}
			
			return ActionResultType.SUCCESS;
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}
	
	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		int i = state.getValue(AGE);
		if (i < 4 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
			worldIn.setBlockAndUpdate(pos, state.setValue(AGE, i + 1));
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);
		int i = state.getValue(AGE);
		if (i < 4 && worldIn.getRawBrightness(pos.above(), 0) >= 7 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0)) {
			worldIn.setBlock(pos, state.setValue(AGE, i + 1), 2);
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	    if (worldIn.isRainingAt(pos.above())) {
	        if (rand.nextInt(15) == 1) {
	            BlockPos blockpos = pos.below();
	            BlockState blockstate = worldIn.getBlockState(blockpos);
	            if (!blockstate.canOcclude() || !blockstate.isFaceSturdy(worldIn, blockpos, Direction.UP)) {
	                double d0 = ((float)pos.getX() + rand.nextFloat());
	                double d1 = pos.getY() - 0.05D;
	                double d2 = ((float)pos.getZ() + rand.nextFloat());
	                worldIn.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	            }
	        }
	    }
	}

	@Override
	public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
	    return true;
	}

	protected boolean isStateValid(World worldIn, BlockPos pos) {
	    Block block = worldIn.getBlockState(pos.above()).getBlock();
	    return block.is(BlockTags.LEAVES) || block.is(BlockTags.LOGS);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public float getShadeBrightness(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1.0F;
	}
	
	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}
	
	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
	    World world = context.getLevel();
	    BlockPos pos = context.getClickedPos();
	    BlockState state = context.getLevel().getBlockState(context.getClickedPos());
		if (state.getBlock() == this && !state.getValue(DOUBLE) && state.getValue(AGE) < 2) {
			return state.setValue(DOUBLE, true);
		}
	    if (isStateValid(world, pos)) {
	        return defaultBlockState();
	    }
	    return null;
	}
	
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}
}


