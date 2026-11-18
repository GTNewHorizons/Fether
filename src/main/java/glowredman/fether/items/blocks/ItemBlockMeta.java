package glowredman.fether.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

import glowredman.fether.blocks.BlockNetherLeaves;
import glowredman.fether.blocks.BlockNetherSapling;
import glowredman.fether.blocks.wood.BlockLogBase;
import glowredman.fether.blocks.wood.BlockNetherLog;
import glowredman.fether.blocks.wood.BlockNetherWood;

public class ItemBlockMeta extends ItemMultiTexture {

    public ItemBlockMeta(Block block, BlockLogBase blockLog) {
        super(block, blockLog, blockLog.names);
    }

    public ItemBlockMeta(Block block, BlockNetherLog blockLog) {
        this(block, (BlockLogBase) blockLog);
    }

    public ItemBlockMeta(Block block, BlockNetherWood blockWood) {
        super(block, blockWood, blockWood.names);
    }

    public ItemBlockMeta(Block block, BlockNetherLeaves blockLeaves) {
        super(block, blockLeaves, blockLeaves.func_150125_e());
    }

    public ItemBlockMeta(Block block, BlockNetherSapling blockSapling) {
        super(block, blockSapling, blockSapling.names);
    }
}
