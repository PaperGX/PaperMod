package fr.papergx.papermod.task;

import fr.papergx.papermod.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Ban {
    public Ban(Player player, Player target, Main main, String reason) {
        target.kickPlayer("§e§lBannissement §7§l➛ §6Tu viens d'être bannis pour la raison: §e" + reason);
        File file = new File(main.getDataFolder(), "data/Ban.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set(target.getName().toLowerCase(), reason);
        Bukkit.broadcastMessage("§e[§2✔§e] Le joueur §6" + target.getName() + " §e vient d'être banni pour: §6" + reason);
        try {
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
