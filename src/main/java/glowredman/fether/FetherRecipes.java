package glowredman.fether;

import java.util.ArrayList;
import java.util.Set;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;

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
            new ItemStack(FetherBlocks.blockNetherWoodPlanks, 4, 0),
            new ItemStack(FetherBlocks.blockNetherLog, 1, OreDictionary.WILDCARD_VALUE));

        GameRegistry.addShapelessRecipe(
            new ItemStack(FetherBlocks.blockNetherWoodPlanks, 4, 1),
            new ItemStack(FetherBlocks.blockLegacyNetherLog, 1, OreDictionary.WILDCARD_VALUE));

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
        if (FetherConfig.enableQuartzItems) {
            GameRegistry.addRecipe(
                new ShapedOreRecipe(new ItemStack(FetherItems.itemQuartzHelmet), "QQQ", "Q Q", 'Q', "ingotQuartz"));

            GameRegistry.addRecipe(
                new ShapedOreRecipe(
                    new ItemStack(FetherItems.itemQuartzChestplate),
                    "Q Q",
                    "QQQ",
                    "QQQ",
                    'Q',
                    "ingotQuartz"));

            GameRegistry.addRecipe(
                new ShapedOreRecipe(
                    new ItemStack(FetherItems.itemQuartzLeggings),
                    "QQQ",
                    "Q Q",
                    "Q Q",
                    'Q',
                    "ingotQuartz"));

            GameRegistry.addRecipe(
                new ShapedOreRecipe(new ItemStack(FetherItems.itemQuartzBoots), "Q Q", "Q Q", 'Q', "ingotQuartz"));

            GameRegistry.addRecipe(
                new ShapedOreRecipe(
                    new ItemStack(FetherItems.itemQuartzAxe),
                    "QQ",
                    "QS",
                    " S",
                    'Q',
                    "ingotQuartz",
                    'S',
                    "stickWood"));

            GameRegistry.addRecipe(
                new ShapedOreRecipe(
                    new ItemStack(FetherItems.itemQuartzHoe),
                    "QQ",
                    " S",
                    " S",
                    'Q',
                    "ingotQuartz",
                    'S',
                    "stickWood"));

            GameRegistry.addRecipe(
                new ShapedOreRecipe(
                    new ItemStack(FetherItems.itemQuartzPickaxe),
                    "QQQ",
                    " S ",
                    " S ",
                    'Q',
                    "ingotQuartz",
                    'S',
                    "stickWood"));

            GameRegistry.addRecipe(
                new ShapedOreRecipe(
                    new ItemStack(FetherItems.itemQuartzShovel),
                    "Q",
                    "S",
                    "S",
                    'Q',
                    "ingotQuartz",
                    'S',
                    "stickWood"));

            GameRegistry.addRecipe(
                new ShapedOreRecipe(
                    new ItemStack(FetherItems.itemQuartzSword),
                    "Q",
                    "Q",
                    "S",
                    'Q',
                    "ingotQuartz",
                    'S',
                    "stickWood"));
        }

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherBlocks.blockNetherWoodSlab, 6, 0),
            "PPP",
            'P',
            new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 0));

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherBlocks.blockNetherWoodSlab, 6, 1),
            "PPP",
            'P',
            new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 1));

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherBlocks.blockNetherWoodStairs),
            "P  ",
            "PP ",
            "PPP",
            'P',
            new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 0));

        GameRegistry.addShapedRecipe(
            new ItemStack(FetherBlocks.blockLegacyNetherWoodStairs),
            "P  ",
            "PP ",
            "PPP",
            'P',
            new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 1));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(FetherBlocks.blockNetherWoodFence, 4),
                "PSP",
                "PSP",
                'P',
                new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 0),
                'S',
                "stickWood"));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(FetherBlocks.blockLegacyNetherWoodFence, 4),
                "PSP",
                "PSP",
                'P',
                new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 1),
                'S',
                "stickWood"));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(FetherBlocks.blockNetherWoodFenceGate),
                "SPS",
                "SPS",
                'P',
                new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 0),
                'S',
                "stickWood"));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(FetherBlocks.blockLegacyNetherWoodFenceGate),
                "SPS",
                "SPS",
                'P',
                new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 1),
                'S',
                "stickWood"));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(FetherBlocks.blockNetherWoodPressurePlate),
                "PP",
                'P',
                new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 0)));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(FetherBlocks.blockLegacyNetherWoodPressurePlate),
                "PP",
                'P',
                new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 1)));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(FetherBlocks.blockNetherWoodButton),
                "P",
                'P',
                new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 0)));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(FetherBlocks.blockLegacyNetherWoodButton),
                "P",
                'P',
                new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 1)));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(FetherBlocks.blockNetherWoodTrapdoor),
                "PPP",
                "PPP",
                'P',
                new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 0)));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(FetherBlocks.blockLegacyNetherWoodTrapdoor),
                "PPP",
                "PPP",
                'P',
                new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 1)));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(FetherItems.itemNetherBed, 1, 0),
                "BBB",
                "BBB",
                "PPP",
                'B',
                "cropBloodleaf",
                'P',
                new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 0)));

        GameRegistry.addRecipe(
            new ShapedOreRecipe(
                new ItemStack(FetherItems.itemNetherBed, 1, 1),
                "BBB",
                "BBB",
                "PPP",
                'B',
                "cropBloodleaf",
                'P',
                new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, 1)));
    }

    private static void addSmeltingRecipes() {
        if (FetherConfig.enableQuartzItems) {
            GameRegistry.addSmelting(Items.quartz, new ItemStack(FetherItems.itemQuartzIngot), 0.2f);
        }
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

    @SuppressWarnings("unchecked")
    public static void modifyVanillaRecipes() {
        Fether.LOGGER.debug("Modifying vanilla recipes...");
        Stopwatch stopwatch = Stopwatch.createStarted();

        int counter = 0;
        Set<Item> targets = Sets.newHashSet(
            Item.getItemFromBlock(Blocks.fence_gate),
            Item.getItemFromBlock(Blocks.wooden_pressure_plate),
            Item.getItemFromBlock(Blocks.wooden_button),
            Item.getItemFromBlock(Blocks.trapdoor));
        ItemStack netherWoodPlanks = new ItemStack(FetherBlocks.blockNetherWoodPlanks, 1, OreDictionary.WILDCARD_VALUE);

        for (IRecipe recipe : CraftingManager.getInstance()
            .getRecipeList()) {
            if (!(recipe instanceof ShapedOreRecipe oreRecipe)) {
                continue;
            }
            if (!targets.contains(
                oreRecipe.getRecipeOutput()
                    .getItem())) {
                continue;
            }

            Object[] input = oreRecipe.getInput();
            for (int i = 0; i < input.length; i++) {
                if (!(input[i] instanceof ArrayList list)) {
                    continue;
                }
                for (int j = 0; j < list.size(); j++) {
                    ItemStack stack = (ItemStack) list.get(j);
                    if (stack.isItemEqual(netherWoodPlanks)) {
                        ArrayList<ItemStack> newList = new ArrayList<>(list);
                        newList.remove(j);
                        input[i] = newList;
                        break;
                    }
                }
            }

            counter++;
        }

        Fether.LOGGER.debug("Done! Modified {} recipes, took {}", counter, stopwatch.stop());
    }
}
