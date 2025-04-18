package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.blueprint.core.endimator.PlayableEndimation;
import com.teamabnormals.blueprint.core.endimator.PlayableEndimationManager;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;

public final class UAPlayableEndimations {
	public static final PlayableEndimation THRASHER_SNAP_AT_PRAY = register("thrasher/snap_at_pray", 10, PlayableEndimation.LoopType.NONE);
	public static final PlayableEndimation THRASHER_HURT = register("thrasher/hurt", 10, PlayableEndimation.LoopType.NONE);
	public static final PlayableEndimation THRASHER_THRASH = register("thrasher/thrash", 55, PlayableEndimation.LoopType.NONE);
	public static final PlayableEndimation THRASHER_SONAR_FIRE = register("thrasher/sonar_fire", 30, PlayableEndimation.LoopType.NONE);

	private static PlayableEndimation register(String name, int duration, PlayableEndimation.LoopType loopType) {
		return PlayableEndimationManager.INSTANCE.registerPlayableEndimation(new PlayableEndimation(UpgradeAquatic.location(name), duration, loopType));
	}
}
