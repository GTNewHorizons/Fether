package glowredman.fether.blocks.wood;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.util.IIcon;

public class BlockNetherWoodFenceGate extends BlockFenceGate {

    private final Block baseBlock;
    private final int baseMeta;

    public BlockNetherWoodFenceGate(Block baseBlock, int baseMeta) {
        this.baseBlock = baseBlock;
        this.baseMeta = baseMeta;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.baseBlock.getIcon(side, this.baseMeta);
    }
}
