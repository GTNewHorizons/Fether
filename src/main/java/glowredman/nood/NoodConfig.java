package glowredman.nood;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.config.Configuration;

import org.apache.commons.io.FileUtils;

import cpw.mods.fml.common.Loader;

public class NoodConfig {
    
    private static final String CATEGORY_CROPS = "crops";
    private static final String CATEGORY_TREES = "fruit trees";
    private static final String CATEGORY_GARDENS = "gardens";
    private static final String CATEGORY_MISC_RECIPES = "miscellaneous recipes";
    
    // crops
    public static int foodHungerRestore = 1;
    public static float foodSaturationModifier = 0.6f;
    public static boolean cropsDropSeeds = false;
    public static boolean rClickHarvestCrops = true;
    public static boolean rClickMatureCropsShowHearts = false;
    
    // fruit trees
    public static boolean generateNetherTrees = true;
    public static int treeRarity = 15;
    public static boolean rClickHarvestFruits = true;
    public static boolean rClickMatureFruitsShowHearts = false;
    
    // gardens
    public static boolean generateGardens = true;
    public static boolean gardenSpread = true;
    public static int gardenSpreadRate = 100;
    public static int gardenRarity = 4;
    public static int gardenDropAmount = 3;
    public static boolean gardensDropSeeds = false;
    public static boolean generateGlowFlowers = true;
    public static boolean glowFlowerSpread = true;
    public static int glowFlowerSpreadRate = 100;
    public static int glowFlowerRarity = 4;
    public static boolean glowFlowersDropSeeds = false;
    
    // miscellaneous recipes
    public static boolean enableCrop2SeedRecipes = true;
    
    static void init(File configDir) {
        File fileHarvestTheNether = new File(configDir, "harvestthenether.cfg");
        File fileNood = new File(configDir, "nood.cfg");
        
        if(Loader.isModLoaded("harvestthenether")) {
            Nood.LOGGER.warn("Pam's Harvest the Nether is loaded! Consider removing it, its content is fully contained within Nood.");
        } else if(fileHarvestTheNether.exists() && !fileNood.exists()) {
            try {
                FileUtils.moveFile(fileHarvestTheNether, fileNood);
            } catch (IOException e) {
                Nood.LOGGER.error("An error occured while converting Pam's Harvest the Nether's config file.", e);
            }
        }
        
        Configuration cfg = new Configuration(fileNood, Tags.VERSION);
        
        // spotless:off
        foodHungerRestore = cfg.getInt("cropfoodRestore", CATEGORY_CROPS, foodHungerRestore, 0, 20, "How many hunger points crop drops should restore");
        foodSaturationModifier = cfg.getFloat("cropsaturationRestore", CATEGORY_CROPS, foodSaturationModifier, 0.0f, 10.0f, "Saturation modifer of all crop drops");
        cropsDropSeeds = cfg.getBoolean("cropsdropSeeds", CATEGORY_CROPS, cropsDropSeeds, "If crops should drop seeds instead of some of the food");
        rClickHarvestCrops = cfg.getBoolean("rightclickharvestCrop", CATEGORY_CROPS, rClickHarvestCrops, "If crops can be harvested and replaced by richt-clicking them");
        rClickMatureCropsShowHearts = cfg.getBoolean("rightclickmatureshowcropHearts", CATEGORY_CROPS, rClickMatureCropsShowHearts, "If crops show heart particles if fully grown when right-clicked");

        generateNetherTrees = cfg.getBoolean("nethertreeGeneration", CATEGORY_TREES, generateNetherTrees, "If Nether Trees should generate naturally");
        treeRarity = cfg.getInt("treeRarity", CATEGORY_TREES, treeRarity, 0, 1000, "Number of Nether Tree generation attempts per chunk");
        rClickHarvestFruits = cfg.getBoolean("rightclickharvestFruit", CATEGORY_TREES, rClickHarvestFruits, "If fruits can be harvested and replaced by richt-clicking them");
        rClickMatureFruitsShowHearts = cfg.getBoolean("rightclickmatureshowfruitHearts", CATEGORY_TREES, rClickMatureFruitsShowHearts, "If fruits show heart particles if fully grown when right-clicked");

        generateGardens = cfg.getBoolean("enablenethergardenGeneration", CATEGORY_GARDENS, generateGardens, "If Nether Gardens should generate naturally");
        gardenSpread = cfg.getBoolean("enablegardenSpread", CATEGORY_GARDENS, gardenSpread, "If Nether Gardens should spread over time");
        gardenSpreadRate = cfg.getInt("gardenspreadRate", CATEGORY_GARDENS, gardenSpreadRate, 1, Integer.MAX_VALUE, "How many random ticks it takes (on average) for a Nether Garden to spread to another block");
        gardenRarity = cfg.getInt("gardenRarity", CATEGORY_GARDENS, gardenRarity, 0, 1000, "Number of Nether Garden group generation attempts per chunk (one group consists of up to eight Nether Gardens)");
        gardenDropAmount = cfg.getInt("gardendropAmount", CATEGORY_GARDENS, gardenDropAmount, 1, 1024, "How many items should drop when breaking a Nether Garden");
        gardensDropSeeds = cfg.getBoolean("gardensdropSeeds", CATEGORY_GARDENS, gardensDropSeeds, "If Nether Gardens should drop seeds instead of normal food");
        generateGlowFlowers = cfg.getBoolean("enablenetherglowflowerGeneration", CATEGORY_GARDENS, generateGlowFlowers, "If Glow Flowers should generate naturally");
        glowFlowerSpread = cfg.getBoolean("enableglowflowerSpread", CATEGORY_GARDENS, glowFlowerSpread, "If Glow Flowers should spread over time");
        glowFlowerSpreadRate = cfg.getInt("glowflowerspreadRate", CATEGORY_GARDENS, glowFlowerSpreadRate, 1, Integer.MAX_VALUE, "How many random ticks it takes (on average) for a Glow Flower to spread to another block");
        glowFlowerRarity = cfg.getInt("glowflowerRarity", CATEGORY_GARDENS, glowFlowerRarity, 0, 1000, "Number of Glow Flower group generation attempts per chunk (one group consists of up to eight Glow Flowers)");
        glowFlowersDropSeeds = cfg.getBoolean("glowflowersdropSeeds", CATEGORY_GARDENS, glowFlowersDropSeeds, "If Glow Flower blocks should drop Glow Flower Seeds instead of itself");

        enableCrop2SeedRecipes = cfg.getBoolean("enablecroptoseedRecipe", CATEGORY_MISC_RECIPES, enableCrop2SeedRecipes, "If crop drops can be converted to seeds via shapeless crafting");
        // spotless:on
        
        if(cfg.hasChanged()) {
            cfg.save();
        }
    }
}
