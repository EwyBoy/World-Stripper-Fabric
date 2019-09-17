package com.ewyboy.worldstripper.network;

import com.ewyboy.worldstripper.config.Config;
import com.ewyboy.worldstripper.config.ConfigHandler;
import com.ewyboy.worldstripper.other.Profile;
import com.ewyboy.worldstripper.other.Reference;
import com.ewyboy.worldstripper.WorldStripper;
import com.raphydaphy.crochet.network.IPacket;
import com.raphydaphy.crochet.network.MessageHandler;
import io.github.prospector.modmenu.config.ModMenuConfigManager;
import me.sargunvohra.mcmods.autoconfig1.ConfigManager;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Arrays;

public class PacketStripWorld implements IPacket {

    public static final Identifier ID = new Identifier(Reference.ModInfo.MOD_ID, "strip_packet");

    public PacketStripWorld() {}

    @Override
    public void read(PacketByteBuf packetByteBuf) {}

    @Override
    public void write(PacketByteBuf packetByteBuf) {}

    @Override
    public Identifier getID() {
        return ID;
    }

    public static class Handler extends MessageHandler<PacketStripWorld> {

        @Override
        protected PacketStripWorld create() {
            return new PacketStripWorld();
        }

        @Override
        public void handle(PacketContext ctx, PacketStripWorld packetStripWorld) {
            Config config = ConfigHandler.getConfig();

            ServerPlayerEntity player = (ServerPlayerEntity) ctx.getPlayer();
            World world = player != null ? player.getEntityWorld() : null;

            int profile = config.getSelectedProfile();
            double chunkClearSizeX = (16 * config.getChunkRadiusX() / 2);
            double chunkClearSizeZ = (16 * config.getChunkRadiusZ() / 2);

            if (player != null) {
                if (player.isCreative()) {
                    player.sendMessage(new LiteralText(Formatting.BOLD + "" + Formatting.RED + "WARNING! " + Formatting.WHITE + "World Stripping Initialized! Lag May Occur.."));
                    for (int x = (int) (player.getPos().getX() - chunkClearSizeX); (double) x <= player.getPos().getX() + chunkClearSizeX; x++) {
                        for (int y = 0; (double) y <= player.getPos().getY() + 16; ++y) {
                            for (int z = (int) (player.getPos().getZ() - chunkClearSizeZ); (double) z <= player.getPos().getZ() + chunkClearSizeZ; z++) {
                                BlockPos targetBlockPos = new BlockPos(x, y , z);
                                BlockState targetBlockState = world.getBlockState(targetBlockPos);
                                Block targetBlock = targetBlockState.getBlock();

                                if (!targetBlock.equals(Blocks.AIR) && !targetBlock.equals(Blocks.BEDROCK)) {
                                    Arrays.stream(config.getStripProfile1().toArray()).filter(Registry.BLOCK.getId(targetBlock).toString() :: equals).forEachOrdered(s -> {
                                        WorldStripper.hashedBlockCache.put(targetBlockPos, targetBlockState);
                                        world.setBlockState(targetBlockPos, Registry.BLOCK.get(new Identifier(config.getReplacementBlock())).getDefaultState(), config.getBlockStateFlag());
                                    });
                                }
                            }
                        }
                    }
                    player.sendMessage(new LiteralText("World Stripping Successfully Done!"));
                } else {
                    player.sendMessage(new LiteralText(Formatting.RED + "Error: You have to be in creative mode to use this feature!"));
                }
            }
        }
    }
}