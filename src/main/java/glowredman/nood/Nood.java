package glowredman.nood;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.ClientCommandHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry.Type;

@Mod(acceptedMinecraftVersions = "1.7.10", modid = Nood.MODID, name = "Nood", version = Tags.VERSION)
public class Nood {

    public static final String MODID = "nood";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final CreativeTabs TAB_NOOD = new CreativeTabs(MODID) {

        @Override
        public Item getTabIconItem() {
            return Items.nether_wart;
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NoodConfig.init(event.getModConfigurationDirectory());
        NoodBlocks.init();
        NoodItems.init();
        if (event.getSide()
            .isClient()) {
            ClientCommandHandler.instance.registerCommand(new CommandNoodAI());
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // TODO recipe
        // TODO register world gen
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
                    case "netherLog" -> mapping.remap(NoodBlocks.blockNetherLog);
                    case "netherLeaves" -> mapping.remap(NoodBlocks.blockNetherLeaves);
                    case "netherPlanks" -> mapping.remap(NoodBlocks.blockNetherPlanks);
                    case "netherSapling" -> mapping.remap(NoodBlocks.blockNetherSapling);
                    case "ignisFruit" -> mapping.remap(NoodBlocks.blockIgnisFruit);
                    case "netherBed" -> mapping.remap(NoodBlocks.blockNetherBed);
                    case "netherGarden" -> mapping.remap(NoodBlocks.blockNetherGarden);
                    case "bloodleafCrop" -> mapping.remap(NoodBlocks.blockBloodLeafCrop);
                    case "fleshrootCrop" -> mapping.remap(NoodBlocks.blockFleshRootCrop);
                    case "marrowberryCrop" -> mapping.remap(NoodBlocks.blockMarrowBerryCrop);
                    case "glowFlower" -> mapping.remap(NoodBlocks.blockGlowFlower);
                    case "glowflowerCrop" -> mapping.remap(NoodBlocks.blockGlowFlowerCrop);
                    default -> {
                        LOGGER.warn("Could not remap unknown block from Pam's Harvest the Nether: {}", mapping.name);
                        continue;
                    }
                }
                continue;
            }
            switch (name) {
                case "quartzingotItem" -> mapping.remap(NoodItems.itemQuartzIngot);
                case "quartzhelmItem" -> mapping.remap(NoodItems.itemQuartzHelmet);
                case "quartzchestItem" -> mapping.remap(NoodItems.itemQuartzChestplate);
                case "quartzleggingsItem" -> mapping.remap(NoodItems.itemQuartzLeggings);
                case "quartzbootsItem" -> mapping.remap(NoodItems.itemQuartzBoots);
                case "Quartz Axe" -> mapping.remap(NoodItems.itemQuartzAxe);
                case "Quartz Hoe" -> mapping.remap(NoodItems.itemQuartzHoe);
                case "Quartz Pickaxe" -> mapping.remap(NoodItems.itemQuartzPickaxe);
                case "Quartz Shovel" -> mapping.remap(NoodItems.itemQuartzShovel);
                case "Quartz Sword" -> mapping.remap(NoodItems.itemQuartzSword);
                case "ignisfruitItem" -> mapping.remap(NoodItems.itemIgnisFruit);
                case "netherbedItem" -> mapping.remap(NoodItems.itemNetherBed);
                case "bloodleafItem" -> mapping.remap(NoodItems.itemBloodLeaf);
                case "fleshrootItem" -> mapping.remap(NoodItems.itemFleshRoot);
                case "marrowberryItem" -> mapping.remap(NoodItems.itemMarrowBerry);
                case "bloodleafseedItem" -> mapping.remap(NoodItems.itemBloodLeafSeeds);
                case "fleshrootseedItem" -> mapping.remap(NoodItems.itemFleshRootSeeds);
                case "marrowberryseedItem" -> mapping.remap(NoodItems.itemMarrowBerrySeeds);
                case "glowflowerseedItem" -> mapping.remap(NoodItems.itemGlowFlowerSeeds);
                default -> {
                    LOGGER.warn("Could not remap unknown item from Pam's Harvest the Nether: {}", mapping.name);
                    continue;
                }
            }
        }
    }
}
