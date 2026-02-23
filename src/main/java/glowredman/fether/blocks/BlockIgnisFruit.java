package glowredman.fether.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import glowredman.fether.FetherBlocks;
import glowredman.fether.FetherConfig;
import glowredman.fether.FetherItems;

public class BlockIgnisFruit extends Block implements IGrowable {

    private IIcon[] icons;

    public BlockIgnisFruit() {
        super(Material.circuits);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (meta > 2) {
            meta = 2;
        }
        return this.icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.icons = new IIcon[3];
        for (int i = 0; i < 3; i++) {
            this.icons[i] = reg.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        return null;
    }

    @Override
    public void updateTick(World worldIn, int x, int y, int z, Random random) {
        if (worldIn.isRemote) {
            return;
        }
        int meta = worldIn.getBlockMetadata(x, y, z);
        if (meta < 2 && random.nextInt(30) == 0) {
            worldIn.setBlock(x, y, z, this, meta + 1, 2);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        if (worldIn.getBlockMetadata(x, y, z) >= 2) {
            if (worldIn.isRemote) {
                if (FetherConfig.rClickMatureFruitsShowHearts) {
                    worldIn.spawnParticle("heart", x + subX, y + subY, z + subZ, 0.0, 0.0, 0.0);
                }
            } else {
                if (FetherConfig.rClickHarvestFruits) {
                    this.dropBlockAsItem(worldIn, x, y, z, 2, 0);
                    worldIn.setBlock(x, y, z, this, 0, 2);
                }
            }
        }
        return false;
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, int x, int y, int z, int meta) {
        if (meta == 2) {
            worldIn.setBlock(x, y, z, this, 0, 2);
        }
    }

    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        if (!this.canBlockStay(worldIn, x, y, z)) {
            worldIn.func_147480_a(x, y, z, false); // MCP: breakBlock
        }
    }

    @Override
    public boolean canBlockStay(World worldIn, int x, int y, int z) {
        return worldIn.getBlock(x, y + 1, z) == FetherBlocks.blockNetherLeaves;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z) {
        return super.canPlaceBlockAt(worldIn, x, y, z) && this.canBlockStay(worldIn, x, y, z);
    }

    @Override
    public Item getItem(World worldIn, int x, int y, int z) {
        return FetherItems.itemIgnisFruit;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return FetherItems.itemIgnisFruit;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isNormalCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 1;
    }

    @Override
    public float getBlockHardness(World worldIn, int x, int y, int z) {
        return worldIn.getBlockMetadata(x, y, z) < 2 ? 5.0f : 0.0f;
    }

    /**
     * MCP: {@code canFertilize}
     */
    @Override
    public boolean func_149851_a(World worldIn, int x, int y, int z, boolean isClient) {
        return worldIn.getBlockMetadata(x, y, z) < 2;
    }

    /**
     * MCP: {@code shouldFertilize}
     */
    @Override
    public boolean func_149852_a(World worldIn, Random random, int x, int y, int z) {
        return true;
    }

    /**
     * MCP: {@code fertilize}
     */
    @Override
    public void func_149853_b(World worldIn, Random random, int x, int y, int z) {
        worldIn.setBlock(x, y, z, this, 2, 2);
    }
}
