package glowredman.fether.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

import glowredman.fether.blocks.BlockNetherLog;

public class ItemBlockNetherLog extends ItemMultiTexture {

    public ItemBlockNetherLog(Block block) {
        super(block, block, BlockNetherLog.NAMES);
    }
}
