package glowredman.nood.compat;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import glowredman.nood.NoodBlocks;
import glowredman.nood.NoodItems;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.utils.CropUtils;

public class ThaumcraftCompat {

    public static void init() {
        addStandardCrops(NoodBlocks.blockBloodLeafCrop, 7);
        addStandardCrops(NoodBlocks.blockFleshRootCrop, 7);
        addStandardCrops(NoodBlocks.blockMarrowBerryCrop, 7);
        addStandardCrops(NoodBlocks.blockGlowFlowerCrop, 7);
        addAspects();
    }

    private static void addStandardCrops(@Nonnull Block crop, @Nonnegative int finalStage) {
        // can't use IMC here because it uses ItemStacks and crop may not have a corresponding Item (thus causing a
        // NullPointerException, see issue #1)
        CropUtils.standardCrops.add(crop.getUnlocalizedName() + finalStage);
    }

    private static void addAspects() {
        // spotless:off
        ThaumcraftApi.registerObjectTag(new ItemStack(NoodBlocks.blockNetherLog), new AspectList().add(Aspect.TREE, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(NoodBlocks.blockNetherLeaves), new AspectList().add(Aspect.PLANT, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(NoodBlocks.blockNetherSapling), new AspectList().add(Aspect.TREE, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(NoodBlocks.blockGlowFlower), new AspectList().add(Aspect.HUNGER, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(NoodItems.itemIgnisFruit), new AspectList().add(Aspect.CROP, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(NoodItems.itemBloodLeaf), new AspectList().add(Aspect.HUNGER, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(NoodItems.itemFleshRoot), new AspectList().add(Aspect.HUNGER, 1).add(Aspect.FIRE, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(NoodItems.itemMarrowBerry), new AspectList().add(Aspect.HUNGER, 1).add(Aspect.CROP, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(NoodItems.itemBloodLeafSeeds), new AspectList().add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(NoodItems.itemFleshRootSeeds), new AspectList().add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(NoodItems.itemMarrowBerrySeeds), new AspectList().add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(NoodItems.itemGlowFlowerSeeds), new AspectList().add(Aspect.PLANT, 1));
        // spotless:on
    }
}
