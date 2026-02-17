package glowredman.nood;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;

public class NoodRecipes {

    static void init() {
        addShapelessRecipes();
        addShapedRecipes();
        addSmeltingRecipes();

        if (NoodConfig.enableCrop2SeedRecipes) {
            addSeedRecipes();
        }
    }

    private static void addShapelessRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(NoodBlocks.blockNetherPlanks, 4), NoodBlocks.blockNetherLog);

        GameRegistry.addShapelessRecipe(
            new ItemStack(Blocks.cobblestone, 9),
            Blocks.netherrack,
            Blocks.netherrack,
            Blocks.netherrack,
            Blocks.netherrack,
            Blocks.netherrack,
            Blocks.netherrack,
            Blocks.netherrack,
            Blocks.netherrack,
            Blocks.netherrack);

        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sand, 2), Blocks.gravel, Blocks.gravel);

        GameRegistry.addShapelessRecipe(
            new ItemStack(Items.glowstone_dust, 3),
            NoodBlocks.blockGlowFlower,
            NoodBlocks.blockGlowFlower,
            NoodBlocks.blockGlowFlower);
    }

    private static void addShapedRecipes() {
        GameRegistry
            .addShapedRecipe(new ItemStack(NoodItems.itemQuartzHelmet), "QQQ", "Q Q", 'Q', NoodItems.itemQuartzIngot);

        GameRegistry.addShapedRecipe(
            new ItemStack(NoodItems.itemQuartzChestplate),
            "Q Q",
            "QQQ",
            "QQQ",
            'Q',
            NoodItems.itemQuartzIngot);

        GameRegistry.addShapedRecipe(
            new ItemStack(NoodItems.itemQuartzLeggings),
            "QQQ",
            "Q Q",
            "Q Q",
            'Q',
            NoodItems.itemQuartzIngot);

        GameRegistry
            .addShapedRecipe(new ItemStack(NoodItems.itemQuartzBoots), "Q Q", "Q Q", 'Q', NoodItems.itemQuartzIngot);

        GameRegistry.addShapedRecipe(
            new ItemStack(NoodItems.itemQuartzAxe),
            "QQ",
            "QS",
            " S",
            'Q',
            NoodItems.itemQuartzIngot,
            'S',
            Items.stick);

        GameRegistry.addShapedRecipe(
            new ItemStack(NoodItems.itemQuartzHoe),
            "QQ",
            " S",
            " S",
            'Q',
            NoodItems.itemQuartzIngot,
            'S',
            Items.stick);

        GameRegistry.addShapedRecipe(
            new ItemStack(NoodItems.itemQuartzPickaxe),
            "QQQ",
            " S ",
            " S ",
            'Q',
            NoodItems.itemQuartzIngot,
            'S',
            Items.stick);

        GameRegistry.addShapedRecipe(
            new ItemStack(NoodItems.itemQuartzShovel),
            "Q",
            "S",
            "S",
            'Q',
            NoodItems.itemQuartzIngot,
            'S',
            Items.stick);

        GameRegistry.addShapedRecipe(
            new ItemStack(NoodItems.itemQuartzSword),
            "Q",
            "Q",
            "S",
            'Q',
            NoodItems.itemQuartzIngot,
            'S',
            Items.stick);

        GameRegistry.addShapedRecipe(
            new ItemStack(NoodItems.itemNetherBed),
            "BBB",
            "BBB",
            "PPP",
            'B',
            NoodItems.itemBloodLeaf,
            'P',
            NoodBlocks.blockNetherPlanks);
    }

    private static void addSmeltingRecipes() {
        GameRegistry.addSmelting(Items.quartz, new ItemStack(NoodItems.itemQuartzIngot), 0.2f);
        GameRegistry.addSmelting(NoodItems.itemWeevil, new ItemStack(NoodItems.itemCookedWeevil), 0.35f);
        GameRegistry.addSmelting(NoodItems.itemWeevilEgg, new ItemStack(NoodItems.itemScrambledWeevilEgg), 0.35f);
    }

    private static void addSeedRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(NoodItems.itemBloodLeafSeeds), NoodItems.itemBloodLeaf);

        GameRegistry.addShapelessRecipe(new ItemStack(NoodItems.itemFleshRootSeeds), NoodItems.itemFleshRoot);

        GameRegistry.addShapelessRecipe(new ItemStack(NoodItems.itemMarrowBerrySeeds), NoodItems.itemMarrowBerry);

        GameRegistry.addShapelessRecipe(new ItemStack(NoodItems.itemGlowFlowerSeeds), NoodBlocks.blockGlowFlower);
    }
}
