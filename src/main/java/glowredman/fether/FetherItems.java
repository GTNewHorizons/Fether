package glowredman.fether;

import static glowredman.fether.Fether.MODID;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;
import glowredman.fether.items.ItemFoodWithTooltip;
import glowredman.fether.items.ItemNetherBed;
import glowredman.fether.items.ItemNetherSeedFood;
import glowredman.fether.items.ItemQuartzArmor;

public class FetherItems {

    public static final ArmorMaterial ARMOR_MATERIAL_QUARTZ = EnumHelper
        .addArmorMaterial("QUARTZ", 15, new int[] { 2, 6, 5, 2 }, 9);
    public static final ToolMaterial TOOL_MATERIAL_QUARTZ = EnumHelper
        .addToolMaterial("QUARTZ", 2, 250, 6.0f, 2.0f, 14);

    public static Item itemQuartzIngot;
    public static Item itemQuartzHelmet;
    public static Item itemQuartzChestplate;
    public static Item itemQuartzLeggings;
    public static Item itemQuartzBoots;
    public static Item itemQuartzAxe;
    public static Item itemQuartzHoe;
    public static Item itemQuartzPickaxe;
    public static Item itemQuartzShovel;
    public static Item itemQuartzSword;
    public static Item itemIgnisFruit;
    public static Item itemNetherBed;
    public static Item itemBloodLeaf;
    public static Item itemFleshRoot;
    public static Item itemMarrowBerry;
    public static Item itemBloodLeafSeeds;
    public static Item itemFleshRootSeeds;
    public static Item itemMarrowBerrySeeds;
    public static Item itemGlowFlowerSeeds;
    public static Item itemWeevil;
    public static Item itemWeevilEgg;
    public static Item itemCookedWeevil;
    public static Item itemScrambledWeevilEgg;

    static void init() {
        createItems();
        registerItems();
        addODTags();

        FetherBlocks.blockBloodLeafCrop.setItems(itemBloodLeaf, itemBloodLeafSeeds);
        FetherBlocks.blockFleshRootCrop.setItems(itemFleshRoot, itemFleshRootSeeds);
        FetherBlocks.blockMarrowBerryCrop.setItems(itemMarrowBerry, itemMarrowBerrySeeds);
        FetherBlocks.blockGlowFlowerCrop
            .setItems(Item.getItemFromBlock(FetherBlocks.blockGlowFlower), itemGlowFlowerSeeds);

        if (FetherConfig.isArmorRepairable && FetherConfig.enableQuartzItems) {
            ARMOR_MATERIAL_QUARTZ.customCraftingMaterial = itemQuartzIngot;
        }
        if (FetherConfig.areToolsRepairable && FetherConfig.enableQuartzItems) {
            TOOL_MATERIAL_QUARTZ.setRepairItem(new ItemStack(itemQuartzIngot));
        }
    }

