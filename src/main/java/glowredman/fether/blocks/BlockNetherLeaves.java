package glowredman.fether.blocks;

import java.util.Arrays;
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
import glowredman.fether.client.AnimVariants;

public class BlockNetherLeaves extends BlockLeaves {

    private final String[] names;
    private final int maxMeta;
    private final int[] variationCount;
    private IIcon[][][] icons;

    public BlockNetherLeaves(String... names) {
        this.names = names;
        this.maxMeta = names.length - 1;
        this.variationCount = new int[names.length];
        Arrays.fill(this.variationCount, 1);
    }

    public BlockNetherLeaves setIconVariationCount(String name, int count) {
        for (int i = 0; i < this.names.length; i++) {
            if (this.names[i].equals(name)) {
                this.variationCount[i] = count;
                break;
            }
        }
        return this;
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int meta = MathHelper.clamp_int(world.getBlockMetadata(x, y, z) & 0b0011, 0, this.maxMeta);
        int v = this.variationCount[meta] > 1 ? AnimVariants.pick(x, y, z, this.variationCount[meta]) : 0;
        return this.icons[this.isOpaqueCube() ? 1 : 0][meta][v];
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.icons[this.isOpaqueCube() ? 1 : 0][MathHelper.clamp_int(meta & 3, 0, this.maxMeta)][0];
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
        this.icons = new IIcon[2][this.maxMeta + 1][Arrays.stream(this.variationCount)
            .max()
            .getAsInt()];

        for (int i = 0; i <= this.maxMeta; i++) {
            if (this.variationCount[i] > 1) {
                for (int v = 0; v < this.variationCount[i]; v++) {
                    this.icons[0][i][v] = reg.registerIcon(this.getTextureName() + "_" + this.names[i] + "_" + v);
                    this.icons[1][i][v] = reg
                        .registerIcon(this.getTextureName() + "_" + this.names[i] + "_opaque_" + v);
                }
                continue;
            }
            this.icons[0][i][0] = reg.registerIcon(this.getTextureName() + "_" + this.names[i]);
            this.icons[1][i][0] = reg.registerIcon(this.getTextureName() + "_" + this.names[i] + "_opaque");
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
        if ((meta & 0b0011) == 0 && chance < 200 && world.rand.nextInt(128) == 0) {
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
