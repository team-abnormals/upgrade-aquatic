package com.minecraftabnormals.upgrade_aquatic.core;

import com.minecraftabnormals.abnormals_core.core.annotations.ConfigKey;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class UAConfig {

	public static class Common {

		@ConfigKey("clerics_buy_thrasher_teeth")
		public final ConfigValue<Boolean> clericsBuyThrasherTeeth;

		@ConfigKey("drowned_has_swimming_animation")
		public final ConfigValue<Boolean> drownedSwimmingAnimation;

		@ConfigKey("leatherworkers_sell_bedrolls")
		public final ConfigValue<Boolean> leatherworkersSellBedrolls;


		@ConfigKey("deep_ocean_mob_max_spawn_height")
		public final ConfigValue<Integer> deepOceanMobMaxHeight;


		@ConfigKey("glow_squid_weight")
		public final ConfigValue<Integer> glowSquidWeight;

		@ConfigKey("thrasher_weight")
		public final ConfigValue<Integer> thrasherWeight;

		@ConfigKey("nautilus_weight")
		public final ConfigValue<Integer> nautilusWeight;

		@ConfigKey("lionfish_weight")
		public final ConfigValue<Integer> lionfishWeight;

		@ConfigKey("pike_weight")
		public final ConfigValue<Integer> pikeWeight;

		@ConfigKey("perch_weight")
		public final ConfigValue<Integer> perchWeight;

		@ConfigKey("pike_weight_swamp")
		public final ConfigValue<Integer> pikeSwampWeight;

		@ConfigKey("lionfish_weight_swamp")
		public final ConfigValue<Integer> squidSwampWeight;

		@ConfigKey("beachgrass_frequency")
		public final ConfigValue<Integer> beachgrassFrequency;

		@ConfigKey("searocket_frequency")
		public final ConfigValue<Integer> searocketFrequency;

		@ConfigKey("pickerelweed_frequency")
		public final ConfigValue<Integer> pickerelweedFrequency;

		@ConfigKey("pickerelweed_frequency_extra")
		public final ConfigValue<Integer> pickerelweedExtraFrequency;

		@ConfigKey("flowering_rush_frequency")
		public final ConfigValue<Integer> floweringRushFrequency;

		public Common(ForgeConfigSpec.Builder builder) {
			builder.push("items");
			builder.push("trades");
			this.clericsBuyThrasherTeeth = builder.define("Clerics buy thrasher teeth", true);
			this.leatherworkersSellBedrolls = builder.define("Leatherworkers sell bedrolls", true);
			builder.pop();
			builder.pop();

			builder.push("mobs");
			this.drownedSwimmingAnimation = builder.comment("Give Drowneds a swimming animation, like in bedrock edition").define("Drowned swimming animation", true);
			this.deepOceanMobMaxHeight = builder.comment("The max height that deep ocean mobs can spawn at").defineInRange("Deep ocean mob max height", 30, 0, 255);

			builder.push("spawns");
			this.glowSquidWeight = builder.define("Glow Squid spawn weight (oceans)", 67);
			this.thrasherWeight = builder.define("Thrasher spawn weight (cold oceans)", 90);
			this.nautilusWeight = builder.define("Nautilus spawn weight (warm oceans)", 51);
			this.lionfishWeight = builder.define("Lionfish spawn weight (coral reefs)", 15);
			this.pikeWeight = builder.define("Pike spawn weight (rivers)", 11);
			this.perchWeight = builder.define("Perch spawn weight (swamps)", 5);
			this.pikeSwampWeight = builder.define("Pike spawn weight (swamps)", 5);
			this.squidSwampWeight = builder.define("Squid spawn weight (swamps)", 5);
			builder.pop();

			builder.pop();

			builder.push("world");
			builder.push("generation");
			this.beachgrassFrequency = builder.define("Beachgrass frequency (beaches)", 18);
			this.searocketFrequency = builder.define("Searocket frequency (beaches)", 15);
			this.pickerelweedFrequency = builder.define("Pickerelweed frequency (jungles, swamps, rivers)", 28);
			this.pickerelweedExtraFrequency = builder.define("Pickerelweed frequency (flower forests)", 90);
			this.floweringRushFrequency = builder.define("Flowering Rush frequency (rivers)", 15);
			builder.pop();
			builder.pop();
		}
	}

	public static class Client {

		@ConfigKey("show_unobtainable_description")
		public final ConfigValue<Boolean> showUnobtainableDescription;

		@ConfigKey("days_till_insomnia_overlay")
		public final ConfigValue<Integer> daysTillRenderInsomniaOverlay;

		public Client(ForgeConfigSpec.Builder builder) {
			builder.push("misc");
			this.showUnobtainableDescription = builder.comment("If unimplemented items should show that they are unobtainable in their item description").define("Show unobtainable description", true);
			this.daysTillRenderInsomniaOverlay = builder
					.comment("The amount of days till the insomnia overlay is rendered", "Setting to 3 will make the overlay indicate phantom spawns", "Setting to 0 will disable the overlay")
					.define("Days until insomnia overlay", 0);
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
