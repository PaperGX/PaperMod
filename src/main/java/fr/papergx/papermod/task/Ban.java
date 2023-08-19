package fr.papergx.papermod.task;

import fr.papergx.papermod.Main;
import fr.papergx.papermod.sql.RequestManager;
import fr.papergx.papermod.sql.enums.REQUEST_TYPE;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;


public class Ban {
    public Ban(Player player, OfflinePlayer target, Main main, String reason) {
        if(Bukkit.getPlayer(target.getName()).isOnline()) {
            Player onlineTarget = (Player) target;
            onlineTarget.kickPlayer("§e§lBannissement §7§l➛ §6Tu viens d'être bannis pour la raison: §e" + reason);
        }

        RequestManager.addData(REQUEST_TYPE.BAN, target, reason, player);

        Bukkit.broadcastMessage("§e[§2✔§e] Le joueur §6" + target.getName() + " §e vient d'être banni pour: §6" + reason);
    }
}
