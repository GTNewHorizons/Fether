package glowredman.nood;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;

import net.minecraftforge.common.config.Configuration;

import org.apache.commons.io.FileUtils;

import cpw.mods.fml.common.Loader;

public class NoodConfig {

    private static final String CATEGORY_CROPS = "crops";
    private static final String CATEGORY_TREES = "fruit trees";
    private static final String CATEGORY_GARDENS = "gardens";
    @Deprecated
    private static final String CATEGORY_MISC_RECIPES = "miscellaneous recipes";
    private static final String CATEGORY_RECIPES = "recipes";

    // general
    public static boolean enableNoodAI = true;
    public static float aiRandomness = 0.1f;
    public static int foodHungerRestore = 1;
    public static float foodSaturationModifier = 0.6f;

    // crops
    public static boolean cropsDropSeeds = false;
    public static boolean rClickHarvestCrops = true;
    public static boolean rClickMatureCropsShowHearts = false;

    // fruit trees
    public static int treeRarity = 15;
    public static boolean rClickHarvestFruits = true;
    public static boolean rClickMatureFruitsShowHearts = false;

    // gardens
    public static int gardenSpreadRate = 100;
    public static int gardenRarity = 4;
    public static int gardenDropAmount = 3;
    public static boolean gardensDropSeeds = false;
    public static int glowFlowerSpreadRate = 100;
    public static int glowFlowerRarity = 4;
    public static boolean glowFlowersDropSeeds = false;

    // miscellaneous recipes
    public static boolean enableCrop2SeedRecipes = true;
    public static boolean isArmorRepairable = true;
    public static boolean areToolsRepairable = true;

