package glowredman.fether.blocks.wood;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

import glowredman.fether.FetherBlocks;

public class BlockNetherWoodFence extends BlockFence {

    public BlockNetherWoodFence(String p_i45406_1_, Material p_i45406_2_) {
        super(p_i45406_1_, p_i45406_2_);
    }

    @Override
    public boolean canConnectFenceTo(IBlockAccess p_149826_1_, int p_149826_2_, int p_149826_3_, int p_149826_4_) {
        Block block = p_149826_1_.getBlock(p_149826_2_, p_149826_3_, p_149826_4_);
        if (block != this && block != FetherBlocks.blockNetherWoodFenceGate) {
            if (block.getMaterial()
                .isOpaque() && block.renderAsNormalBlock()) {
                return block.getMaterial() != Material.gourd;
            }
            return false;
        }
        return true;
    }
}
