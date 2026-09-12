package glowredman.fether;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class FetherConfig {

    private static final String CATEGORY_CROPS = "crops";
    private static final String CATEGORY_TREES = "fruit trees";
    private static final String CATEGORY_GARDENS = "gardens";
    private static final String CATEGORY_RECIPES = "recipes";

    // general
    public static boolean enableFetherAI = true;
    public static float aiRandomness = 0.1f;
    public static int foodHungerRestore = 1;
    public static float foodSaturationModifier = 0.6f;
    public static boolean enableQuartzItems = true;

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

    static void init(File configFile) {
        Configuration cfg = new Configuration(configFile, Tags.VERSION);

        // TODO: convert from pam's harvest the nether config (see
        // https://github.com/GTNewHorizons/Fether/commit/68faa2ceb4e7cfeb3ad3e1fa7c879e43c31ba671
        // for an earlier attempt

        // spotless:off
        enableFetherAI = cfg.getBoolean("enableFetherAI", Configuration.CATEGORY_GENERAL, enableFetherAI, "(CLIENT ONLY) If the \"/fetherai\" command should be enabled");
        aiRandomness = cfg.getFloat("aiRandomness", Configuration.CATEGORY_GENERAL, aiRandomness, 0.0f, 1.0f, "(CLIENT ONLY) What percentage of responses to the same prompt in \"/fetherai text <prompt>\" should return a random response");
        foodHungerRestore = cfg.getInt("foodHungerRestore", Configuration.CATEGORY_GENERAL, foodHungerRestore, 0, 20, "How many hunger points crop drops should restore");
        foodSaturationModifier = cfg.getFloat("foodSaturationModifier", Configuration.CATEGORY_GENERAL, foodSaturationModifier, 0.0f, 10.0f, "Saturation modifer of all crop drops");
        enableQuartzItems = cfg.getBoolean("enableQuartzItems", Configuration.CATEGORY_GENERAL, enableQuartzItems, "If Quartz Tools, Quartz Armor and the Quartz Ingot should be registered. WARNING: Changing this to false can cause problems with existing worlds!");

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
}
