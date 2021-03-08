package com.minecraftabnormals.upgrade_aquatic.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class UAConfig {

	public static class Common {
		public final ConfigValue<Integer> glowSquidSpawnWeight;
		public final ConfigValue<Integer> thrasherSpawnWeight;
		public final ConfigValue<Integer> nautilusSpawnWeight;
		public final ConfigValue<Integer> lionfishSpawnWeight;
		public final ConfigValue<Integer> pikeSpawnWeight;

		public final ConfigValue<Integer> pikeSwampSpawnWeight;
		public final ConfigValue<Integer> squidSwampSpawnWeight;
		public final ConfigValue<Integer> salmonSwampSpawnWeight;

		public final ConfigValue<Integer> deepOceanMobMaxHeight;

		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Upgrade Aquatic common configuration").push("common");
			builder.push("mobs");

			this.deepOceanMobMaxHeight = builder.comment("The max height that deep ocean mobs can spawn at").defineInRange("deepOceanMobMaxHeight", 30, 0, 255);

			builder.push("spawns");

			this.glowSquidSpawnWeight = builder.comment("Glow Squid spawn weight in oceans").define("glowSquidSpawnWeight", 67);
			this.thrasherSpawnWeight = builder.comment("Thrasher spawn weight in cold oceans").define("thrasherSpawnWeight", 90);
			this.nautilusSpawnWeight = builder.comment("Nautilus spawn weight in warm oceans").define("nautilusSpawnWeight", 51);
			this.lionfishSpawnWeight = builder.comment("Lionfish spawn weight in coral reefs").define("lionfishSpawnWeight", 15);
			this.pikeSpawnWeight = builder.comment("Pike spawn weight in rivers").define("pikeSpawnWeight", 11);

			this.pikeSwampSpawnWeight = builder.comment("Pike spawn weight in swamps").define("pikeSwampSpawnWeight", 5);
			this.squidSwampSpawnWeight = builder.comment("Squid spawn weight in swamps").define("squidSwampSpawnWeight", 5);
			this.salmonSwampSpawnWeight = builder.comment("Salmon spawn weight in swamps").define("salmonSwampSpawnWeight", 5);

			builder.pop();
			builder.pop();
			builder.pop();
		}
	}

	public static class Client {
		public final ConfigValue<Integer> daysTillRenderInsomniaOverlay;

		public Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Upgrade Aquatic client configuration").push("client");
			builder.push("misc");

			this.daysTillRenderInsomniaOverlay = builder
					.comment("The amount of days till the insomnia overlay is rendered", "Setting to 3 will make the overlay indicate phantom spawns", "Setting to 0 will disable the overlay")
					.define("daysTillRenderInsomniaOverlay", 0);

			builder.pop();
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
