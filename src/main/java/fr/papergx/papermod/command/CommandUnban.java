package fr.papergx.papermod.command;

import fr.papergx.papermod.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

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
        File file = new File(plugin.getDataFolder(), "data/Ban.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        if(!configuration.contains(args[0].toLowerCase())) {
            player.sendMessage("§c[✘] Le joueurs n'est pas banni !");
            return false;
        }
        configuration.set(args[0], null);
        player.sendMessage("§e[§2✔§e] Tu viens de débannir le joueur");
        try {
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
