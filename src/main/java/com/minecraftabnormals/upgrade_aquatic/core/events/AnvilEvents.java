package com.minecraftabnormals.upgrade_aquatic.core.events;

import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class AnvilEvents {

	@SubscribeEvent
	public static void onAnvilUpdate(AnvilUpdateEvent event) {
		if(event.getLeft().getItem() == Items.TRIDENT && event.getRight().getItem() == UAItems.THRASHER_TOOTH.get()) {
			ItemStack leftItem = event.getLeft();
			ItemStack cloneLeftStack = event.getLeft().copy();
			ItemStack rightItem = event.getRight();
			int i = 0;
			int j = 0;
			int k = 0;
			boolean flag = rightItem.getItem() == Items.ENCHANTED_BOOK && !EnchantedBookItem.getEnchantments(rightItem).isEmpty();
			int l2 = Math.min(cloneLeftStack.getDamageValue(), cloneLeftStack.getMaxDamage() / 4);
			if (l2 <= 0) {
				event.setOutput(ItemStack.EMPTY);
				event.setCost(0);
				return;
			}

			int i3;
			for(i3 = 0; l2 > 0 && i3 < rightItem.getCount(); ++i3) {
				int j3 = cloneLeftStack.getDamageValue() - l2;
				cloneLeftStack.setDamageValue(j3);
				i++;
				l2 = Math.min(cloneLeftStack.getDamageValue(), cloneLeftStack.getMaxDamage() / 4);
			}
			event.setMaterialCost(i3);
            
			if (StringUtils.isBlank(event.getName())) {
				if (leftItem.hasCustomHoverName()) {
					k = 1;
					i += k;
					cloneLeftStack.resetHoverName();
				}
			} else if (!event.getName().equals(leftItem.getHoverName().getString())) {
				k = 1;
				i += k;
				cloneLeftStack.setHoverName(new StringTextComponent(event.getName()));
			}
			if (flag && !cloneLeftStack.isBookEnchantable(rightItem)) cloneLeftStack = ItemStack.EMPTY;

			event.setCost(j + i);
			if (i <= 0) {
				cloneLeftStack = ItemStack.EMPTY;
			}

			if (k == i && k > 0 && event.getCost() >= 40) {
				event.setCost(39);
			}

			if (event.getCost() >= 40) {
				cloneLeftStack = ItemStack.EMPTY;
			}

			if (!cloneLeftStack.isEmpty()) {
				int k2 = cloneLeftStack.getBaseRepairCost();
				if (!rightItem.isEmpty() && k2 < rightItem.getBaseRepairCost()) {
					k2 = rightItem.getBaseRepairCost();
				}

				if (k != i || k == 0) {
					k2 = k2 * 2 + 1;
				}

				cloneLeftStack.setRepairCost(k2);
				Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(cloneLeftStack);
				EnchantmentHelper.setEnchantments(map, cloneLeftStack);
                
				event.setOutput(cloneLeftStack);
			}
		}
	}
	
}
