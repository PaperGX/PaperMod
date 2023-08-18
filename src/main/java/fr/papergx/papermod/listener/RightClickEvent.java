package fr.papergx.papermod.listener;

import fr.papergx.papermod.Main;
import fr.papergx.papermod.gui.InformationGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class RightClickEvent implements Listener {
    Main plugin;

    public RightClickEvent(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void PlayerInteractEntityEvent(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();

        if(event.getRightClicked() == null) return;
        if (!(event.getRightClicked() instanceof Player)) return;
        Player target = (Player) event.getRightClicked();

        ItemStack stack = player.getInventory().getItemInHand();
        if (stack == null) return;

        switch (stack.getItemMeta().getDisplayName()) {
            case "§7§l➛ §e§lInventaire du joueur":
                player.openInventory(target.getInventory());
                break;
            case "§7§l➛ §e§lInformations/Sanctions joueurs":
                Main.getGuiAPI().openGUI(player, new InformationGUI(plugin, target));
                break;
        }
    }
}
