package glowredman.nood;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;
import glowredman.nood.blocks.BlockGlowFlower;
import glowredman.nood.blocks.BlockIgnisFruit;
import glowredman.nood.blocks.BlockNetherBed;
import glowredman.nood.blocks.BlockNetherCrops;
import glowredman.nood.blocks.BlockNetherGarden;
import glowredman.nood.blocks.BlockNetherLeaves;
import glowredman.nood.blocks.BlockNetherLog;
import glowredman.nood.blocks.BlockNetherSapling;

public class NoodBlocks {

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
        blockNetherLog = new BlockNetherLog().setCreativeTab(Nood.TAB_NOOD)
            .setBlockName(Nood.MODID + ".nether_log")
            .setBlockTextureName(Nood.MODID + ":nether_log");
        blockNetherLeaves = new BlockNetherLeaves().setCreativeTab(Nood.TAB_NOOD)
            .setBlockName(Nood.MODID + ".nether_leaves")
            .setBlockTextureName(Nood.MODID + ":nether_leaves");
        blockNetherPlanks = new Block(Material.wood) {}.setCreativeTab(Nood.TAB_NOOD)
            .setBlockName(Nood.MODID + ".nether_planks")
            .setBlockTextureName(Nood.MODID + ":nether_planks");
        blockNetherSapling = (BlockSapling) new BlockNetherSapling().setCreativeTab(Nood.TAB_NOOD)
            .setBlockName(Nood.MODID + ".nether_sapling")
            .setBlockTextureName(Nood.MODID + ":nether_sapling")
            .setStepSound(Block.soundTypeGrass);
        blockNetherBed = new BlockNetherBed().setHardness(0.2f)
            .setBlockName(Nood.MODID + ".nether_bed")
            .setBlockTextureName(Nood.MODID + ":nether_bed");
        blockNetherGarden = new BlockNetherGarden().setCreativeTab(Nood.TAB_NOOD)
            .setBlockName(Nood.MODID + ".nether_garden")
            .setBlockTextureName(Nood.MODID + ":nether_garden");
        blockIgnisFruit = new BlockIgnisFruit().setBlockName(Nood.MODID + ".ignis_fruit")
            .setBlockTextureName(Nood.MODID + ":ignis_fruit");
        blockGlowFlower = new BlockGlowFlower().setLightLevel(1.0f)
            .setCreativeTab(Nood.TAB_NOOD)
            .setBlockName(Nood.MODID + ".glow_flower")
            .setBlockTextureName(Nood.MODID + ":glow_flower");
        blockBloodLeafCrop = (BlockNetherCrops) new BlockNetherCrops().setCreativeTab(Nood.TAB_NOOD)
            .setBlockName(Nood.MODID + ".blood_leaf_crop")
            .setBlockTextureName(Nood.MODID + ":blood_leaf_crop");
        blockFleshRootCrop = (BlockNetherCrops) new BlockNetherCrops().setCreativeTab(Nood.TAB_NOOD)
            .setBlockName(Nood.MODID + ".flesh_root_crop")
            .setBlockTextureName(Nood.MODID + ":flesh_root_crop");
        blockMarrowBerryCrop = (BlockNetherCrops) new BlockNetherCrops().setCreativeTab(Nood.TAB_NOOD)
            .setBlockName(Nood.MODID + ".marrow_berry_crop")
            .setBlockTextureName(Nood.MODID + ":marrow_berry_crop");
        blockGlowFlowerCrop = (BlockNetherCrops) new BlockNetherCrops().setLightLevel(1.0f)
            .setCreativeTab(Nood.TAB_NOOD)
            .setBlockName(Nood.MODID + ".glow_flower_crop")
            .setBlockTextureName(Nood.MODID + ":glow_flower_crop");
    }

    private static void registerBlocks() {
        GameRegistry.registerBlock(blockNetherLog, "nether_log");
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
