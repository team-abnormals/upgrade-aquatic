package com.teamabnormals.upgrade_aquatic.common.entities;

import com.teamabnormals.upgrade_aquatic.api.entity.EntityBucketableWaterMob;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityNautilus extends EntityBucketableWaterMob {
    private static final DataParameter<Boolean> MOVING = EntityDataManager.createKey(EntityNautilus.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> FLEEING = EntityDataManager.createKey(EntityNautilus.class, DataSerializers.BOOLEAN);

    public EntityNautilus(EntityType<? extends EntityNautilus> type, World worldIn) {
        super(type, worldIn);
        this.moveController = new EntityNautilus.MoveHelperController(this);
    }

    public EntityNautilus(World world, double posX, double posY, double posZ) {
        this(UAEntities.NAUTILUS, world);
        this.setPosition(posX, posY, posZ);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.65D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<PlayerEntity>(this, PlayerEntity.class, 9.0F, 1.5D, 1.2D, EntityPredicates.NOT_SPECTATING::test) {

            @Override
            public void startExecuting() {
                ((EntityNautilus) this.entity).setFleeing(true);
                super.startExecuting();
            }

            @Override
            public void resetTask() {
                ((EntityNautilus) this.entity).setFleeing(false);
                super.resetTask();
            }

        });
        this.goalSelector.addGoal(2, new AvoidEntityGoal<SquidEntity>(this, SquidEntity.class, 9.0F, 1.5D, 1.2D, EntityPredicates.NOT_SPECTATING::test) {

            @Override
            public void startExecuting() {
                ((EntityNautilus) this.entity).setFleeing(true);
                super.startExecuting();
            }

            @Override
            public void resetTask() {
                ((EntityNautilus) this.entity).setFleeing(false);
                super.resetTask();
            }

        });
        this.goalSelector.addGoal(2, new AvoidEntityGoal<DrownedEntity>(this, DrownedEntity.class, 9.0F, 1.5D, 1.2D, EntityPredicates.NOT_SPECTATING::test) {

            @Override
            public void startExecuting() {
                ((EntityNautilus) this.entity).setFleeing(true);
                super.startExecuting();
            }

            @Override
            public void resetTask() {
                ((EntityNautilus) this.entity).setFleeing(false);
                super.resetTask();
            }

        });
        this.goalSelector.addGoal(4, new EntityNautilus.SwimGoal(this));
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        return new SwimmerPathNavigator(this, worldIn);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(MOVING, false);
        this.dataManager.register(FLEEING, false);
    }

    public boolean isFleeing() {
        return this.dataManager.get(FLEEING);
    }

    public void setFleeing(boolean p_203706_1_) {
        this.dataManager.set(FLEEING, p_203706_1_);
    }

    public boolean isMoving() {
        return this.dataManager.get(MOVING);
    }

    public void setMoving(boolean p_203706_1_) {
        this.dataManager.set(MOVING, p_203706_1_);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Moving", this.isMoving());
        compound.putBoolean("Fleeing", this.isMoving());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setMoving(compound.getBoolean("Moving"));
        this.setMoving(compound.getBoolean("Fleeing"));
    }

    public ItemStack getBucket() {
        return new ItemStack(UAItems.NAUTILUS_BUCKET.get());
    }

    @Override
    public void livingTick() {
        if (this.isMoving() && this.isInWater() && this.eyesInWater) {
            Vec3d vec3d1 = this.getLook(0.0F);

            if (this.getEntityWorld().getGameTime() % 2 == 0) {
                this.world.addParticle(ParticleTypes.BUBBLE, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.getWidth() - vec3d1.x * 0.75D, this.posY + this.rand.nextDouble() * (double) this.getHeight() - vec3d1.y * 1D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.getWidth() - vec3d1.z * 0.75D, 0.0D, 0.0D, 0.0D);
            }
        }
        super.livingTick();
    }

    public void travel(Vec3d p_213352_1_) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(0.01F, p_213352_1_);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.9D));
            if (this.getAttackTarget() == null) {
                this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(p_213352_1_);
        }
    }

    @Override
    public void onEnterBubbleColumn(boolean downwards) {
    }

    @Override
    public void onEnterBubbleColumnWithAirAbove(boolean downwards) {
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.65F;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 8;
    }

    public static void addSpawn() {
		ForgeRegistries.BIOMES.getValues().stream().forEach(EntityNautilus::processSpawning);
	}
	
	private static void processSpawning(Biome biome) {
		if(biome.getCategory() == Category.OCEAN && biome.getPrecipitation() != RainType.SNOW) {
			biome.getSpawns(EntityClassification.WATER_CREATURE).add(new Biome.SpawnListEntry(UAEntities.NAUTILUS, 51, 1, 4));
        }
	}
    
    static class MoveHelperController extends MovementController {
        private final EntityNautilus nautilus;

        MoveHelperController(EntityNautilus nautilus) {
            super(nautilus);
            this.nautilus = nautilus;
        }

        public void tick() {
            if (this.nautilus.areEyesInFluid(FluidTags.WATER)) {
                this.nautilus.setMotion(this.nautilus.getMotion().add(0.0D, 0.005D, 0.0D));
            }

            if (this.action == MovementController.Action.MOVE_TO && !this.nautilus.getNavigator().noPath()) {
                double d0 = this.posX - this.nautilus.posX;
                double d1 = this.posY - this.nautilus.posY;
                double d2 = this.posZ - this.nautilus.posZ;
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.nautilus.rotationYaw = this.limitAngle(this.nautilus.rotationYaw, f, 90.0F);
                this.nautilus.renderYawOffset = this.nautilus.rotationYaw;
                float f1 = (float) (this.speed * this.nautilus.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
                this.nautilus.setAIMoveSpeed(MathHelper.lerp(0.125F, this.nautilus.getAIMoveSpeed(), f1));
                this.nautilus.setMotion(this.nautilus.getMotion().add(0.0D, (double)this.nautilus.getAIMoveSpeed() * d1 * 0.03D, 0.0D));
                nautilus.setMoving(true);
            } else {
            	nautilus.setMoving(false);
            }
        }
    }

    static class SwimGoal extends RandomSwimmingGoal {
        @SuppressWarnings("unused")
        private final EntityNautilus nautilus;

        public SwimGoal(EntityNautilus nautilus) {
            super(nautilus, 1.0D, 30);
            this.nautilus = nautilus;
        }

        public boolean shouldExecute() {
            return super.shouldExecute();
        }
    }
}