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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.terraingen.TerrainGen;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import glowredman.fether.worldgen.WorldGenNetherTree;

public class BlockNetherSapling extends BlockSapling {

    public final String[] names;
    protected IIcon[] icons;
    private final int maxMeta;

    public BlockNetherSapling(String... names) {
        this.names = names;
        this.maxMeta = names.length - 1;
        this.setStepSound(soundTypeGrass);
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.netherrack || ground == Blocks.soul_sand;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.icons[MathHelper.clamp_int(meta & 7, 0, this.maxMeta)];
    }

    /**
     * MCP: {@code growTree}
     */
    @Override
    public void func_149878_d(World worldIn, int x, int y, int z, Random random) {
        if (!TerrainGen.saplingGrowTree(worldIn, random, x, y, z)) {
            return;
        }

        int meta = worldIn.getBlockMetadata(x, y, z) & 7;

        WorldGenerator generator;
        switch (meta) {
            case 0 -> {
                generator = new WorldGenNetherTree.Normal(true);
            }
            case 1 -> {
                generator = new WorldGenNetherTree.Legacy(true);
            }
            default -> {
                return;
            }
        }

        worldIn.setBlock(x, y, z, Blocks.air, 0, 4);
        if (!generator.generate(worldIn, random, x, y, z)) {
            worldIn.setBlock(x, y, z, this, meta, 4);
        }

    }

    @Override
    public int damageDropped(int meta) {
        return MathHelper.clamp_int(meta & 7, 0, this.maxMeta);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int i = 0; i <= this.maxMeta; i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.icons = new IIcon[this.maxMeta + 1];
        for (int i = 0; i <= this.maxMeta; i++) {
            this.icons[i] = reg.registerIcon(this.getTextureName() + "_" + this.names[i]);
        }
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
