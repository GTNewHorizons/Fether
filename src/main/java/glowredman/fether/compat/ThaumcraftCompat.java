package glowredman.fether.compat;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import glowredman.fether.FetherBlocks;
import glowredman.fether.FetherItems;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.utils.CropUtils;

public class ThaumcraftCompat {

    public static void init() {
        addStandardCrops(FetherBlocks.blockBloodLeafCrop, 7);
        addStandardCrops(FetherBlocks.blockFleshRootCrop, 7);
        addStandardCrops(FetherBlocks.blockMarrowBerryCrop, 7);
        addStandardCrops(FetherBlocks.blockGlowFlowerCrop, 7);
        addAspects();
    }

    private static void addStandardCrops(@Nonnull Block crop, @Nonnegative int finalStage) {
        // can't use IMC here because it uses ItemStacks and crop may not have a corresponding Item (thus causing a
        // NullPointerException, see issue #1)
        CropUtils.standardCrops.add(crop.getUnlocalizedName() + finalStage);
    }

    private static void addAspects() {
        // spotless:off
        ThaumcraftApi.registerObjectTag(new ItemStack(FetherBlocks.blockNetherLog), new AspectList().add(Aspect.TREE, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(FetherBlocks.blockNetherLeaves), new AspectList().add(Aspect.PLANT, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(FetherBlocks.blockNetherSapling), new AspectList().add(Aspect.TREE, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(FetherBlocks.blockGlowFlower), new AspectList().add(Aspect.HUNGER, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(FetherItems.itemIgnisFruit), new AspectList().add(Aspect.CROP, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(FetherItems.itemBloodLeaf), new AspectList().add(Aspect.HUNGER, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(FetherItems.itemFleshRoot), new AspectList().add(Aspect.HUNGER, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(FetherItems.itemMarrowBerry), new AspectList().add(Aspect.HUNGER, 1).add(Aspect.CROP, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(FetherItems.itemBloodLeafSeeds), new AspectList().add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(FetherItems.itemFleshRootSeeds), new AspectList().add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(FetherItems.itemMarrowBerrySeeds), new AspectList().add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(FetherItems.itemGlowFlowerSeeds), new AspectList().add(Aspect.PLANT, 1));
        // spotless:on
    }
}
