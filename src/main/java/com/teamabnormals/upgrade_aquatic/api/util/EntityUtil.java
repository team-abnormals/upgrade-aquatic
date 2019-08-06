package com.teamabnormals.upgrade_aquatic.api.util;

import java.util.Random;

import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;

/**
 * 
 * @author - SmellyModder(Luke Tonon)
 *
 */
public class EntityUtil {

	/**
	 * Used to create a new trade map for a villager's trade list
	 * @param ImmutableMap<Integer, VillagerTrades.ITrade[]> map - A immutable map of an integer and array of trades
	 * Example: ImmutableMap.of(1, new VillagerTrades.ITrade[] {
	 *     new EntityUtil.ItemsForEmeraldsTrade(Items.SEA_PICKLE, 2, 1, 5, 1)
	 * })
	 */
	public static Int2ObjectMap<VillagerTrades.ITrade[]> newTradeMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> map) {
		return new Int2ObjectOpenHashMap<>(map);
	}
	
	/**
	 * Builder Class for Basic Emerald Trading
	 */
	public static class ItemsForEmeraldsTrade implements VillagerTrades.ITrade {
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
	
}
