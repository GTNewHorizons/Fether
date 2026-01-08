package glowredman.nood.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import glowredman.nood.NoodBlocks;
import glowredman.nood.NoodConfig;
import glowredman.nood.NoodItems;

public class BlockGlowFlower extends BlockNetherFlower {

    public BlockGlowFlower() {
        super(NoodConfig.glowFlowerSpreadRate);
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.netherrack || ground == Blocks.soul_sand || super.canPlaceBlockOn(ground);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return NoodConfig.glowFlowersDropSeeds ? NoodItems.itemGlowFlowerSeeds
            : Item.getItemFromBlock(NoodBlocks.blockGlowFlower);
    }
}
