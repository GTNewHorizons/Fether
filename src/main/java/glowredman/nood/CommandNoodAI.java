package glowredman.nood;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;

/*
 * NOTE: Although this "AI" is artificial, it is not intelligent. In fact it is really stupid. Also: no AI whatsoever
 * has been used in the creation of this thing (this is also true for the responses). All bugs and mistakes were proudly
 * introduced by me. - glowredman, January 2026
 */
public class CommandNoodAI extends CommandBase implements IResourceManagerReloadListener {

    private int numResponsesText;
    private int numResponsesMood;

    public CommandNoodAI() {
        ((IReloadableResourceManager) Minecraft.getMinecraft()
            .getResourceManager()).registerReloadListener(this);
    }

    @Override
    public void onResourceManagerReload(IResourceManager manager) {
        try {
            this.numResponsesText = Integer
                .parseInt(StatCollector.translateToLocal("commands.nood.noodai.response.text.num"));
        } catch (NumberFormatException e) {
            Nood.LOGGER.error("Failed to parse number of text responses", e);
            this.numResponsesText = 0;
        }
        try {
            this.numResponsesMood = Integer
                .parseInt(StatCollector.translateToLocal("commands.nood.noodai.response.mood.num"));
        } catch (NumberFormatException e) {
            Nood.LOGGER.error("Failed to parse number of mood responses", e);
            this.numResponsesMood = 0;
        }
    }

    @Override
    public String getCommandName() {
        return "noodai";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.nood.noodai.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            throw new CommandException("commands.nood.noodai.usage");
        }

        Random rng = new Random();

        if ("text".equals(args[0])) {
            if (args.length == 1) {
                throw new CommandException("commands.nood.noodai.failed.noprompt");
            }
            if (this.numResponsesText < 1) {
                throw new CommandException("commands.nood.noodai.failed.noresponses");
            }

            String prompt = String.join(" ", args)
                .substring(5);

            if (rng.nextFloat() >= NoodConfig.aiRandomness) {
                rng.setSeed(prompt.hashCode());
            }
            int selectedResponse = rng.nextInt(this.numResponsesText);
            sender.addChatMessage(new ChatComponentTranslation("commands.nood.noodai.prompt", prompt));
            sender.addChatMessage(
                new ChatComponentTranslation(
                    "commands.nood.noodai.response.text.prefix",
                    new ChatComponentTranslation("commands.nood.noodai.response.text." + selectedResponse)));
            return;
        }
        if (!"mood".equals(args[0])) {
            throw new CommandException("commands.nood.noodai.usage");
        }
        if (this.numResponsesMood < 1) {
            throw new CommandException("commands.nood.noodai.failed.noresponses");
        }
        int selectedResponse = rng.nextInt(this.numResponsesMood);
        sender.addChatMessage(
            new ChatComponentTranslation(
                "commands.nood.noodai.response.mood.prefix",
                new ChatComponentTranslation("commands.nood.noodai.response.mood." + selectedResponse)));
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length != 1) {
            return null;
        }
        return getListOfStringsMatchingLastWord(args, "text", "mood");
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
