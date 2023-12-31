package fr.papergx.papermod.command;

import fr.papergx.papermod.Main;
import fr.papergx.papermod.sql.RequestManager;
import fr.papergx.papermod.sql.enums.REQUEST_TYPE;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CommandUnban implements CommandExecutor {
    private Main plugin;
    public CommandUnban(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(!player.hasPermission("unban.use")) {
            player.sendMessage("§c[✘] Tu n'as pas la permission de faire cela !");
            return false;
        }
        if(!(args.length == 1)) {
            player.sendMessage("§c[✘] Erreur: /unban (pseudo)");
            return false;
        }
        HashMap<String, List<Object>> listBans = Main.getBans();
        if (!listBans.containsKey(args[0])) {
            player.sendMessage("§c[✘] Le joueur n'est pas banni !");
            return false;
        }
        RequestManager.removeBan(REQUEST_TYPE.BAN, args[0]);
        return false;
    }
}
