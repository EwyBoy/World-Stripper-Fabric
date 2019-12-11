package com.ewyboy.worldstripper;

import com.ewyboy.worldstripper.config.ConfigHandler;
import com.ewyboy.worldstripper.network.packets.PacketDressWorld;
import com.ewyboy.worldstripper.network.packets.PacketStripWorld;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class WorldStripper implements ModInitializer {

	private static final Logger LOGGER = LogManager.getLogger();

	public static final HashMap<BlockPos, BlockState> hashedBlockCache = new HashMap<>();

	public static Logger getLogger() {
		return LOGGER;
	}

	@Override
	public void onInitialize() {
		ConfigHandler.init();
		ServerSidePacketRegistry.INSTANCE.register(PacketStripWorld.ID, new PacketStripWorld.Handler());
		ServerSidePacketRegistry.INSTANCE.register(PacketDressWorld.ID, new PacketDressWorld.Handler());
	}

}
