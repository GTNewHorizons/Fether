package glowredman.fether;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;

public class FetherRecipes {

    static void init() {
        addShapelessRecipes();
        addShapedRecipes();
        addSmeltingRecipes();

        if (FetherConfig.enableCrop2SeedRecipes) {
            addSeedRecipes();
        }
    }

    private static void addShapelessRecipes() {
        GameRegistry.addShapelessRecipe(
            new ItemStack(FetherBlocks.blockNetherPlanks, 4),
            new ItemStack(FetherBlocks.blockNetherLog, 1, OreDictionary.WILDCARD_VALUE));

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
            FetherBlocks.blockGlowFlower,
            FetherBlocks.blockGlowFlower,
            FetherBlocks.blockGlowFlower);
    }

    private static void addShapedRecipes() {
        GameRegistry.addShapedRecipe(
            new ItemStack(FetherItems.itemQuartzHelmet),
            "QQQ",
            "Q Q",
            'Q',
            FetherItems.itemQuartzIngot);

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherItems.itemQuartzChestplate),
            "Q Q",
            "QQQ",
            "QQQ",
            'Q',
            FetherItems.itemQuartzIngot);

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherItems.itemQuartzLeggings),
            "QQQ",
            "Q Q",
            "Q Q",
            'Q',
            FetherItems.itemQuartzIngot);

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherItems.itemQuartzBoots),
            "Q Q",
            "Q Q",
            'Q',
            FetherItems.itemQuartzIngot);

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherItems.itemQuartzAxe),
            "QQ",
            "QS",
            " S",
            'Q',
            FetherItems.itemQuartzIngot,
            'S',
            Items.stick);

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherItems.itemQuartzHoe),
            "QQ",
            " S",
            " S",
            'Q',
            FetherItems.itemQuartzIngot,
            'S',
            Items.stick);

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherItems.itemQuartzPickaxe),
            "QQQ",
            " S ",
            " S ",
            'Q',
            FetherItems.itemQuartzIngot,
            'S',
            Items.stick);

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherItems.itemQuartzShovel),
            "Q",
            "S",
            "S",
            'Q',
            FetherItems.itemQuartzIngot,
            'S',
            Items.stick);

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherItems.itemQuartzSword),
            "Q",
            "Q",
            "S",
            'Q',
            FetherItems.itemQuartzIngot,
            'S',
            Items.stick);

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherItems.itemNetherBed),
            "BBB",
            "BBB",
            "PPP",
            'B',
            FetherItems.itemBloodLeaf,
            'P',
            FetherBlocks.blockNetherPlanks);
    }

    private static void addSmeltingRecipes() {
        GameRegistry.addSmelting(Items.quartz, new ItemStack(FetherItems.itemQuartzIngot), 0.2f);
        GameRegistry.addSmelting(FetherItems.itemWeevil, new ItemStack(FetherItems.itemCookedWeevil), 0.35f);
        GameRegistry.addSmelting(FetherItems.itemWeevilEgg, new ItemStack(FetherItems.itemScrambledWeevilEgg), 0.35f);
        GameRegistry.addSmelting(FetherBlocks.blockNetherLog, new ItemStack(Items.coal, 1, 1), 0.15f);
    }

    private static void addSeedRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(FetherItems.itemBloodLeafSeeds), FetherItems.itemBloodLeaf);

        GameRegistry.addShapelessRecipe(new ItemStack(FetherItems.itemFleshRootSeeds), FetherItems.itemFleshRoot);

        GameRegistry.addShapelessRecipe(new ItemStack(FetherItems.itemMarrowBerrySeeds), FetherItems.itemMarrowBerry);

        GameRegistry.addShapelessRecipe(new ItemStack(FetherItems.itemGlowFlowerSeeds), FetherBlocks.blockGlowFlower);
    }
}
