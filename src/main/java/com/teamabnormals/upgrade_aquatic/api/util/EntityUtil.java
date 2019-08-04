package com.teamabnormals.upgrade_aquatic.api.util;

import java.util.Random;

import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
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
	
	@Deprecated
	public static Int2ObjectMap<VillagerTrades.ITrade[]> combineTradeMap(Int2ObjectMap<VillagerTrades.ITrade[]> originalMap, ImmutableMap<Integer, VillagerTrades.ITrade[]> newMap) {
		Int2ObjectOpenHashMap<VillagerTrades.ITrade[]> map = (Int2ObjectOpenHashMap<ITrade[]>) originalMap;
		map.putAll(newMap);
		return map;
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
	    
	    public ItemsForEmeraldsTrade(Block block, int stackSize, int p_i50528_3_, int p_i50528_4_, int p_i50528_5_) {
	    	this(new ItemStack(block), stackSize, p_i50528_3_, p_i50528_4_, p_i50528_5_);
	    }

	    public ItemsForEmeraldsTrade(Item item, int stackSize, int p_i50529_3_, int p_i50529_4_) {
	    	this(new ItemStack(item), stackSize, p_i50529_3_, 12, p_i50529_4_);
	    }

	    public ItemsForEmeraldsTrade(Item item, int stackSize, int p_i50530_3_, int p_i50530_4_, int priceMultiplier) {
	    	this(new ItemStack(item), stackSize, p_i50530_3_, p_i50530_4_, priceMultiplier);
	    }

	    public ItemsForEmeraldsTrade(ItemStack stack, int stackSize, int recievedSize, int maxUses, int givenExp) {
	    	this(stack, stackSize, recievedSize, maxUses, givenExp, 0.05F);
	    }

	    public ItemsForEmeraldsTrade(ItemStack stack, int stackSize, int p_i50532_3_, int p_i50532_4_, int p_i50532_5_, float p_i50532_6_) {
	    	this.itemstack = stack;
	    	this.stackSize = stackSize;
	    	this.recievedSize = p_i50532_3_;
	    	this.maxUses = p_i50532_4_;
	    	this.givenExp = p_i50532_5_;
	    	this.priceMultiplier = p_i50532_6_;
	    }

	    public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
	    	return new MerchantOffer(new ItemStack(Items.EMERALD, this.stackSize), new ItemStack(this.itemstack.getItem(), this.recievedSize), this.maxUses, this.givenExp, this.priceMultiplier);
	    }
	}
	
}
