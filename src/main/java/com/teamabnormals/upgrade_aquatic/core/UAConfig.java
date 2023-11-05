package com.teamabnormals.upgrade_aquatic.core;

import com.teamabnormals.blueprint.core.annotations.ConfigKey;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import org.apache.commons.lang3.tuple.Pair;

public class UAConfig {

	public static class Common {
		public final BooleanValue clericsBuyThrasherTeeth;
		public final BooleanValue drownedSwimmingAnimation;
		public final BooleanValue leatherworkersSellBedrolls;

		@ConfigKey("kelpy_ocean_ruins")
		public final BooleanValue kelpyOceanRuins;

		public final IntValue nautilusMaxSpawnHeight;

		public final IntValue thrasherMaxSpawnHeight;
		public final DoubleValue greatThrasherSpawnChance;
		public final DoubleValue thrasherDaytimeSpawnChance;

		public final BooleanValue renewableSand;
		public final BooleanValue renewableSandRequiresMagmaBlocks;
		public final BooleanValue renewableGravel;

		public Common(ForgeConfigSpec.Builder builder) {
			builder.push("items");
			builder.push("trades");
			this.clericsBuyThrasherTeeth = builder.define("Clerics buy thrasher teeth", true);
			this.leatherworkersSellBedrolls = builder.define("Leatherworkers sell bedrolls", true);
			builder.pop();
			builder.pop();

			builder.push("generation");
			builder.push("structures");
			this.kelpyOceanRuins = builder.comment("If Mossy Cobblestone and Stone Bricks are replaced with Kelpy Cobblestone and Stone Bricks in Ocean Ruins").define("Kelpy Ocean Ruins", true);
			builder.pop();
			builder.pop();

			builder.push("mobs");
			this.drownedSwimmingAnimation = builder.comment("Give Drowneds a swimming animation, like in bedrock edition").define("Drowned swimming animation", true);
			this.nautilusMaxSpawnHeight = builder.comment("The max height that Nautilus can spawn at").defineInRange("Nautilus max spawn height", 30, -64, 320);
			builder.push("thrasher");
			this.thrasherMaxSpawnHeight = builder.comment("The max height that Thrashers can spawn at").defineInRange("Thrasher max spawn height", 30, -64, 320);
			this.thrasherDaytimeSpawnChance = builder.comment("The chance of a Thrasher spawning during the daytime").defineInRange("Thrasher daytime spawn chance", 0.75D, 0.0D, 1.0D);
			this.greatThrasherSpawnChance = builder.comment("The chance a Thrasher has of spawning as a Great Thrasher in Deep Frozen Oceans").defineInRange("Great Thrasher spawn chance", 0.25D, 0.0D, 1.0D);
			builder.pop();
			builder.pop();

			builder.push("misc");
			builder.push("renewable_sand");
			this.renewableSand = builder.comment("If Sand variants should be renewable by placing their respective Sandstone above a Bubble Column").define("Renewable Sand", true);
			this.renewableSandRequiresMagmaBlocks = builder.comment("If 'drag' Bubble Columns from Magma Blocks should be required to renew Sand and Gravel").define("Renewable Sand requires Magma Blocks", true);
			this.renewableGravel = builder.comment("If Gravel should be renewable by placing Cobblestone above a Bubble Column").define("Renewable Gravel", true);
			builder.pop();
			builder.pop();
		}
	}

	public static class Client {
		public final BooleanValue showUnobtainableDescription;
		public final ConfigValue<Integer> daysTillRenderInsomniaOverlay;
		public final BooleanValue replaceGlowSquidRenderer;

		public Client(ForgeConfigSpec.Builder builder) {
			builder.push("misc");
			this.showUnobtainableDescription = builder.comment("If unimplemented items should show that they are unobtainable in their item description").define("Show unobtainable description", true);
			this.daysTillRenderInsomniaOverlay = builder.comment("The amount of days till the insomnia overlay is rendered", "Setting to 3 will make the overlay indicate phantom spawns", "Setting to 0 will disable the overlay").define("Days until insomnia overlay", 0);
			this.replaceGlowSquidRenderer = builder.comment("If Glow Squids should use our Glow Squid Renderer.").define("Replace Glow Squid Renderer", true);
			builder.pop();
		}
	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final Client CLIENT;

	static {
		Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = commonSpecPair.getRight();
		COMMON = commonSpecPair.getLeft();

		Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENT_SPEC = clientSpecPair.getRight();
		CLIENT = clientSpecPair.getLeft();
	}
}
