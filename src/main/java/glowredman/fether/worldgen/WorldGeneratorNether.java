package glowredman.fether.worldgen;

import java.util.Random;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorNether implements IWorldGenerator {

    private final int rarity;
    private final WorldGenerator generator;

    public WorldGeneratorNether(@Nonnegative int rarity, @Nonnull WorldGenerator generator) {
        this.rarity = rarity;
        this.generator = generator;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
        IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == -1) {
            for (int i = 0; i < this.rarity; i++) {
                int x = (chunkX << 4) + random.nextInt(16);
                int y = random.nextInt(128);
                int z = (chunkZ << 4) + random.nextInt(16);
                this.generator.generate(world, random, x, y, z);
            }
        }
    }
}
