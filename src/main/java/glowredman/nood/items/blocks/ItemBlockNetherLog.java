package glowredman.nood.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

import glowredman.nood.blocks.BlockNetherLog;

public class ItemBlockNetherLog extends ItemMultiTexture {

    public ItemBlockNetherLog(Block block) {
        super(block, block, BlockNetherLog.NAMES);
    }
}
