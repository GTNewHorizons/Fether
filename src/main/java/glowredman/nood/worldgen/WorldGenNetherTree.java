package glowredman.nood.worldgen;

import java.util.Random;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import glowredman.nood.NoodBlocks;

public class WorldGenNetherTree extends WorldGenAbstractTree {

    protected @Nonnegative int minTreeHeight;
    protected @Nonnull IPlantable sapling;
    protected @Nonnull Block blockLeaves;
    protected @Nonnegative int metaLeaves;
    protected @Nonnull Block blockFruit;
    protected @Nonnegative int metaFruit;
    protected @Nonnull Block blockLog;
    protected @Nonnegative int metaLog;

    public WorldGenNetherTree(boolean doBlockNotify) {
        super(doBlockNotify);
        this.minTreeHeight = 5;
        this.sapling = NoodBlocks.blockNetherSapling;
        this.blockLeaves = NoodBlocks.blockNetherLeaves;
        this.metaLeaves = 0;
        this.blockFruit = NoodBlocks.blockIgnisFruit;
        this.metaFruit = 0;
        this.blockLog = NoodBlocks.blockNetherLog;
        this.metaLog = 0;
    }

    @Override
    public boolean generate(World world, Random rng, int x, int y, int z) {
        int height = this.minTreeHeight + rng.nextInt(3);

        if (y < 1 || y + height > 254) {
            // invalid y coordinate
            return false;
        }

        Block soil = world.getBlock(x, y - 1, z);
        if (!soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this.sapling)) {
            // invalid soil block
            return false;
        }

        // check if tree growth is blocked
        for (int testY = y; testY <= y + height + 1; testY++) {
            int r;

            if (testY == y) {
                r = 0;
            } else if (testY >= y + height - 1) {
                r = 2;
            } else {
                r = 1;
            }

            for (int testX = x - r; testX <= x + r; testX++) {
                for (int testZ = z - r; testZ <= z + r; testZ++) {
                    if (!this.isReplaceable(world, testX, testY, testZ)) {
                        return false;
                    }
                }
            }
        }

        soil.onPlantGrow(world, x, y - 1, z, x, y, z);

        // set leaves && fruits
        for (int offsetY = -3; offsetY <= 0; offsetY++) {
            int leavesY = y + height + offsetY;
            int r = 1 - offsetY / 2;

            for (int offsetX = -r; offsetX <= r; offsetX++) {
                int leavesX = x + offsetX;

                for (int offsetZ = -r; offsetZ <= r; offsetZ++) {
                    int leavesZ = z + offsetZ;

                    if (Math.abs(offsetX) != r || Math.abs(offsetZ) != r || rng.nextBoolean() && offsetY != 0) {
                        Block block = world.getBlock(leavesX, leavesY, leavesZ);
                        if (block.isAir(world, leavesX, leavesY, leavesZ)
                            || block.isLeaves(world, leavesX, leavesY, leavesZ)) {
                            this.setBlockAndNotifyAdequately(
                                world,
                                leavesX,
                                leavesY,
                                leavesZ,
                                this.blockLeaves,
                                this.metaLeaves);

                            if (world.isAirBlock(leavesX, leavesY - 1, leavesZ)
                                && world.isAirBlock(leavesX, leavesY - 2, leavesZ)
                                && rng.nextInt(4) == 0) {
                                this.setBlockAndNotifyAdequately(
                                    world,
                                    leavesX,
                                    leavesY - 1,
                                    leavesZ,
                                    this.blockFruit,
                                    this.metaFruit);
                            }
                        }
                    }
                }
            }
        }

        // set logs
        for (int offsetY = 0; offsetY < height; offsetY++) {
            int logY = y + offsetY;
            Block block = world.getBlock(x, logY, z);
            if (block.isAir(world, x, logY, z) || block.isLeaves(world, x, logY, z) || block == this.blockFruit) {
                this.setBlockAndNotifyAdequately(world, x, logY, z, this.blockLog, this.metaLog);
            }
        }

        return true;
    }
}
