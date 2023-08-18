package fr.papergx.papermod.gui;

import dev.jcsoftware.minecraft.gui.GUI;
import fr.papergx.papermod.Main;
import fr.papergx.papermod.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class InformationGUI extends GUI<Main> {
    private Player target;
    private Main plugin;
    public InformationGUI(Main plugin, Player target) {
        super(plugin);
        this.target = target;
        this.plugin = plugin;
        createInventory();
    }

    public void createInventory() {
        set(3, new ItemBuilder(Material.BOOK)
                .setName("§7§l➛ §e§lBannissement")
                .setLore("§7Menu des sanctions (bannissement)")
                .toItemStack(), (player, clickedItem) -> {
            plugin.getGuiAPI().openGUI(player, new BannissementGUI(plugin, target));
            return ButtonAction.CANCEL;

        });
        set(5, new ItemBuilder(Material.PAPER)
                .setName("§7§l➛ §e§lMute")
                .setLore("§7Menu des sactions (mute)")
                .toItemStack(), (whoClicked, clickedItem) -> {
            return ButtonAction.CLOSE_GUI;
        });
        set(20, new ItemBuilder(Material.BOOKSHELF)
                .setName("§7§l➛ §e§lHistorique Bannissement")
                .setLore("§7Clique pour voir les bannissement du joueur")
                .toItemStack(), (player, clickedItem) -> {
            return ButtonAction.CLOSE_GUI;

        });
        set(22, new ItemBuilder(Material.BOOK_AND_QUILL)
                .setName("§7§l➛ §e§lHistorique Report")
                .setLore("§7Clique pour voir tout les reports contre le joueur")
                .toItemStack(), (player, clickedItem) -> {
            return ButtonAction.CLOSE_GUI;
        });
        set(24, new ItemBuilder(Material.BOOK)
                .setName("§7§l➛ §e§lHistorique Mute")
                .setLore("§7Clique pour voir tout les mutes du joueurs")
                .toItemStack(), (player, clickedItem) -> {
            return ButtonAction.CLOSE_GUI;
        });
    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public String getTitle() {
        return "§7§l➛ §e§lInformations";
    }

    // While false, the inventory will not be allowed to close
    @Override
    public boolean canClose(Player player) {
        return true;
    }
}
