package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.upgrade_aquatic.common.entities.EntityLionfish;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.*;

public class UAEntitySpawns {

	//TODO: Write proper system to slim down code
	public static void addSpawnsToBiomes() {
		EntityNautilus.addSpawn();
		EntityPike.addSpawn();
		EntityLionfish.addSpawn();
		EntityThrasher.addSpawn();
		EntityGreatThrasher.addSpawn();
	}
	
}