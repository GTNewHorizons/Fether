package glowredman.fether.compat;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.item.ItemStack;

import forestry.api.circuits.ChipsetManager;
import forestry.api.circuits.ICircuit;
import forestry.api.core.ForestryAPI;
import forestry.api.farming.Farmables;
import forestry.api.farming.IFarmable;
import forestry.api.recipes.RecipeManagers;
import forestry.core.fluids.Fluids;
import forestry.core.items.EnumElectronTube;
import forestry.farming.circuits.CircuitFarmLogic;
import forestry.farming.logic.FarmableBasicFruit;
import forestry.farming.logic.FarmableGenericCrop;
import forestry.farming.logic.FarmableGenericSapling;
import forestry.farming.logic.FarmableReference;
import forestry.plugins.PluginCore;
import forestry.plugins.PluginManager;
import glowredman.fether.FetherBlocks;
import glowredman.fether.FetherItems;

public class ForestryCompat {

    public static final String KEY_FARM_INFERNAL_ARBOREAL = "farmInfernalArborealManaged";
    public static ICircuit farmInfernalArborealManaged;

    public static void preInit() {
        Farmables.farmables.put(KEY_FARM_INFERNAL_ARBOREAL, new ArrayList<>());
    }

    public static void init() {
        int fruitJuiceAmount = ForestryAPI.activeMode.getIntegerSetting("squeezer.liquid.apple");
        int fruitJuiceAmountBy25 = Math.max(fruitJuiceAmount / 25, 1);
        int fruitJuiceAmountBy2 = Math.max(fruitJuiceAmount / 2, 1);
        int seedAmount = ForestryAPI.activeMode.getIntegerSetting("squeezer.liquid.seed");

        RecipeManagers.squeezerManager.addRecipe(
            10,
            new ItemStack[] { new ItemStack(FetherItems.itemIgnisFruit) },
            Fluids.JUICE.getFluid(fruitJuiceAmountBy2));
        RecipeManagers.squeezerManager.addRecipe(
            10,
            new ItemStack[] { new ItemStack(FetherItems.itemMarrowBerry) },
            Fluids.JUICE.getFluid(fruitJuiceAmountBy25));
        RecipeManagers.squeezerManager.addRecipe(
            10,
            new ItemStack[] { new ItemStack(FetherBlocks.blockGlowFlower) },
            Fluids.JUICE.getFluid(fruitJuiceAmountBy2));
        RecipeManagers.squeezerManager.addRecipe(
            10,
            new ItemStack[] { new ItemStack(FetherItems.itemBloodLeaf) },
            Fluids.JUICE.getFluid(fruitJuiceAmountBy2));
        RecipeManagers.squeezerManager.addRecipe(
            10,
            new ItemStack[] { new ItemStack(FetherItems.itemFleshRoot) },
            Fluids.JUICE.getFluid(fruitJuiceAmountBy2));
        RecipeManagers.squeezerManager.addRecipe(
            10,
            new ItemStack[] { new ItemStack(FetherItems.itemBloodLeafSeeds) },
            Fluids.SEEDOIL.getFluid(seedAmount));
        RecipeManagers.squeezerManager.addRecipe(
            10,
            new ItemStack[] { new ItemStack(FetherItems.itemFleshRootSeeds) },
            Fluids.SEEDOIL.getFluid(seedAmount));
        RecipeManagers.squeezerManager.addRecipe(
            10,
            new ItemStack[] { new ItemStack(FetherItems.itemGlowFlowerSeeds) },
            Fluids.SEEDOIL.getFluid(seedAmount));
        RecipeManagers.squeezerManager.addRecipe(
            10,
            new ItemStack[] { new ItemStack(FetherItems.itemMarrowBerrySeeds) },
            Fluids.SEEDOIL.getFluid(seedAmount));

        if (!PluginManager.Module.FARMING.isEnabled()) {
            return;
        }

        farmInfernalArborealManaged = new CircuitFarmLogic("managedInfernalArboreal", FarmLogicInfernalArboreal.class);

        ChipsetManager.solderManager.addRecipe(
            ChipsetManager.circuitRegistry.getLayout("forestry.farms.managed"),
            PluginCore.items.tubes.get(EnumElectronTube.OBSIDIAN, 1),
            farmInfernalArborealManaged);

        Collection<IFarmable> farmablesInfernalArboreal = Farmables.farmables.get(KEY_FARM_INFERNAL_ARBOREAL);
        Collection<IFarmable> farmablesInfernal = Farmables.farmables.get(FarmableReference.Infernal.get());
        Collection<IFarmable> farmablesOrchard = Farmables.farmables.get(FarmableReference.Orchard.get());

        farmablesInfernalArboreal.add(new FarmableGenericSapling(FetherBlocks.blockNetherSapling, -1));

        // spotless:off
        farmablesInfernal.add(new FarmableGenericCrop(new ItemStack(FetherItems.itemBloodLeafSeeds), FetherBlocks.blockBloodLeafCrop, 7));
        farmablesInfernal.add(new FarmableGenericCrop(new ItemStack(FetherItems.itemFleshRootSeeds), FetherBlocks.blockFleshRootCrop, 7));
        farmablesInfernal.add(new FarmableGenericCrop(new ItemStack(FetherItems.itemGlowFlowerSeeds), FetherBlocks.blockGlowFlowerCrop, 7));
        farmablesInfernal.add(new FarmableGenericCrop(new ItemStack(FetherItems.itemMarrowBerrySeeds), FetherBlocks.blockMarrowBerryCrop, 7));
        // spotless:on

        farmablesOrchard.add(new FarmableBasicFruit(FetherBlocks.blockIgnisFruit, 2));
    }
}
