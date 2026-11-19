package glowredman.fether.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import glowredman.fether.FetherBlocks;

public class WorldGenWitchfireCypressTree extends WorldGenNetherTree {

    public WorldGenWitchfireCypressTree(boolean doBlockNotify) {
        super(doBlockNotify);
        this.minTreeHeight = 6;
        this.metaLeaves = 2;
        this.blockLog = FetherBlocks.blockWitchfireLog;
        this.blockLogVariant = FetherBlocks.blockWitchfireLog;
        this.metaLogVariant = 1;
    }

    @Override
    public boolean generate(World world, Random rng, int x, int y, int z) {
        int height = this.minTreeHeight + rng.nextInt(3) + rng.nextInt(3);

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

        // MAIN BRANCH
        int dirMainBranch = rng.nextInt(4);
        int branchingHeight = height - rng.nextInt(5) - 1;
        int branchLength = 4 - rng.nextInt(4);
        int logX = x;
        int logZ = z;
        int lastLogY = 0;

        // set logs
        for (int offsetY = 0; offsetY < height; offsetY++) {
            int logY = y + offsetY;

            if (offsetY >= branchingHeight && branchLength > 0) {
                logX += Direction.offsetX[dirMainBranch];
                logZ += Direction.offsetZ[dirMainBranch];
                branchLength--;
            }

            Block block = world.getBlock(logX, logY, logZ);
            if (block.isAir(world, logX, logY, logZ) || block.isLeaves(world, logX, logY, logZ)) {
                this.setLog(world, rng, logX, logY, logZ);
                lastLogY = logY;
            }
        }

        // set leaves
        for (int offsetX = -1; offsetX <= 1; offsetX++) {
            for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
                this.setLeaves(world, logX + offsetX, lastLogY + 1, logZ + offsetZ);
            }
        }

        this.setLeaves(world, logX + 2, lastLogY + 1, logZ);
        this.setLeaves(world, logX - 2, lastLogY + 1, logZ);
        this.setLeaves(world, logX, lastLogY + 1, logZ + 2);
        this.setLeaves(world, logX, lastLogY + 1, logZ - 2);

        for (int offsetX = -3; offsetX <= 3; offsetX++) {
            for (int offsetZ = -3; offsetZ <= 3; offsetZ++) {
                if (Math.abs(offsetX) != 3 || Math.abs(offsetZ) != 3) {
                    this.setLeaves(world, logX + offsetX, lastLogY, logZ + offsetZ);
                }
            }
        }

        // OFF BRANCHES
        int dirFirstOffBranch = -1;
        for (int i = 0; i < 2; i++) {
            int dirOffBranch = rng.nextInt(4);

            if (dirOffBranch == dirMainBranch || dirOffBranch == dirFirstOffBranch) {
                continue;
            }

            branchingHeight -= rng.nextInt(2) + 1;
            branchLength = 1 + rng.nextInt(3);
            logX = x;
            logZ = z;
            lastLogY = 0;

            // set logs
            for (int offsetY = branchingHeight; offsetY < height && branchLength > 0; offsetY++, branchLength--) {
                if (offsetY < 1) {
                    continue;
                }

                int logY = y + offsetY;
                logX += Direction.offsetX[dirOffBranch];
                logZ += Direction.offsetZ[dirOffBranch];

                Block block = world.getBlock(logX, logY, logZ);
                if (block.isAir(world, logX, logY, logZ) || block.isLeaves(world, logX, logY, logZ)) {
                    this.setLog(world, rng, logX, logY, logZ);
                    lastLogY = logY;
                }
            }

            if (lastLogY <= 0) {
                continue;
            }

            dirFirstOffBranch = dirOffBranch;

            // set leaves
            for (int offsetX = -1; offsetX <= 1; offsetX++) {
                for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
                    this.setLeaves(world, logX + offsetX, lastLogY + 1, logZ + offsetZ);
                }
            }

            for (int offsetX = -2; offsetX <= 2; offsetX++) {
                for (int offsetZ = -2; offsetZ <= 2; offsetZ++) {
                    if (Math.abs(offsetX) != 2 || Math.abs(offsetZ) != 2) {
                        this.setLeaves(world, logX + offsetX, lastLogY, logZ + offsetZ);
                    }
                }
            }
        }

        return true;
    }
}
