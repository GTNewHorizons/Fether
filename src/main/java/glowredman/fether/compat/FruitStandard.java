package glowredman.fether.compat;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import powercrystals.minefactoryreloaded.api.IFactoryFruit;
import powercrystals.minefactoryreloaded.api.ReplacementBlock;

public class FruitStandard implements IFactoryFruit {

    private final Block fruit;
    private final int targetMeta;
    private final ReplacementBlock replacementFruit;

    public FruitStandard(@Nonnull Block fruit, int targetMeta) {
        this.fruit = fruit;
        this.targetMeta = targetMeta;
        this.replacementFruit = new ReplacementBlock(fruit).setMeta(0);
    }

    @Override
    public Block getPlant() {
        return this.fruit;
    }

    @Override
    public boolean canBePicked(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) >= this.targetMeta;
    }

    @Override
    public boolean breakBlock() {
        return false;
    }

    @Override
    public ReplacementBlock getReplacementBlock(World world, int x, int y, int z) {
        return this.replacementFruit;
    }

    @Override
    public List<ItemStack> getDrops(World world, Random rand, int x, int y, int z) {
        return this.fruit.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
    }

    @Override
    public void prePick(World world, int x, int y, int z) {}

    @Override
    public void postPick(World world, int x, int y, int z) {}
}
