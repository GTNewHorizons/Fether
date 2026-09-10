package glowredman.fether.blocks.wood;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockWoodSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import glowredman.fether.FetherBlocks;

public class BlockNetherWoodSlab extends BlockWoodSlab {

    public static final String[] VARIANTS = { "normal" };

    public BlockNetherWoodSlab(boolean p_i45437_1_) {
        super(p_i45437_1_);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return FetherBlocks.blockNetherWoodPlanks.getIcon(side, meta & 7);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(FetherBlocks.blockNetherWoodSlab);
    }

    @Override
    protected ItemStack createStackedBlock(int meta) {
        return new ItemStack(FetherBlocks.blockNetherWoodSlab, 2, meta & 7);
    }

    @Override
    public String func_150002_b(int p_150002_1_) {
        if (p_150002_1_ < 0 || p_150002_1_ >= VARIANTS.length) {
            p_150002_1_ = 0;
        }
        return this.getUnlocalizedName() + "." + VARIANTS[p_150002_1_];
    };

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        if (this.field_150004_a) {
            return;
        }
        for (int i = 0; i < VARIANTS.length; i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }
}