    static void init(File configDir) {
        File fileHarvestTheNether = new File(configDir, "harvestthenether.cfg");
        File fileNood = new File(configDir, "nood.cfg");

        if (Loader.isModLoaded("harvestthenether")) {
            Nood.LOGGER.warn(
                "Pam's Harvest the Nether is loaded! Consider removing it, its content is fully contained within Nood.");
        } else if (fileHarvestTheNether.exists() && !fileNood.exists()) {
            try {
                FileUtils.moveFile(fileHarvestTheNether, fileNood);
            } catch (IOException e) {
                Nood.LOGGER.error("An error occured while converting Pam's Harvest the Nether's config file.", e);
            }
        }

        Configuration cfg = new Configuration(fileNood, Tags.VERSION);

        // backwards config
        cfg.moveProperty(CATEGORY_MISC_RECIPES, "enablecroptoseedRecipe", CATEGORY_RECIPES);
        cfg.moveProperty(CATEGORY_CROPS, "cropfoodRestore", Configuration.CATEGORY_GENERAL);
        cfg.moveProperty(CATEGORY_CROPS, "cropsaturationRestore", Configuration.CATEGORY_GENERAL);
        cfg.removeCategory(cfg.getCategory(CATEGORY_MISC_RECIPES));

        cfg.renameProperty(Configuration.CATEGORY_GENERAL, "cropfoodRestore", "foodHungerRestore");
        cfg.renameProperty(Configuration.CATEGORY_GENERAL, "cropsaturationRestore", "foodSaturationModifier");
        cfg.renameProperty(CATEGORY_CROPS, "cropsdropSeeds", "cropsDropSeeds");
        cfg.renameProperty(CATEGORY_CROPS, "rightclickharvestCrop", "rClickHarvestCrops");
        cfg.renameProperty(CATEGORY_CROPS, "rightclickmatureshowcropHearts", "rClickMatureCropsShowHearts");
        cfg.renameProperty(CATEGORY_TREES, "rightclickharvestFruit", "rClickHarvestFruits");
        cfg.renameProperty(CATEGORY_TREES, "rightclickmatureshowfruitHearts", "rClickMatureFruitsShowHearts");
        cfg.renameProperty(CATEGORY_GARDENS, "gardenspreadRate", "gardenSpreadRate");
        cfg.renameProperty(CATEGORY_GARDENS, "gardendropAmount", "gardenDropAmount");
        cfg.renameProperty(CATEGORY_GARDENS, "gardensdropSeeds", "gardensDropSeeds");
        cfg.renameProperty(CATEGORY_GARDENS, "glowflowerspreadRate", "glowFlowerSpreadRate");
        cfg.renameProperty(CATEGORY_GARDENS, "glowflowerRarity", "glowFlowerRarity");
        cfg.renameProperty(CATEGORY_GARDENS, "glowflowersdropSeeds", "glowFlowersDropSeeds");
        cfg.renameProperty(CATEGORY_RECIPES, "enablecroptoseedRecipe", "enableCrop2SeedRecipes");

        convert(cfg, CATEGORY_TREES, "nethertreeGeneration", "treeRarity", treeRarity);
        convert(cfg, CATEGORY_GARDENS, "enablegardenSpread", "gardenSpreadRate", gardenSpreadRate);
        convert(cfg, CATEGORY_GARDENS, "enablenethergardenGeneration", "gardenRarity", gardenRarity);
        convert(cfg, CATEGORY_GARDENS, "enableglowflowerSpread", "glowFlowerSpreadRate", glowFlowerSpreadRate);
        convert(cfg, CATEGORY_GARDENS, "enablenetherglowflowerGeneration", "glowFlowerRarity", glowFlowerRarity);

        // spotless:off
        enableNoodAI = cfg.getBoolean("enableNoodAI", Configuration.CATEGORY_GENERAL, enableNoodAI, "(CLIENT ONLY) If the \"/noodai\" command should be enabled");
        aiRandomness = cfg.getFloat("aiRandomness", Configuration.CATEGORY_GENERAL, aiRandomness, 0.0f, 1.0f, "(CLIENT ONLY) What percentage of responses to the same prompt in \"/noodai text <prompt>\" should return a random response");
        foodHungerRestore = cfg.getInt("foodHungerRestore", Configuration.CATEGORY_GENERAL, foodHungerRestore, 0, 20, "How many hunger points crop drops should restore");
        foodSaturationModifier = cfg.getFloat("foodSaturationModifier", Configuration.CATEGORY_GENERAL, foodSaturationModifier, 0.0f, 10.0f, "Saturation modifer of all crop drops");

        cropsDropSeeds = cfg.getBoolean("cropsDropSeeds", CATEGORY_CROPS, cropsDropSeeds, "If crops should drop seeds instead of some of the food");
        rClickHarvestCrops = cfg.getBoolean("rClickHarvestCrops", CATEGORY_CROPS, rClickHarvestCrops, "If crops can be harvested and replaced by richt-clicking them");
        rClickMatureCropsShowHearts = cfg.getBoolean("rClickMatureCropsShowHearts", CATEGORY_CROPS, rClickMatureCropsShowHearts, "If crops show heart particles if fully grown when right-clicked");

        treeRarity = cfg.getInt("treeRarity", CATEGORY_TREES, treeRarity, 0, 1000, "Number of Nether Tree generation attempts per chunk");
        rClickHarvestFruits = cfg.getBoolean("rClickHarvestFruits", CATEGORY_TREES, rClickHarvestFruits, "If fruits can be harvested and replaced by richt-clicking them");
        rClickMatureFruitsShowHearts = cfg.getBoolean("rClickMatureFruitsShowHearts", CATEGORY_TREES, rClickMatureFruitsShowHearts, "If fruits show heart particles if fully grown when right-clicked");

        gardenSpreadRate = cfg.getInt("gardenSpreadRate", CATEGORY_GARDENS, gardenSpreadRate, 0, Integer.MAX_VALUE, "How many random ticks it takes (on average) for a Nether Garden to spread to another block (set to 0 to disable spreading)");
        gardenRarity = cfg.getInt("gardenRarity", CATEGORY_GARDENS, gardenRarity, 0, 1000, "Number of Nether Garden group generation attempts per chunk (one group consists of up to eight Nether Gardens)");
        gardenDropAmount = cfg.getInt("gardenDropAmount", CATEGORY_GARDENS, gardenDropAmount, 1, 1024, "How many items should drop when breaking a Nether Garden");
        gardensDropSeeds = cfg.getBoolean("gardensDropSeeds", CATEGORY_GARDENS, gardensDropSeeds, "If Nether Gardens should drop seeds instead of normal food");
        glowFlowerSpreadRate = cfg.getInt("glowFlowerSpreadRate", CATEGORY_GARDENS, glowFlowerSpreadRate, 0, Integer.MAX_VALUE, "How many random ticks it takes (on average) for a Glow Flower to spread to another block (set to 0 to disable spreading)");
        glowFlowerRarity = cfg.getInt("glowFlowerRarity", CATEGORY_GARDENS, glowFlowerRarity, 0, 1000, "Number of Glow Flower group generation attempts per chunk (one group consists of up to eight Glow Flowers)");
        glowFlowersDropSeeds = cfg.getBoolean("glowFlowersDropSeeds", CATEGORY_GARDENS, glowFlowersDropSeeds, "If Glow Flower blocks should drop Glow Flower Seeds instead of itself");

        enableCrop2SeedRecipes = cfg.getBoolean("enableCrop2SeedRecipes", CATEGORY_RECIPES, enableCrop2SeedRecipes, "If crop drops can be converted to seeds via shapeless crafting");
        isArmorRepairable = cfg.getBoolean("isArmorRepairable", CATEGORY_RECIPES, isArmorRepairable, "If Quartz Armor can be repaired using Quartzite Ingots");
        areToolsRepairable = cfg.getBoolean("areToolsRepairable", CATEGORY_RECIPES, areToolsRepairable, "If Quartz Tools / Weapons can be repaired using Quartzite Ingots");
        // spotless:on

        if (cfg.hasChanged()) {
            cfg.save();
        }
    }

    private static void convert(@Nonnull Configuration cfg, String category, String propertyBoolean, String propertyInt,
        int defaultInt) {
        if (cfg.hasKey(category, propertyBoolean)) {
            if (cfg.getBoolean(propertyBoolean, category, false, null)) {
                cfg.get(category, propertyInt, defaultInt)
                    .set(0);
            }
            cfg.getCategory(category)
                .remove(propertyBoolean);
        }
    }
}
