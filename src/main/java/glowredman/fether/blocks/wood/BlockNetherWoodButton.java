package glowredman.fether.blocks.wood;

import net.minecraft.block.Block;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.util.IIcon;

public class BlockNetherWoodButton extends BlockButtonWood {

    private final Block baseBlock;
    private final int baseMeta;

    public BlockNetherWoodButton(Block baseBlock, int baseMeta) {
        this.baseBlock = baseBlock;
        this.baseMeta = baseMeta;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.baseBlock.getIcon(1, this.baseMeta);
    }
}
