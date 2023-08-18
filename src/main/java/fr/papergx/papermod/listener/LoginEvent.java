package fr.papergx.papermod.listener;

import fr.papergx.papermod.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.File;

public class LoginEvent implements Listener {
    private Main plugin;
    public LoginEvent(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void ee(PlayerLoginEvent event) {
        File file = new File(plugin.getDataFolder(), "data/Ban.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        Player player = event.getPlayer();
        if(configuration.get(player.getName().toLowerCase()) != null) {
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            event.setKickMessage("§e§lBannissement §7§l➛ §6Tu es bannis pour la raison: §e" + configuration.get(player.getName().toLowerCase()));
        }
    }
}
