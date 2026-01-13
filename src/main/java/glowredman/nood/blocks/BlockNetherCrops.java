package glowredman.nood.blocks;

import java.util.Random;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import glowredman.nood.Nood;
import glowredman.nood.NoodConfig;

public class BlockNetherCrops extends BlockCrops {

    private Item drop;
    private Item seed;
    private final ThreadLocal<Boolean> rClickHarvest = ThreadLocal.withInitial(() -> Boolean.FALSE);

    public BlockNetherCrops setItems(@Nonnull Item drop, @Nonnull Item seed) {
        if (this.drop == null) {
            this.drop = drop;
        } else {
            Nood.LOGGER.warn(
                "Can not assign {} as drop to {} as {} is already assigned to it.",
                drop.delegate.name(),
                this.delegate.name(),
                this.drop.delegate.name());
        }
        if (this.seed == null) {
            this.seed = seed;
        } else {
            Nood.LOGGER.warn(
                "Can not assign {} as seed to {} as {} is already assigned to it.",
                seed.delegate.name(),
                this.delegate.name(),
                this.seed.delegate.name());
        }
        return this;
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
                    this.rClickHarvest.set(Boolean.TRUE);
                    this.dropBlockAsItem(worldIn, x, y, z, 2, 0);
                    this.rClickHarvest.set(Boolean.FALSE);
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

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Nether;
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
        return this.rClickHarvest.get() ? 0 : 1;
    }
}
