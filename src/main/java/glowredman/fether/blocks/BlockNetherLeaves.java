package glowredman.fether.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import glowredman.fether.FetherBlocks;

public class BlockNetherLeaves extends BlockLeaves {

    protected IIcon blockIconOpaque;

    @Override
    public IIcon getIcon(int side, int meta) {
        if (this.isOpaqueCube()) {
            return this.blockIconOpaque;
        }
        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        super.registerBlockIcons(reg);
        this.blockIconOpaque = reg.registerIcon(this.getTextureName() + "_opaque");
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
        // dummy value, in case some mod does something with it
        return new String[] { "nether" };
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(FetherBlocks.blockNetherSapling);
    }

    @Override
    public int damageDropped(int meta) {
        return 0;
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
