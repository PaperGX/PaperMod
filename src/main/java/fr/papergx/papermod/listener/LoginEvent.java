package fr.papergx.papermod.listener;

import fr.papergx.papermod.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class LoginEvent implements Listener {
    private Main plugin;
    public LoginEvent(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        HashMap<String, List<Object>> getBans = Main.getBans();
        if(getBans.containsKey(player.getName())) {
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            event.setKickMessage("§e§lBannissement §7§l➛ §e§lTu es bannis du serveur de façon définitive pour la raison: §6§l" + getBans.get(player.getName()).get(0));
        }
    }
}
