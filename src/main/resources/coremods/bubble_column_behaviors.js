var Opcodes = org.objectweb.asm.Opcodes;
var VarInsnNode = org.objectweb.asm.tree.VarInsnNode;
var InsnList = org.objectweb.asm.tree.InsnList;

/**
 * @author SmellyModder(Luke Tonon)
 */
function initializeCoreMod() {
	return {
		'bubble-column-behaviors': {
			'target': {
				'type': 'METHOD',
				'class': 'net.minecraft.block.BubbleColumnBlock',
				'methodName': 'func_225534_a_', //# tick
				'methodDesc': '(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V'
			},
			'transformer': function(method) {
				var ASM = Java.type('net.minecraftforge.coremod.api.ASMAPI');
            	
				var methodInstructions = new InsnList();
            	
				methodInstructions.add(new VarInsnNode(Opcodes.ALOAD, 1));
				methodInstructions.add(new VarInsnNode(Opcodes.ALOAD, 2));
				methodInstructions.add(new VarInsnNode(Opcodes.ALOAD, 3));
				methodInstructions.add(ASM.buildMethodCall(
					"com/teamabnormals/upgrade_aquatic/core/UAHooks",
					"addBubbleColumnBehaviors",
					"(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/util/math/BlockPos;)V",
					ASM.MethodType.STATIC
				));
            	
				method.instructions.insertBefore(method.instructions.getFirst(), methodInstructions);
				return method;
			}
		}
	}
}