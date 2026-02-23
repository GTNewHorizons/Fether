package glowredman.fether;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.ClientCommandHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.Type;
import glowredman.fether.compat.EFRCompat;
import glowredman.fether.compat.MFRCompat;
import glowredman.fether.compat.ThaumcraftCompat;
import glowredman.fether.worldgen.WorldGenNetherFlowers;
import glowredman.fether.worldgen.WorldGenNetherTree;
import glowredman.fether.worldgen.WorldGeneratorNether;

@Mod(acceptedMinecraftVersions = "1.7.10", modid = Fether.MODID, name = "Fether", version = Tags.VERSION)
public class Fether {

    public static final String MODID = "fether";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final CreativeTabs TAB_NOOD = new CreativeTabs(MODID) {

        @Override
        public Item getTabIconItem() {
            return Items.nether_wart;
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        FetherConfig.init(event.getModConfigurationDirectory());
        FetherBlocks.init();
        FetherItems.init();
        if (event.getSide()
            .isClient()) {
            FetherAI.init();
            ClientCommandHandler.instance.registerCommand(new CommandFetherAI());
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (Loader.isModLoaded("Thaumcraft")) {
            ThaumcraftCompat.init();
        }
        if (Loader.isModLoaded("MineFactoryReloaded")) {
            MFRCompat.init();
        }
        if (Loader.isModLoaded("etfuturum")) {
            EFRCompat.init();
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        FetherRecipes.init();
        if (FetherConfig.gardenRarity > 0) {
            GameRegistry.registerWorldGenerator(
                new WorldGeneratorNether(
                    FetherConfig.gardenRarity,
                    new WorldGenNetherFlowers(false, FetherBlocks.blockNetherGarden)),
                0);
        }
        if (FetherConfig.glowFlowerRarity > 0) {
            GameRegistry.registerWorldGenerator(
                new WorldGeneratorNether(
                    FetherConfig.glowFlowerRarity,
                    new WorldGenNetherFlowers(false, FetherBlocks.blockGlowFlower)),
                0);
        }
        if (FetherConfig.treeRarity > 0) {
            GameRegistry.registerWorldGenerator(
                new WorldGeneratorNether(FetherConfig.treeRarity, new WorldGenNetherTree(false)),
                0);
        }
    }

    @EventHandler
    public void onMissingMapping(FMLMissingMappingsEvent event) {
        for (MissingMapping mapping : event.getAll()) {
            if (!mapping.name.startsWith("harvestthenether:")) {
                continue;
            }
            String name = mapping.name.substring(17); // removes "harvestthenether:"
            if (mapping.type == Type.BLOCK) {
                switch (name) {
                    case "netherLog" -> mapping.remap(FetherBlocks.blockNetherLog);
                    case "netherLeaves" -> mapping.remap(FetherBlocks.blockNetherLeaves);
                    case "netherPlanks" -> mapping.remap(FetherBlocks.blockNetherPlanks);
                    case "netherSapling" -> mapping.remap(FetherBlocks.blockNetherSapling);
                    case "ignisFruit" -> mapping.remap(FetherBlocks.blockIgnisFruit);
                    case "netherBed" -> mapping.remap(FetherBlocks.blockNetherBed);
                    case "netherGarden" -> mapping.remap(FetherBlocks.blockNetherGarden);
                    case "bloodleafCrop" -> mapping.remap(FetherBlocks.blockBloodLeafCrop);
                    case "fleshrootCrop" -> mapping.remap(FetherBlocks.blockFleshRootCrop);
                    case "marrowberryCrop" -> mapping.remap(FetherBlocks.blockMarrowBerryCrop);
                    case "glowFlower" -> mapping.remap(FetherBlocks.blockGlowFlower);
                    case "glowflowerCrop" -> mapping.remap(FetherBlocks.blockGlowFlowerCrop);
                    default -> {
                        LOGGER.warn("Could not remap unknown block from Pam's Harvest the Nether: {}", mapping.name);
                        continue;
                    }
                }
                continue;
            }
            switch (name) {
                case "quartzingotItem" -> mapping.remap(FetherItems.itemQuartzIngot);
                case "quartzhelmItem" -> mapping.remap(FetherItems.itemQuartzHelmet);
                case "quartzchestItem" -> mapping.remap(FetherItems.itemQuartzChestplate);
                case "quartzleggingsItem" -> mapping.remap(FetherItems.itemQuartzLeggings);
                case "quartzbootsItem" -> mapping.remap(FetherItems.itemQuartzBoots);
                case "Quartz Axe" -> mapping.remap(FetherItems.itemQuartzAxe);
                case "Quartz Hoe" -> mapping.remap(FetherItems.itemQuartzHoe);
                case "Quartz Pickaxe" -> mapping.remap(FetherItems.itemQuartzPickaxe);
                case "Quartz Shovel" -> mapping.remap(FetherItems.itemQuartzShovel);
                case "Quartz Sword" -> mapping.remap(FetherItems.itemQuartzSword);
                case "ignisfruitItem" -> mapping.remap(FetherItems.itemIgnisFruit);
                case "netherbedItem" -> mapping.remap(FetherItems.itemNetherBed);
                case "bloodleafItem" -> mapping.remap(FetherItems.itemBloodLeaf);
                case "fleshrootItem" -> mapping.remap(FetherItems.itemFleshRoot);
                case "marrowberryItem" -> mapping.remap(FetherItems.itemMarrowBerry);
                case "bloodleafseedItem" -> mapping.remap(FetherItems.itemBloodLeafSeeds);
                case "fleshrootseedItem" -> mapping.remap(FetherItems.itemFleshRootSeeds);
                case "marrowberryseedItem" -> mapping.remap(FetherItems.itemMarrowBerrySeeds);
                case "glowflowerseedItem" -> mapping.remap(FetherItems.itemGlowFlowerSeeds);
                default -> {
                    LOGGER.warn("Could not remap unknown item from Pam's Harvest the Nether: {}", mapping.name);
                    continue;
                }
            }
        }
    }
}
