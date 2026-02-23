package glowredman.fether.worldgen;

import java.util.Random;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherFlowers extends WorldGenerator {

    private final Block flower;
    private final long salt;

    public WorldGenNetherFlowers(boolean doBlockNotify, @Nonnull Block flower) {
        super(doBlockNotify);
        this.flower = flower;
        long hash = flower.delegate.name()
            .hashCode();
        this.salt = hash << 32 | hash;
    }

    @Override
    public boolean generate(World world, Random rng, int x, int y, int z) {
        rng.setSeed(rng.nextLong() ^ this.salt);

        for (int i = 0; i < 8; i++) {
            int newX = x + rng.nextInt(15) + 1;
            int newY = y + rng.nextInt(7) - 3;
            int newZ = z + rng.nextInt(15) + 1;

            if (newY < 1 || newY > 254) {
                continue;
            }

            if (world.isAirBlock(newX, newY, newZ) && this.flower.canPlaceBlockAt(world, newX, newY, newZ)) {
                this.setBlockAndNotifyAdequately(world, newX, newY, newZ, this.flower, 0);
            }
        }
        return true;
    }
}
