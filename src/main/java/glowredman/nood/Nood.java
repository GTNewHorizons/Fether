package glowredman.nood;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(acceptedMinecraftVersions = "1.7.10", modid = "nood", name = "Nood", version = Tags.VERSION)
public class Nood {
    
    public static final Logger LOGGER = LogManager.getLogger("nood");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // TODO blocks
        // TODO items
        NoodConfig.init(event.getModConfigurationDirectory());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // TODO compat
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // TODO recipe
        // TODO register world gen
    }

    @EventHandler
    public void onMissingMapping(FMLMissingMappingsEvent event) {
        // TODO make Nood drop-in
    }
}
