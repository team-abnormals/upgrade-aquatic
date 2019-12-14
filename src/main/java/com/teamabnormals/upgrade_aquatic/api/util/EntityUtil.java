package com.teamabnormals.upgrade_aquatic.api.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;

/**
 * 
 * @author - SmellyModder(Luke Tonon)
 *
 */
public class EntityUtil {
	
	public static RayTraceResult rayTrace(Entity entity, double distance, float delta) {
		return entity.world.rayTraceBlocks(new RayTraceContext(
			entity.getEyePosition(delta),
			entity.getEyePosition(delta).add(entity.getLook(delta).scale(distance)),
			RayTraceContext.BlockMode.OUTLINE,
			RayTraceContext.FluidMode.NONE,
			entity
		));
	}
	
	/**
	 * Builder Class for Basic Emerald Trading
	 */
	public static class ItemsForEmeraldsTrade implements ITrade {
	    private final ItemStack itemstack;
	    private final int stackSize;
	    private final int recievedSize;
	    private final int maxUses;
	    private final int givenExp;
	    private final float priceMultiplier;
	    
	    public ItemsForEmeraldsTrade(Block block, int stackSize, int recievedSize, int maxUses, int givenExp) {
	    	this(new ItemStack(block), stackSize, recievedSize, maxUses, givenExp);
	    }

	    public ItemsForEmeraldsTrade(Item item, int stackSize, int recievedSize, int givenExp) {
	    	this(new ItemStack(item), stackSize, recievedSize, 12, givenExp);
	    }

	    public ItemsForEmeraldsTrade(Item item, int stackSize, int recievedSize, int maxUses, int givenExp) {
	    	this(new ItemStack(item), stackSize, recievedSize, maxUses, givenExp);
	    }

	    public ItemsForEmeraldsTrade(ItemStack stack, int stackSize, int recievedSize, int maxUses, int givenExp) {
	    	this(stack, stackSize, recievedSize, maxUses, givenExp, 0.05F);
	    }

	    public ItemsForEmeraldsTrade(ItemStack stack, int stackSize, int recievedSize, int maxUses, int givenExp, float priceMultiplier) {
	    	this.itemstack = stack;
	    	this.stackSize = stackSize;
	    	this.recievedSize = recievedSize;
	    	this.maxUses = maxUses;
	    	this.givenExp = givenExp;
	    	this.priceMultiplier = priceMultiplier;
	    }

	    public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
	    	return new MerchantOffer(new ItemStack(Items.EMERALD, this.stackSize), new ItemStack(this.itemstack.getItem(), this.recievedSize), this.maxUses, this.givenExp, this.priceMultiplier);
	    }
	}
	
	public static class EmeraldsForItemsTrade implements ITrade {
	    private final ItemStack itemstack;
	    private final int stackSize;
	    private final int recievedSize;
	    private final int maxUses;
	    private final int givenExp;
	    private final float priceMultiplier;
		
	    public EmeraldsForItemsTrade(Block block, int stackSize, int recievedSize, int maxUses, int givenExp) {
	    	this(new ItemStack(block), stackSize, recievedSize, maxUses, givenExp);
	    }

	    public EmeraldsForItemsTrade(Item item, int stackSize, int recievedSize, int givenExp) {
	    	this(new ItemStack(item), stackSize, recievedSize, 12, givenExp);
	    }

	    public EmeraldsForItemsTrade(Item item, int stackSize, int recievedSize, int maxUses, int givenExp) {
	    	this(new ItemStack(item), stackSize, recievedSize, maxUses, givenExp);
	    }

	    public EmeraldsForItemsTrade(ItemStack stack, int stackSize, int recievedSize, int maxUses, int givenExp) {
	    	this(stack, stackSize, recievedSize, maxUses, givenExp, 0.05F);
	    }
	    
	    public EmeraldsForItemsTrade(ItemStack stack, int stackSize, int recievedSize, int maxUses, int givenExp, float priceMultiplier) {
	    	this.itemstack = stack;
	        this.stackSize = stackSize;
	        this.recievedSize = recievedSize;
	        this.maxUses = maxUses;
	        this.givenExp = givenExp;
	        this.priceMultiplier = priceMultiplier;
	    }

	    public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
	    	return new MerchantOffer(new ItemStack(this.itemstack.getItem(), this.stackSize), new ItemStack(Items.EMERALD, this.recievedSize), this.maxUses, this.givenExp, this.priceMultiplier);
	    }
	}
	
	public static class ItemsForEmeraldsAndItemsTrade implements ITrade {
	    private final ItemStack buyingItem;
	    private final int buyingItemCount;
	    private final int emeraldCount;
	    private final ItemStack sellingItem;
	    private final int sellingItemCount;
	    private final int maxUses;
	    private final int givenExp;
	    private final float priceMultiplier;
	    
	    public ItemsForEmeraldsAndItemsTrade(Item item, int stackSize, Item item2, int recievedSize, int maxUses, int givenExp) {
	    	this(new ItemStack(item), stackSize, 1, new ItemStack(item2), recievedSize, maxUses, givenExp, 0.05F);
	    }

	    public ItemsForEmeraldsAndItemsTrade(ItemStack stack, int stackSize, int emeraldCount, ItemStack recieved, int recievedSize, int maxUses, int givenExp, float priceMultiplier) {
	    	this.buyingItem = stack;
	    	this.buyingItemCount = stackSize;
	    	this.emeraldCount = emeraldCount;
	    	this.sellingItem = recieved;
	    	this.sellingItemCount = recievedSize;
	    	this.maxUses = maxUses;
	    	this.givenExp = givenExp;
	    	this.priceMultiplier = 0.05F;
	    }
		
	    public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
	    	return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCount), new ItemStack(this.buyingItem.getItem(), this.buyingItemCount), new ItemStack(this.sellingItem.getItem(), this.sellingItemCount), this.maxUses, this.givenExp, this.priceMultiplier);
	    }
	}
}

