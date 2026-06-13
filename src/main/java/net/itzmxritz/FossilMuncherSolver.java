package net.itzmxritz;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.itzmxritz.utils.ClientScheduler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FossilMuncherSolver implements ClientModInitializer {

	public static final String MOD_ID = "fossilmunchersolver";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		ClientScheduler.init();

		ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
			if (message.getString().contains("§e[NPC] §6Fossil Muncher§f: §f")) {
				String muncherMessage = message.getString().split("§f: §f")[1];
				FossilType type = FossilType.getByHintMessage(muncherMessage);
				LocalPlayer localPlayer = Minecraft.getInstance().player;

				if (type == null) return;
				if (localPlayer == null) return;

				ClientScheduler.runNextTick(() -> {
					localPlayer.displayClientMessage(Component.literal("§8» ").append(Component.translatable("message.fossilmunchersolver.solution", type.getCuteName()).withColor(0xAAAAAA)), false);
				});
			}
		});
	}
}