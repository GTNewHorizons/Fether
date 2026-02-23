package glowredman.fether.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.terraingen.TerrainGen;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import glowredman.fether.worldgen.WorldGenNetherTree;

public class BlockNetherSapling extends BlockSapling {

    public BlockNetherSapling() {
        this.setStepSound(soundTypeGrass);
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.netherrack || ground == Blocks.soul_sand;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.blockIcon;
    }

    /**
     * MCP: {@code growTree}
     */
    @Override
    public void func_149878_d(World worldIn, int x, int y, int z, Random random) {
        if (!TerrainGen.saplingGrowTree(worldIn, random, x, y, z)) {
            return;
        }
        int meta = worldIn.getBlockMetadata(x, y, z);
        worldIn.setBlock(x, y, z, Blocks.air, 0, 4);
        if (!new WorldGenNetherTree(true).generate(worldIn, random, x, y, z)) {
            worldIn.setBlock(x, y, z, this, meta, 4);
        }
    }

    @Override
    public int damageDropped(int meta) {
        return 0;
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(itemIn));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTextureName());
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Nether;
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
        this.func_149878_d(worldIn, x, y, z, random);
    }
}
