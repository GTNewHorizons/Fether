package glowredman.fether.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import glowredman.fether.FetherBlocks;
import glowredman.fether.FetherConfig;
import glowredman.fether.FetherItems;

public class BlockGlowFlower extends BlockNetherFlower {

    public BlockGlowFlower() {
        super(FetherConfig.glowFlowerSpreadRate);
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.netherrack || ground == Blocks.soul_sand || super.canPlaceBlockOn(ground);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return FetherConfig.glowFlowersDropSeeds ? FetherItems.itemGlowFlowerSeeds
            : Item.getItemFromBlock(FetherBlocks.blockGlowFlower);
    }

    @Override
    public String getItemIconName() {
        return this.getTextureName();
    }
}
