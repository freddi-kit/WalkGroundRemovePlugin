package dev.freddi.walkgroundremoveplugin;

import dev.freddi.walkgroundremoveplugin.controller.WalkEventController;
import dev.freddi.walkgroundremoveplugin.model.Commands;
import dev.freddi.walkgroundremoveplugin.model.Const;
import dev.freddi.walkgroundremoveplugin.model.Flags;
import dev.freddi.walkgroundremoveplugin.model.Texts;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class WalkGroundRemovePlugin extends JavaPlugin {

    private WalkEventController walkEventController = new WalkEventController();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(walkEventController, this);
        getLogger().info(Const.LogHeaderDebug + "プラグイン起動成功");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(cmd.getName().equalsIgnoreCase(Commands.root)) { // 起動
            if (args.length != 1) {
                sender.sendMessage(ChatColor.RED + Const.LogHeader + Texts.Log.invalid_format);
                return false;
            }
            if (args[0].equalsIgnoreCase(Commands.Subs.start)) {
                if (!Flags.COMMAND_ENABLED) {
                    Flags.COMMAND_ENABLED = true;
                    sender.sendMessage(ChatColor.GREEN + Const.LogHeader + Texts.Log.start);
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + Const.LogHeader + Texts.Log.start_already);
                }
            } else if (args[0].equalsIgnoreCase(Commands.Subs.end))  { // 終了
                if (Flags.COMMAND_ENABLED) {
                    Flags.COMMAND_ENABLED = false;
                    walkEventController.reset();
                    sender.sendMessage(ChatColor.GREEN + Const.LogHeader + Texts.Log.end);
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + Const.LogHeader + Texts.Log.end_already);
                }
            } else if (args[0].equalsIgnoreCase(Commands.Subs.help))  { // Help
                sender.sendMessage(ChatColor.BLUE + "\n" +  Const.LogHeader + Texts.Log.help);
            } else {
                sender.sendMessage(ChatColor.RED + Const.LogHeader + Texts.Log.invalid_format);
            }
        }

        return false;
    }
    @Override
    public void onDisable() {
    }
}
