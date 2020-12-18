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
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings("deprecation")
public class MulberryVineBlock extends Block implements IForgeShearable, IGrowable {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0, 4.0, 5.0, 13.0, 16.0, 13.0);

	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);
	public static final BooleanProperty DOUBLE = BooleanProperty.create("double");

	
	public MulberryVineBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0).with(DOUBLE, false));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE, DOUBLE);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Vector3d Vector3d = state.getOffset(worldIn, pos);
		return SHAPE.withOffset(Vector3d.x, Vector3d.y, Vector3d.z);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
	    return VoxelShapes.empty();
	}

	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		return useContext.getItem().getItem() == this.asItem() && !state.get(DOUBLE) && state.get(AGE) < 2 || super.isReplaceable(state, useContext);
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean moving) {
		if (world.getBlockState(pos.up()) == Blocks.AIR.getDefaultState()) {
			world.removeBlock(pos, false);
		}
	}    

	public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
	    return 1;
	}
	
	@Override
	public boolean canGrow(IBlockReader arg0, BlockPos arg1, BlockState state, boolean arg3) {
		return state.get(AGE) < 4;
	}

	@Override
	public boolean canUseBonemeal(World arg0, Random arg1, BlockPos arg2, BlockState state) {
		return state.get(AGE) < 4;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		int i = state.get(AGE);
		boolean flag = i == 4;
		if (!flag && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) {
			return ActionResultType.PASS;
		} else if (player.getHeldItem(handIn).getItem() == Items.SHEARS && state.get(DOUBLE)) {
			player.getHeldItem(handIn).damageItem(1, player, (onBroken) -> { onBroken.sendBreakAnimation(handIn); });
			worldIn.playSound(null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			if (state.get(AGE) == 4) spawnAsEntity(worldIn, pos, new ItemStack(UAItems.MULBERRY.get(), 1));
			worldIn.setBlockState(pos, state.with(DOUBLE, false), 2);
			return ActionResultType.SUCCESS;
		} else if (flag) {
			spawnAsEntity(worldIn, pos, new ItemStack(UAItems.MULBERRY.get(), state.get(DOUBLE) ? 2 : 1));
			worldIn.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
			worldIn.setBlockState(pos, state.with(AGE, 1), 2);
			
			if (player instanceof ServerPlayerEntity && player.isAlive()) {
    			ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
    			if(!player.world.isRemote()) {
    				UACriteriaTriggers.PICK_MULBERRIES.trigger(serverPlayer); 
    			}
    		}
			
			return ActionResultType.SUCCESS;
		} else {
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}
	
	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		int i = state.get(AGE);
		if (i < 4 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
			worldIn.setBlockState(pos, state.with(AGE, i + 1));
			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);	
		}
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);
		int i = state.get(AGE);
		if (i < 4 && worldIn.getLightSubtracted(pos.up(), 0) >= 7 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0)) {
			worldIn.setBlockState(pos, state.with(AGE, i + 1), 2);
			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	    if (worldIn.isRainingAt(pos.up())) {
	        if (rand.nextInt(15) == 1) {
	            BlockPos blockpos = pos.down();
	            BlockState blockstate = worldIn.getBlockState(blockpos);
	            if (!blockstate.isSolid() || !blockstate.isSolidSide(worldIn, blockpos, Direction.UP)) {
	                double d0 = (double)((float)pos.getX() + rand.nextFloat());
	                double d1 = (double)pos.getY() - 0.05D;
	                double d2 = (double)((float)pos.getZ() + rand.nextFloat());
	                worldIn.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	            }
	        }
	    }
	}

	@Override
	public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
	    return true;
	}

	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
	    return false;
	}

	protected boolean isStateValid(World worldIn, BlockPos pos) {
	    Block block = worldIn.getBlockState(pos.up()).getBlock();
	    return block.isIn(BlockTags.LEAVES) || block.isIn(BlockTags.LOGS);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1.0F;
	}
	
	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}
	
	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
	    World world = context.getWorld();
	    BlockPos pos = context.getPos();
	    BlockState state = context.getWorld().getBlockState(context.getPos());
		if (state.getBlock() == this && !state.get(DOUBLE) && state.get(AGE) < 2) {
			return state.with(DOUBLE, true);
		}
	    if (isStateValid(world, pos)) {
	        return getDefaultState();
	    }
	    return null;
	}
	
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}
}


