package glowredman.nood.blocks;

import java.util.Random;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import glowredman.nood.NoodConfig;

public class BlockNetherCrops extends BlockCrops {

    private final Item drop;
    private final Item seed;

    public BlockNetherCrops(@Nonnull Item drop, @Nonnull Item seed) {
        this.drop = drop;
        this.seed = seed;
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        if (worldIn.getBlockMetadata(x, y, z) == 2) {
            if (worldIn.isRemote) {
                if (NoodConfig.rClickMatureCropsShowHearts) {
                    worldIn.spawnParticle("heart", x + subX, y + subY, z + subZ, 0.0, 0.0, 0.0);
                }
            } else {
                if (NoodConfig.rClickHarvestCrops) {
                    this.dropBlockAsItem(worldIn, x, y, z, 2, 0);
                    worldIn.setBlock(x, y, z, this, 0, 2);
                }
            }
        }
        return false;
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.soul_sand;
    }

    /**
     * MCP: {@code getSeed}
     */
    @Override
    protected Item func_149866_i() {
        return NoodConfig.cropsDropSeeds ? this.seed : this.drop;
    }

    /**
     * MCP: {@code getCrop}
     */
    @Override
    protected Item func_149865_P() {
        return this.drop;
    }

    @Override
    public int quantityDropped(Random random) {
        return NoodConfig.rClickHarvestCrops ? 0 : 1;
    }
}
