package glowredman.fether.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;

import glowredman.fether.blocks.wood.BlockNetherWoodSlab;

public class ItemBlockNetherSlab extends ItemSlab {

    public ItemBlockNetherSlab(Block block, BlockNetherWoodSlab blockSingleSlab, BlockNetherWoodSlab blockDoubleSlab,
        Boolean isDoubleSlab) {
        super(block, blockSingleSlab, blockDoubleSlab, isDoubleSlab);
    }
}
