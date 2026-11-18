package glowredman.fether;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import glowredman.fether.worldgen.TreeTemplate;
import glowredman.fether.worldgen.WorldGenTemplateTree;

public class CommandFetherTree extends CommandBase {

    private final File exportDir;

    public CommandFetherTree(File exportDir) {
        this.exportDir = exportDir;
        TreeTemplate.devExportDir = exportDir;
    }

    @Override
    public String getCommandName() {
        return "fethertree";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/fethertree export <name> <x> <y> <z> [radius] [height] | place <set> <x> <y> <z> [force] [eye] | reload";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            throw new WrongUsageException(this.getCommandUsage(sender));
        }
        World world = sender.getEntityWorld();
        switch (args[0]) {
            case "export" -> export(sender, world, args);
            case "place" -> place(sender, world, args);
            case "reload" -> {
                TreeTemplate.clearCache();
                msg(sender, "Tree template cache cleared.");
            }
            default -> throw new WrongUsageException(this.getCommandUsage(sender));
        }
    }

    private void export(ICommandSender sender, World world, String[] args) {
        if (args.length < 5) {
            throw new WrongUsageException(this.getCommandUsage(sender));
        }
        String name = args[1];
        int ox = coord(sender, sender.getPlayerCoordinates().posX, args[2]);
        int oy = coord(sender, sender.getPlayerCoordinates().posY, args[3]);
        int oz = coord(sender, sender.getPlayerCoordinates().posZ, args[4]);
        int r = args.length > 5 ? parseIntBounded(sender, args[5], 1, 32) : 6;
        int h = args.length > 6 ? parseIntBounded(sender, args[6], 1, 64) : 16;

        List<TreeTemplate.Entry> entries = new ArrayList<>();
        for (int dy = -4; dy <= h; dy++) {
            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    int x = ox + dx, y = oy + dy, z = oz + dz;
                    Block b = world.getBlock(x, y, z);
                    if (b.isAir(world, x, y, z)) {
                        continue;
                    }
                    if (dy < 0 && !b.isWood(world, x, y, z)) {
                        continue;
                    }
                    if (b == Blocks.netherrack || b == Blocks.soul_sand || b == Blocks.bedrock) {
                        continue;
                    }
                    entries.add(new TreeTemplate.Entry(dx, dy, dz, b, world.getBlockMetadata(x, y, z)));
                }
            }
        }
        if (entries.isEmpty()) {
            msg(sender, "Nothing found around " + ox + " " + oy + " " + oz + ".");
            return;
        }
        File file = new File(this.exportDir, name + ".txt");
        try {
            new TreeTemplate(name, entries).write(file);
        } catch (IOException e) {
            Fether.LOGGER.error("Tree export failed", e);
            msg(sender, "Export failed: " + e.getMessage());
            return;
        }
        TreeTemplate.clearCache();
        msg(sender, "Exported " + entries.size() + " blocks to " + file.getAbsolutePath());
    }

    private void place(ICommandSender sender, World world, String[] args) {
        if (args.length < 5) {
            throw new WrongUsageException(this.getCommandUsage(sender));
        }
        int x = coord(sender, sender.getPlayerCoordinates().posX, args[2]);
        int y = coord(sender, sender.getPlayerCoordinates().posY, args[3]);
        int z = coord(sender, sender.getPlayerCoordinates().posZ, args[4]);
        WorldGenTemplateTree gen = args[1].startsWith("witchfire") ? WorldGenTemplateTree.witchfire(true, args[1])
            : new WorldGenTemplateTree(true, args[1], FetherBlocks.blockNetherSapling);
        if (args.length > 6 && "eye".equals(args[6]) || args.length > 5 && "eye".equals(args[5])) {
            gen.eyeChance = 1f;
        }
        gen.checks = !(args.length > 5 && "force".equals(args[5]));
        boolean ok = gen.generate(world, new Random(), x, y, z);
        msg(
            sender,
            ok ? "Grew '" + args[1] + "'."
                : "Could not grow '" + args[1] + "' (no templates, bad soil or not enough space; try 'force').");
    }

    private static int coord(ICommandSender sender, int base, String arg) {
        return (int) Math.floor(func_110666_a(sender, base, arg));
    }

    private static void msg(ICommandSender sender, String text) {
        sender.addChatMessage(new ChatComponentText(text));
    }
}
