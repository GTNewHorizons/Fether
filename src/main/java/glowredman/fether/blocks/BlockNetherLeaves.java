package glowredman.fether.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import glowredman.fether.FetherBlocks;

public class BlockNetherLeaves extends BlockLeaves {

    private Random rng = new Random();
    private final String[] names;
    private final int maxMeta;
    private final boolean[] varied;
    @SideOnly(Side.CLIENT)
    private IIcon[][][] variants;

    public BlockNetherLeaves(String... names) {
        this.names = names;
        this.maxMeta = names.length - 1;
        this.varied = new boolean[names.length];
    }

    public BlockNetherLeaves withVariedAnimation(String name) {
        for (int i = 0; i < this.names.length; i++) {
            if (this.names[i].equals(name)) {
                this.varied[i] = true;
            }
        }
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int meta = MathHelper.clamp_int(world.getBlockMetadata(x, y, z) & 3, 0, this.maxMeta);
        if (this.varied[meta]) {
            int v = AnimVariants.pick(x, y, z);
            if (v > 0) {
                return this.variants[this.isOpaqueCube() ? 1 : 0][meta][v];
            }
        }
        return this.getIcon(side, meta);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.field_150129_M[this.isOpaqueCube() ? 1 : 0][MathHelper.clamp_int(meta & 3, 0, this.maxMeta)];
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
        this.field_150129_M = new IIcon[2][this.maxMeta + 1];
        for (int i = 0; i <= this.maxMeta; i++) {
            this.field_150129_M[0][i] = reg.registerIcon(this.getTextureName() + "_" + this.names[i]);
            this.field_150129_M[1][i] = reg.registerIcon(this.getTextureName() + "_" + this.names[i] + "_opaque");
        }
        this.variants = new IIcon[2][this.maxMeta + 1][AnimVariants.COUNT];
        for (int i = 0; i <= this.maxMeta; i++) {
            if (!this.varied[i]) {
                continue;
            }
            for (int v = 1; v < AnimVariants.COUNT; v++) {
                this.variants[0][i][v] = reg.registerIcon(this.getTextureName() + "_" + this.names[i] + "_" + v);
                this.variants[1][i][v] = reg.registerIcon(this.getTextureName() + "_" + this.names[i] + "_opaque_" + v);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube() {
        return !Minecraft.isFancyGraphicsEnabled();
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int side) {
        // NOTE: if - for whatever reason - this block is changed such that it isn't a full block anymore, this logic
        // needs to be expanded
        Block adjBlock = worldIn.getBlock(x, y, z);
        return adjBlock == this ? Minecraft.isFancyGraphicsEnabled() : !adjBlock.isOpaqueCube();
    }

    @Override
    public String[] func_150125_e() {
        return this.names;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(FetherBlocks.blockNetherSapling);
    }

    @Override
    protected void func_150124_c(World world, int x, int y, int z, int meta, int chance) {
        if ((meta & 3) == 0 && chance < 200 && world.rand.nextInt(128) == 0) {
            this.dropBlockAsItem(world, x, y, z, new ItemStack(FetherBlocks.blockNetherSapling, 1, 1));
        }
    }

    @Override
    public int colorMultiplier(IBlockAccess worldIn, int x, int y, int z) {
        return 0xFFFFFF;
    }

    @Override
    public int getRenderColor(int meta) {
        return 0xFFFFFF;
    }

    @Override
    public int getBlockColor() {
        return 0xFFFFFF;
    }
}
