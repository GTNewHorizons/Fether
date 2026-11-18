package glowredman.fether.worldgen;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import glowredman.fether.FetherBlocks;

public class WorldGenTemplateTree extends WorldGenAbstractTree {

    protected final String set;
    protected final IPlantable sapling;
    public float leafDropout = 0.06f;
    public boolean checks = true;
    public Block eyeBlock;
    public int eyeMeta;
    public float eyeChance;

    public static WorldGenTemplateTree witchfire(boolean doBlockNotify, String set) {
        WorldGenTemplateTree gen = new WorldGenTemplateTree(doBlockNotify, set, FetherBlocks.blockNetherSapling);
        gen.eyeBlock = FetherBlocks.blockWitchfireLog;
        gen.eyeMeta = 2;
        gen.eyeChance = 0.25f;
        return gen;
    }

    public WorldGenTemplateTree(boolean doBlockNotify, String set, IPlantable sapling) {
        super(doBlockNotify);
        this.set = set;
        this.sapling = sapling;
    }

    @Override
    public boolean generate(World world, Random rng, int x, int y, int z) {
        List<TreeTemplate> templates = TreeTemplate.getSet(this.set);
        if (templates.isEmpty()) {
            return false;
        }
        TreeTemplate t = templates.get(rng.nextInt(templates.size()));
        int rot = rng.nextInt(4);
        boolean mirror = rng.nextBoolean();

        if (y + t.minY < 1 || y + t.maxY > 254) {
            return false;
        }

        Block soil = world.getBlock(x, y - 1, z);
        if (this.checks) {
            if (!soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this.sapling)) {
                return false;
            }
            for (TreeTemplate.Entry e : t.entries) {
                int[] p = transform(e, rot, mirror);
                int bx = x + p[0], by = y + e.dy, bz = z + p[1];
                if (bx == x && by == y && bz == z) {
                    continue;
                }
                if (!this.canReplace(world, bx, by, bz, e.dy < 0)) {
                    return false;
                }
            }
        }

        soil.onPlantGrow(world, x, y - 1, z, x, y, z);

        TreeTemplate.Entry eye = this.eyeBlock != null && rng.nextFloat() < this.eyeChance ? pickEyeLog(t) : null;

        for (int pass = 0; pass < 2; pass++) {
            for (TreeTemplate.Entry e : t.entries) {
                boolean isLeaf = e.block instanceof BlockLeaves;
                if (isLeaf != (pass == 1)) {
                    continue;
                }
                if (isLeaf && rng.nextFloat() < this.leafDropout) {
                    continue;
                }
                int[] p = transform(e, rot, mirror);
                int bx = x + p[0], by = y + e.dy, bz = z + p[1];
                if (isLeaf && !world.isAirBlock(bx, by, bz)) {
                    continue;
                }
                if (!isLeaf && this.isFloatingRoot(world, e, bx, by, bz)) {
                    continue;
                }
                if (e == eye) {
                    this.setBlockAndNotifyAdequately(world, bx, by, bz, this.eyeBlock, this.eyeMeta);
                    continue;
                }
                this.setBlockAndNotifyAdequately(world, bx, by, bz, e.block, fixMeta(e.block, e.meta, rot));
            }
        }
        return true;
    }

    protected TreeTemplate.Entry pickEyeLog(TreeTemplate t) {
        int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
        for (TreeTemplate.Entry e : t.entries) {
            if (e.block == this.eyeBlock && e.dy >= 0) {
                lo = Math.min(lo, e.dy);
                hi = Math.max(hi, e.dy);
            }
        }
        if (lo > hi) {
            return null;
        }
        float mid = (lo + hi) / 2f;
        TreeTemplate.Entry best = null;
        for (TreeTemplate.Entry e : t.entries) {
            if (e.block != this.eyeBlock || (e.meta & 12) != 0 || (e.meta & 3) != 0 || e.dy <= 0) {
                continue;
            }
            if (best == null || Math.abs(e.dy - mid) < Math.abs(best.dy - mid)) {
                best = e;
            }
        }
        return best;
    }

    protected boolean isFloatingRoot(World world, TreeTemplate.Entry e, int x, int y, int z) {
        if (e.dy > 0 || (e.dx == 0 && e.dz == 0)) {
            return false;
        }
        if (e.dy < 0) {
            return world.isAirBlock(x, y, z) || !world.getBlock(x, y, z)
                .getMaterial()
                .isSolid();
        }
        return !world.getBlock(x, y - 1, z)
            .getMaterial()
            .isSolid();
    }

    protected boolean canReplace(World world, int x, int y, int z, boolean belowGround) {
        Block b = world.getBlock(x, y, z);
        if (this.isReplaceable(world, x, y, z)) {
            return true;
        }
        return belowGround && (b == Blocks.netherrack || b == Blocks.soul_sand || b == Blocks.gravel);
    }

    static int[] transform(TreeTemplate.Entry e, int rot, boolean mirror) {
        int dx = mirror ? -e.dx : e.dx;
        int dz = e.dz;
        switch (rot & 3) {
            case 1:
                return new int[] { -dz, dx };
            case 2:
                return new int[] { -dx, -dz };
            case 3:
                return new int[] { dz, -dx };
            default:
                return new int[] { dx, dz };
        }
    }

    static int fixMeta(Block block, int meta, int rot) {
        if (block instanceof BlockLeaves) {
            return meta & 3;
        }
        if (block instanceof BlockRotatedPillar && (rot & 1) == 1) {
            int axis = meta & 12;
            if (axis == 4) {
                return (meta & 3) | 8;
            }
            if (axis == 8) {
                return (meta & 3) | 4;
            }
        }
        return meta;
    }
}
