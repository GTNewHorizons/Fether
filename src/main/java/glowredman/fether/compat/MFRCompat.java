package glowredman.fether.compat;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

import glowredman.fether.FetherBlocks;
import glowredman.fether.FetherItems;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.ValuedItem;

public class MFRCompat {

    public static void init() {
        registerCrop(FetherItems.itemBloodLeafSeeds, FetherItems.itemBloodLeaf, FetherBlocks.blockBloodLeafCrop);
        registerCrop(FetherItems.itemFleshRootSeeds, FetherItems.itemFleshRoot, FetherBlocks.blockFleshRootCrop);
        registerCrop(FetherItems.itemMarrowBerrySeeds, FetherItems.itemMarrowBerry, FetherBlocks.blockBloodLeafCrop);
        registerCrop(
            FetherItems.itemGlowFlowerSeeds,
            Item.getItemFromBlock(FetherBlocks.blockGlowFlower),
            FetherBlocks.blockBloodLeafCrop);
        registerFruitTree(
            FetherBlocks.blockNetherSapling,
            FetherBlocks.blockNetherLog,
            FetherBlocks.blockNetherLeaves,
            FetherBlocks.blockIgnisFruit);
    }

    private static void registerCrop(Item seed, Item yield, Block crop) {
        NBTTagCompound tagFertilizable = new NBTTagCompound();
        tagFertilizable.setString("plant", crop.delegate.name());
        tagFertilizable.setInteger("meta", 7);
        FactoryRegistry.sendMessage("registerFertilizable_Crop", tagFertilizable);

        NBTTagCompound tagPlantableSeed = new NBTTagCompound();
        tagPlantableSeed.setString("seed", seed.delegate.name());
        tagPlantableSeed.setString("crop", crop.delegate.name());
        FactoryRegistry.sendMessage("registerPlantable_Standard", tagPlantableSeed);

        NBTTagCompound tagPlantableYield = new NBTTagCompound();
        tagPlantableYield.setString("seed", yield.delegate.name());
        tagPlantableYield.setString("crop", crop.delegate.name());
        FactoryRegistry.sendMessage("registerPlantable_Standard", tagPlantableYield);

        FactoryRegistry.sendMessage("registerHarvestable_Crop", new ValuedItem(7, crop));
    }

    private static void registerFruitTree(Block sapling, Block log, Block leaves, Block fruit) {
        NBTTagCompound tagFertilizableFruit = new NBTTagCompound();
        tagFertilizableFruit.setString("plant", fruit.delegate.name());
        tagFertilizableFruit.setInteger("meta", 2);
        FactoryRegistry.sendMessage("registerFertilizable_Crop", tagFertilizableFruit);

        NBTTagCompound tagFertilizableSapling = new NBTTagCompound();
        tagFertilizableSapling.setString("plant", sapling.delegate.name());
        FactoryRegistry.sendMessage("registerFertilizable_Standard", tagFertilizableSapling);

        FactoryRegistry.sendMessage("registerHarvestable_Log", log.delegate.name());

        FactoryRegistry.sendMessage("registerHarvestable_Leaves", leaves.delegate.name());

        FactoryRegistry.sendMessage("registerPickableFruit", new FruitStandard(fruit, 2));

        NBTTagCompound tagPlantableSapling = new NBTTagCompound();
        tagPlantableSapling.setString("sapling", sapling.delegate.name());
        FactoryRegistry.sendMessage("registerPlantable_Sapling", tagPlantableSapling);
    }
}
