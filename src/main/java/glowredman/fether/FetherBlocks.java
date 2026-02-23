package glowredman.fether;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;
import glowredman.fether.blocks.BlockGlowFlower;
import glowredman.fether.blocks.BlockIgnisFruit;
import glowredman.fether.blocks.BlockNetherBed;
import glowredman.fether.blocks.BlockNetherCrops;
import glowredman.fether.blocks.BlockNetherGarden;
import glowredman.fether.blocks.BlockNetherLeaves;
import glowredman.fether.blocks.BlockNetherLog;
import glowredman.fether.blocks.BlockNetherSapling;
import glowredman.fether.items.blocks.ItemBlockNetherLog;

public class FetherBlocks {

    public static Block blockNetherLog;
    public static Block blockNetherLeaves;
    public static Block blockNetherPlanks;
    public static BlockSapling blockNetherSapling;
    public static Block blockNetherBed;
    public static Block blockNetherGarden;
    public static Block blockIgnisFruit;
    public static Block blockGlowFlower;
    public static BlockNetherCrops blockBloodLeafCrop;
    public static BlockNetherCrops blockFleshRootCrop;
    public static BlockNetherCrops blockMarrowBerryCrop;
    public static BlockNetherCrops blockGlowFlowerCrop;

    static void init() {
        createBlocks();
        registerBlocks();
        addODTags();
    }

    private static void createBlocks() {
        blockNetherLog = new BlockNetherLog().setCreativeTab(Fether.TAB_NOOD)
            .setBlockName(Fether.MODID + ".nether_log")
            .setBlockTextureName(Fether.MODID + ":nether_log");
        blockNetherLeaves = new BlockNetherLeaves().setCreativeTab(Fether.TAB_NOOD)
            .setBlockName(Fether.MODID + ".nether_leaves")
            .setBlockTextureName(Fether.MODID + ":nether_leaves");
        blockNetherPlanks = new Block(Material.wood) {}.setCreativeTab(Fether.TAB_NOOD)
            .setBlockName(Fether.MODID + ".nether_planks")
            .setBlockTextureName(Fether.MODID + ":nether_planks");
        blockNetherSapling = (BlockSapling) new BlockNetherSapling().setCreativeTab(Fether.TAB_NOOD)
            .setBlockName(Fether.MODID + ".nether_sapling")
            .setBlockTextureName(Fether.MODID + ":nether_sapling")
            .setStepSound(Block.soundTypeGrass);
        blockNetherBed = new BlockNetherBed().setHardness(0.2f)
            .setBlockName(Fether.MODID + ".nether_bed")
            .setBlockTextureName(Fether.MODID + ":nether_bed");
        blockNetherGarden = new BlockNetherGarden().setCreativeTab(Fether.TAB_NOOD)
            .setBlockName(Fether.MODID + ".nether_garden")
            .setBlockTextureName(Fether.MODID + ":nether_garden");
        blockIgnisFruit = new BlockIgnisFruit().setBlockName(Fether.MODID + ".ignis_fruit")
            .setBlockTextureName(Fether.MODID + ":ignis_fruit");
        blockGlowFlower = new BlockGlowFlower().setLightLevel(1.0f)
            .setCreativeTab(Fether.TAB_NOOD)
            .setBlockName(Fether.MODID + ".glow_flower")
            .setBlockTextureName(Fether.MODID + ":glow_flower");
        blockBloodLeafCrop = (BlockNetherCrops) new BlockNetherCrops().setCreativeTab(Fether.TAB_NOOD)
            .setBlockName(Fether.MODID + ".blood_leaf_crop")
            .setBlockTextureName(Fether.MODID + ":blood_leaf_crop");
        blockFleshRootCrop = (BlockNetherCrops) new BlockNetherCrops().setCreativeTab(Fether.TAB_NOOD)
            .setBlockName(Fether.MODID + ".flesh_root_crop")
            .setBlockTextureName(Fether.MODID + ":flesh_root_crop");
        blockMarrowBerryCrop = (BlockNetherCrops) new BlockNetherCrops().setCreativeTab(Fether.TAB_NOOD)
            .setBlockName(Fether.MODID + ".marrow_berry_crop")
            .setBlockTextureName(Fether.MODID + ":marrow_berry_crop");
        blockGlowFlowerCrop = (BlockNetherCrops) new BlockNetherCrops().setLightLevel(1.0f)
            .setCreativeTab(Fether.TAB_NOOD)
            .setBlockName(Fether.MODID + ".glow_flower_crop")
            .setBlockTextureName(Fether.MODID + ":glow_flower_crop");
    }

    private static void registerBlocks() {
        GameRegistry.registerBlock(blockNetherLog, ItemBlockNetherLog.class, "nether_log");
        GameRegistry.registerBlock(blockNetherLeaves, "nether_leaves");
        GameRegistry.registerBlock(blockNetherPlanks, "nether_planks");
        GameRegistry.registerBlock(blockNetherSapling, "nether_sapling");
        GameRegistry.registerBlock(blockNetherBed, null, "nether_bed");
        GameRegistry.registerBlock(blockNetherGarden, "nether_garden");
        GameRegistry.registerBlock(blockIgnisFruit, null, "ignis_fruit");
        GameRegistry.registerBlock(blockGlowFlower, "glow_flower");
        GameRegistry.registerBlock(blockBloodLeafCrop, null, "blood_leaf_crop");
        GameRegistry.registerBlock(blockFleshRootCrop, null, "flesh_root_crop");
        GameRegistry.registerBlock(blockMarrowBerryCrop, null, "marrow_berry_crop");
        GameRegistry.registerBlock(blockGlowFlowerCrop, null, "glow_flower_crop");
    }

    private static void addODTags() {
        OreDictionary.registerOre("treeWood", blockNetherLog);
        OreDictionary.registerOre("treeLeaves", blockNetherLeaves);
        OreDictionary.registerOre("plankWood", blockNetherPlanks);
        OreDictionary.registerOre("treeSapling", blockNetherSapling);
    }
}