    private static void createItems() {
        if (FetherConfig.enableQuartzItems) {
            itemQuartzIngot = new Item().setCreativeTab(Fether.TAB_NOOD)
                .setTextureName(MODID + ":quartz_ingot")
                .setUnlocalizedName(MODID + ".quartz_ingot");
            itemQuartzHelmet = new ItemQuartzArmor(0).setCreativeTab(Fether.TAB_NOOD)
                .setTextureName(MODID + ":quartz_helmet")
                .setUnlocalizedName(MODID + ".quartz_helmet");
            itemQuartzChestplate = new ItemQuartzArmor(1).setCreativeTab(Fether.TAB_NOOD)
                .setTextureName(MODID + ":quartz_chestplate")
                .setUnlocalizedName(MODID + ".quartz_chestplate");
            itemQuartzLeggings = new ItemQuartzArmor(2).setCreativeTab(Fether.TAB_NOOD)
                .setTextureName(MODID + ":quartz_leggings")
                .setUnlocalizedName(MODID + ".quartz_leggings");
            itemQuartzBoots = new ItemQuartzArmor(3).setCreativeTab(Fether.TAB_NOOD)
                .setTextureName(MODID + ":quartz_boots")
                .setUnlocalizedName(MODID + ".quartz_boots");
            itemQuartzAxe = new ItemAxe(TOOL_MATERIAL_QUARTZ) {}.setCreativeTab(Fether.TAB_NOOD)
                .setTextureName(MODID + ":quartz_axe")
                .setUnlocalizedName(MODID + ".quartz_axe");
            itemQuartzHoe = new ItemHoe(TOOL_MATERIAL_QUARTZ).setCreativeTab(Fether.TAB_NOOD)
                .setTextureName(MODID + ":quartz_hoe")
                .setUnlocalizedName(MODID + ".quartz_hoe");
            itemQuartzPickaxe = new ItemPickaxe(TOOL_MATERIAL_QUARTZ) {}.setCreativeTab(Fether.TAB_NOOD)
                .setTextureName(MODID + ":quartz_pickaxe")
                .setUnlocalizedName(MODID + ".quartz_pickaxe");
            itemQuartzShovel = new ItemSpade(TOOL_MATERIAL_QUARTZ).setCreativeTab(Fether.TAB_NOOD)
                .setTextureName(MODID + ":quartz_shovel")
                .setUnlocalizedName(MODID + ".quartz_shovel");
            itemQuartzSword = new ItemSword(TOOL_MATERIAL_QUARTZ).setCreativeTab(Fether.TAB_NOOD)
                .setTextureName(MODID + ":quartz_sword")
                .setUnlocalizedName(MODID + ".quartz_sword");
        }
        itemIgnisFruit = new ItemFood(FetherConfig.foodHungerRestore, FetherConfig.foodSaturationModifier, false)
            .setPotionEffect(Potion.fireResistance.id, 5, 0, 0.9f)
            .setCreativeTab(Fether.TAB_NOOD)
            .setTextureName(MODID + ":ignis_fruit")
            .setUnlocalizedName(MODID + ".ignis_fruit");
        itemNetherBed = new ItemNetherBed().setCreativeTab(Fether.TAB_NOOD)
            .setTextureName(MODID + ":nether_bed")
            .setUnlocalizedName(MODID + ".nether_bed");
        itemBloodLeaf = new ItemNetherSeedFood(FetherBlocks.blockBloodLeafCrop).setCreativeTab(Fether.TAB_NOOD)
            .setTextureName(MODID + ":blood_leaf")
            .setUnlocalizedName(MODID + ".blood_leaf");
        itemFleshRoot = new ItemNetherSeedFood(FetherBlocks.blockFleshRootCrop).setCreativeTab(Fether.TAB_NOOD)
            .setTextureName(MODID + ":flesh_root")
            .setUnlocalizedName(MODID + ".flesh_root");
        itemMarrowBerry = new ItemNetherSeedFood(FetherBlocks.blockMarrowBerryCrop).setCreativeTab(Fether.TAB_NOOD)
            .setTextureName(MODID + ":marrow_berry")
            .setUnlocalizedName(MODID + ".marrow_berry");
        itemBloodLeafSeeds = new ItemNetherSeedFood(FetherBlocks.blockBloodLeafCrop).setCreativeTab(Fether.TAB_NOOD)
            .setTextureName(MODID + ":blood_leaf_seeds")
            .setUnlocalizedName(MODID + ".blood_leaf_seeds");
        itemFleshRootSeeds = new ItemNetherSeedFood(FetherBlocks.blockFleshRootCrop).setCreativeTab(Fether.TAB_NOOD)
            .setTextureName(MODID + ":flesh_root_seeds")
            .setUnlocalizedName(MODID + ".flesh_root_seeds");
        itemMarrowBerrySeeds = new ItemNetherSeedFood(FetherBlocks.blockMarrowBerryCrop).setCreativeTab(Fether.TAB_NOOD)
            .setTextureName(MODID + ":marrow_berry_seeds")
            .setUnlocalizedName(MODID + ".marrow_berry_seeds");
        itemGlowFlowerSeeds = new ItemNetherSeedFood(FetherBlocks.blockGlowFlowerCrop).setCreativeTab(Fether.TAB_NOOD)
            .setTextureName(MODID + ":glow_flower_seeds")
            .setUnlocalizedName(MODID + ".glow_flower_seeds");
        itemWeevil = new ItemFoodWithTooltip(
            2 * FetherConfig.foodHungerRestore,
            FetherConfig.foodSaturationModifier,
            false).setPotionEffect(Potion.hunger.id, 30, 0, 0.3F)
                .setCreativeTab(Fether.TAB_NOOD)
                .setTextureName(MODID + ":weevil")
                .setUnlocalizedName(MODID + ".weevil");
        itemWeevilEgg = new ItemFood(FetherConfig.foodHungerRestore, FetherConfig.foodSaturationModifier, false)
            .setCreativeTab(Fether.TAB_NOOD)
            .setTextureName(MODID + ":weevil_egg")
            .setUnlocalizedName(MODID + ".weevil_egg");
        itemCookedWeevil = new ItemFood(2 * FetherConfig.foodHungerRestore, FetherConfig.foodSaturationModifier, false)
            .setCreativeTab(Fether.TAB_NOOD)
            .setTextureName(MODID + ":cooked_weevil")
            .setUnlocalizedName(MODID + ".cooked_weevil");
        itemScrambledWeevilEgg = new ItemFood(
            3 * FetherConfig.foodHungerRestore,
            FetherConfig.foodSaturationModifier,
            false).setCreativeTab(Fether.TAB_NOOD)
                .setTextureName(MODID + ":scrambled_weevil_egg")
                .setUnlocalizedName(MODID + ".scrambled_weevil_egg");
    }

