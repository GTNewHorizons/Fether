package glowredman.fether.compat;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import glowredman.fether.FetherBlocks;
import glowredman.fether.FetherConfig;
import glowredman.txloader.TXLoaderCore;

public class TXLoaderCompat {

    private static final Set<CompletableFuture<Void>> ASSETS = new HashSet<>();
    private static boolean hasPendingAssets = true;
    private static final Random RNG = new Random();

    public static void init() {
        for (int i = 1; i <= 5; i++) {
            ASSETS.add(
                TXLoaderCore.getAssetBuilder("minecraft/sounds/block/sculk_shrieker/shriek" + i + ".ogg", "26.2")
                    .fetch());
        }
        MinecraftForge.EVENT_BUS.register(new TXLoaderCompat());
    }

    @SubscribeEvent
    public void onPlaySound(PlaySoundEvent17 event) {
        if (!"dig.legacy_nether_log".equals(event.name)) {
            return;
        }

        World world = Minecraft.getMinecraft().theWorld;
        ISound sound = event.sound;
        int x = (int) (sound.getXPosF() - 0.5f);
        int y = (int) (sound.getYPosF() - 0.5f);
        int z = (int) (sound.getZPosF() - 0.5f);

        if (world.getBlock(x, y, z) == FetherBlocks.blockLegacyNetherLog
            && (hasPendingAssets() || (world.getBlockMetadata(x, y, z) & 0b0001) != 1
                || RNG.nextFloat() > FetherConfig.carvedLegacyLogShriekProbability)) {
            event.result = new PositionedSoundRecord(
                new ResourceLocation("dig.wood"),
                sound.getVolume(),
                sound.getPitch(),
                sound.getXPosF(),
                sound.getYPosF(),
                sound.getZPosF());
        }
    }

    private static boolean hasPendingAssets() {
        if (!hasPendingAssets) {
            return false;
        }

        Iterator<CompletableFuture<Void>> iter = ASSETS.iterator();
        boolean cancelledOrCompletedExceptionally = false;
        while (iter.hasNext()) {
            CompletableFuture<Void> future = iter.next();
            if (future.isDone()) {
                iter.remove();
                if (future.isCancelled() || future.isCompletedExceptionally()) {
                    cancelledOrCompletedExceptionally = true;
                }
            }
        }

        hasPendingAssets = !ASSETS.isEmpty() || cancelledOrCompletedExceptionally;
        return hasPendingAssets;
    }
}
