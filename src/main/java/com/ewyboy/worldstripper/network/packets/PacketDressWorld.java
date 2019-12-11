package com.ewyboy.worldstripper.network.packets;

import com.ewyboy.worldstripper.other.Reference;
import com.ewyboy.worldstripper.WorldStripper;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PacketDressWorld implements IPacket {

    public static final Identifier ID = new Identifier(Reference.ModInfo.MOD_ID, "dress_packet");

    public PacketDressWorld() {}

    @Override
    public void read(PacketByteBuf packetByteBuf) {}

    @Override
    public void write(PacketByteBuf packetByteBuf) {}

    @Override
    public Identifier getID() {
        return ID;
    }

    public static class Handler extends MessageHandler<PacketDressWorld> {

        @Override
        protected PacketDressWorld create() {
            return new PacketDressWorld();
        }

        @Override
        public void handle(PacketContext ctx, PacketDressWorld packetDressWorld) {
            ServerPlayerEntity player = (ServerPlayerEntity) ctx.getPlayer();
            World world = player != null ? player.getEntityWorld() : null;

            double chunkClearSizeX = (24);
            double chunkClearSizeZ = (24);

            if (player != null) {
                if (player.isCreative()) {
                    player.sendMessage(new LiteralText(Formatting.BOLD + "" + Formatting.RED + "WARNING! " + Formatting.WHITE + "World Dressing Initialized! Lag May Occur.."));
                    for (int x = (int) (player.getPos().getX() - chunkClearSizeX); (double) x <= player.getPos().getX() + chunkClearSizeX; x++) {
                        for (int y = 0; (double) y <= player.getPos().getY() + 16; ++y) {
                            for (int z = (int) (player.getPos().getZ() - chunkClearSizeZ); (double) z <= player.getPos().getZ() + chunkClearSizeZ; z++) {
                                BlockPos targetBlockPos = new BlockPos(x, y, z);
                                if (WorldStripper.hashedBlockCache.get(targetBlockPos) != null) {
                                    if (world != null) {
                                        world.setBlockState(targetBlockPos, WorldStripper.hashedBlockCache.get(targetBlockPos), 3);
                                    }
                                }
                            }
                        }
                    }
                    player.sendMessage(new LiteralText("World Dressing Successfully Done!"));
                } else {
                    player.sendMessage(new LiteralText(Formatting.RED + "Error: You have to be in creative mode to use this feature!"));
                }
            }
        }
    }

}