    private static void registerItems() {
        if (FetherConfig.enableQuartzItems) {
            GameRegistry.registerItem(itemQuartzIngot, "quartz_ingot");
            GameRegistry.registerItem(itemQuartzHelmet, "quartz_helmet");
            GameRegistry.registerItem(itemQuartzChestplate, "quartz_chestplate");
            GameRegistry.registerItem(itemQuartzLeggings, "quartz_leggings");
            GameRegistry.registerItem(itemQuartzBoots, "quartz_boots");
            GameRegistry.registerItem(itemQuartzAxe, "quartz_axe");
            GameRegistry.registerItem(itemQuartzHoe, "quartz_hoe");
            GameRegistry.registerItem(itemQuartzPickaxe, "quartz_pickaxe");
            GameRegistry.registerItem(itemQuartzShovel, "quartz_shovel");
            GameRegistry.registerItem(itemQuartzSword, "quartz_sword");
        }
        GameRegistry.registerItem(itemIgnisFruit, "ignis_fruit");
        GameRegistry.registerItem(itemNetherBed, "nether_bed");
        GameRegistry.registerItem(itemBloodLeaf, "blood_leaf");
        GameRegistry.registerItem(itemFleshRoot, "flesh_root");
        GameRegistry.registerItem(itemMarrowBerry, "marrow_berry");
        GameRegistry.registerItem(itemBloodLeafSeeds, "blood_leaf_seeds");
        GameRegistry.registerItem(itemFleshRootSeeds, "flesh_root_seeds");
        GameRegistry.registerItem(itemMarrowBerrySeeds, "marrow_berry_seeds");
        GameRegistry.registerItem(itemGlowFlowerSeeds, "glow_flower_seeds");
        GameRegistry.registerItem(itemWeevil, "weevil");
        GameRegistry.registerItem(itemWeevilEgg, "weevil_egg");
        GameRegistry.registerItem(itemCookedWeevil, "cooked_weevil");
        GameRegistry.registerItem(itemScrambledWeevilEgg, "scrambled_weevil_egg");
    }

    private static void addODTags() {
        if (FetherConfig.enableQuartzItems) {
            OreDictionary.registerOre("ingotQuartz", itemQuartzIngot);
        }
        OreDictionary.registerOre("cropIgnisfruit", itemIgnisFruit);
        OreDictionary.registerOre("cropBloodleaf", itemBloodLeaf);
        OreDictionary.registerOre("cropFleshroot", itemFleshRoot);
        OreDictionary.registerOre("cropMarrowberry", itemMarrowBerry);
        OreDictionary.registerOre("listAllfruit", itemIgnisFruit);
        OreDictionary.registerOre("listAllfruit", itemMarrowBerry);
        OreDictionary.registerOre("listAllveggie", itemBloodLeaf);
        OreDictionary.registerOre("listAllgreenveggie", itemBloodLeaf);
        OreDictionary.registerOre("listAllmeatraw", itemFleshRoot);
        OreDictionary.registerOre("listAllseed", itemBloodLeafSeeds);
        OreDictionary.registerOre("listAllseed", itemFleshRootSeeds);
        OreDictionary.registerOre("listAllseed", itemMarrowBerrySeeds);
        OreDictionary.registerOre("listAllseed", itemGlowFlowerSeeds);
    }
}
