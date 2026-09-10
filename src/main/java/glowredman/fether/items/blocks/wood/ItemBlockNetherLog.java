package glowredman.fether.items.blocks.wood;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

import glowredman.fether.blocks.wood.BlockNetherLog;

public class ItemBlockNetherLog extends ItemMultiTexture {

    public ItemBlockNetherLog(Block block) {
        super(block, block, BlockNetherLog.NAMES);
    }
}
