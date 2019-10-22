package com.teamabnormals.upgrade_aquatic.core.registry;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelWeed;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelWeedDouble;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityLionfish;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityThrasher;
import com.teamabnormals.upgrade_aquatic.core.registry.util.RegistryUtils;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("rawtypes")
public class UAEntities {
	private static List<EntityType> entities = Lists.newArrayList();
	private static List<Item> spawnEggs = Lists.newArrayList();
	
	public static final EntityType<EntityNautilus> NAUTILUS = createEntity(EntityNautilus.class, EntityNautilus::new, EntityClassification.CREATURE, "nautilus", 0.5F, 0.5F, 14596231, 16744272);
	public static final EntityType<EntityPike> PIKE = createEntity(EntityPike.class, EntityPike::new, EntityClassification.CREATURE, "pike", 0.7F, 0.4F, 4806944, 13002040);
	public static final EntityType<EntityLionfish> LIONFISH = createEntity(EntityLionfish.class, EntityLionfish::new, EntityClassification.CREATURE, "lionfish", 0.6F, 0.5F, 15281931, 16111310);
	public static final EntityType<EntityThrasher> THRASHER = createEntity(EntityThrasher.class, EntityThrasher::new, EntityClassification.MONSTER, "thrasher", 1.6F, 1F, 7255507, 11730927);
	
	private static <T extends Entity> EntityType<T> createEntity(Class<T> entityClass, EntityType.IFactory<T> factory, EntityClassification entityClassification, String name, float width, float height, int eggPrimary, int eggSecondary) {
        ResourceLocation location = new ResourceLocation(Reference.MODID, name);
        
        EntityType<T> entity = EntityType.Builder.create(factory, entityClassification)
        	.size(width, height).setTrackingRange(64)
        	.setShouldReceiveVelocityUpdates(true)
        	.setUpdateInterval(3)
        	.build(location.toString());
        
        entity.setRegistryName(location);
        
        entities.add(entity);
        spawnEggs.add(RegistryUtils.createSpawnEggForEntity(entity, eggPrimary, eggSecondary, ItemGroup.MISC));

        return entity;
    }
    
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
    	for (EntityType entity : entities) {
    		event.getRegistry().register(entity);
    	}
    	EntitySpawnPlacementRegistry.register(NAUTILUS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, UAEntities::ravineMobCondition);
    	EntitySpawnPlacementRegistry.register(PIKE, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, UAEntities::pickerelCondition);
    	EntitySpawnPlacementRegistry.register(LIONFISH, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING, UAEntities::coralCondition);
    }

    @SubscribeEvent
    public static void registerSpawnEggs(RegistryEvent.Register<Item> event) {
        for (Item spawnEgg : spawnEggs) {
            event.getRegistry().register(spawnEgg);
        }
    }
    
    private static boolean ravineMobCondition(EntityType<? extends MobEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
    	if(world.getDimension().getType() != DimensionType.OVERWORLD) return false;
    	return pos.getY() <= 30;
    }
    
    private static boolean pickerelCondition(EntityType<? extends EntityPike> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
    	if(world.getDimension().getType() != DimensionType.OVERWORLD) return false;
    	for (int yy = pos.getY() - 2; yy <= pos.getY() + 2; yy++) {
			for (int xx = pos.getX() - 6; xx <= pos.getX() + 6; xx++) {
				for (int zz = pos.getZ() - 6; zz <= pos.getZ() + 6; zz++) {
					if(world.getBlockState(new BlockPos(xx, yy, zz)).getBlock() instanceof BlockPickerelWeed || world.getBlockState(new BlockPos(xx, yy, zz)).getBlock() instanceof BlockPickerelWeedDouble) {
						if(random.nextFloat() <= 0.25F)
							if(world.getBiome(pos).getCategory() == Category.SWAMP) {
								return random.nextFloat() <= 0.25 ? true : false;
							}
							return true;
					}
				}
			}
		}
		return random.nextFloat() <= 0.05F ? true : false;
    }
    
    private static boolean coralCondition(EntityType<? extends Entity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
    	if(world.getDimension().getType() != DimensionType.OVERWORLD) return false;
    	for (int yy = pos.getY() - 2; yy <= pos.getY() + 2; yy++) {
			for (int xx = pos.getX() - 6; xx <= pos.getX() + 6; xx++) {
				for (int zz = pos.getZ() - 6; zz <= pos.getZ() + 6; zz++) {
					if(world.getBlockState(new BlockPos(xx, yy, zz)).getBlock().isIn(BlockTags.CORAL_BLOCKS)) {
						return true;
					}
				}
			}
		}
    	return false;
    }
}
