package glowredman.fether.compat;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import forestry.api.farming.Farmables;
import forestry.api.farming.IFarmHousing;
import forestry.api.farming.IFarmable;
import forestry.farming.logic.FarmLogicArboreal;
import glowredman.fether.FetherBlocks;

public class FarmLogicInfernalArboreal extends FarmLogicArboreal {

    public FarmLogicInfernalArboreal(IFarmHousing housing, ItemStack resource, ItemStack ground,
        Iterable<IFarmable> germlings) {
        super(housing, resource, ground, germlings);
    }

    public FarmLogicInfernalArboreal(IFarmHousing housing) {
        super(
            housing,
            new ItemStack(Blocks.soul_sand),
            new ItemStack(Blocks.soul_sand),
            Farmables.farmables.get(ForestryCompat.KEY_FARM_INFERNAL_ARBOREAL));
    }

    @Override
    public String getName() {
        return StatCollector.translateToLocal("for.logic.managedInfernalArboreal");
    }

    @Override
    public IIcon getIcon() {
        return FetherBlocks.blockNetherSapling.getBlockTextureFromSide(0);
    }

    @Override
    public int getWaterConsumption(float hydrationModifier) {
        return 0;
    }
}
