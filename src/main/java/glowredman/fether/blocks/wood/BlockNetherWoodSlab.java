package glowredman.fether.blocks.wood;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockWoodSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import glowredman.fether.FetherBlocks;

public class BlockNetherWoodSlab extends BlockWoodSlab {

    public static final String[] VARIANTS = { "normal", "legacy" };

    public BlockNetherWoodSlab(boolean isFullBlock) {
        super(isFullBlock);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return FetherBlocks.blockNetherWoodPlanks.getIcon(side, meta & 7);
    }

    @Override
    public Item getItem(World worldIn, int x, int y, int z) {
        return Item.getItemFromBlock(FetherBlocks.blockNetherWoodSlab);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(FetherBlocks.blockNetherWoodSlab);
    }

    @Override
    protected ItemStack createStackedBlock(int meta) {
        return new ItemStack(FetherBlocks.blockNetherWoodSlab, 2, meta & 7);
    }

    /**
     * MCP: {@code getFullSlabName}
     */
    @Override
    public String func_150002_b(int meta) {
        if (meta < 0 || meta >= VARIANTS.length) {
            meta = 0;
        }
        return this.getUnlocalizedName() + "." + VARIANTS[meta];
    };

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        if (this.field_150004_a) { // MCP: isFullBlock
            return;
        }
        for (int i = 0; i < VARIANTS.length; i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }
}
