package fr.papergx.papermod.gui;

import dev.jcsoftware.minecraft.gui.GUI;
import fr.papergx.papermod.Main;
import fr.papergx.papermod.task.Ban;
import fr.papergx.papermod.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BannissementGUI extends GUI<Main> {
    private Player target;
    private Main plugin;
    public BannissementGUI(Main plugin, Player target) {
        super(plugin);
        this.target = target;
        this.plugin = plugin;
        createInventory();
    }
    public void createInventory() {
        set(9, new ItemBuilder(Material.ENCHANTED_BOOK)
                .setName("§7§l➛ §e§lCheat Non-PVP")
                .setLore("§7Durée: 30j")
                .toItemStack(), (player, clickedItem) -> {
            return ButtonAction.CLOSE_GUI;
        });
        set(10, new ItemBuilder(Material.ENCHANTED_BOOK)
                .setName("§7§l➛ §e§lCheat PVP")
                .setLore("§7Durée: 60j")
                .toItemStack(), (player, clickedItem) -> {
            return ButtonAction.CLOSE_GUI;
        });
        set(12, new ItemBuilder(Material.ENCHANTED_BOOK)
                .setName("§7§l➛ §e§lSkin Incorrecte")
                .setLore("§7Durée: Définitif")
                .toItemStack(), (player, clickedItem) -> {
            new Ban(player, target, plugin, "Skin Incorrecte");
            return ButtonAction.CLOSE_GUI;
        });
        set(13, new ItemBuilder(Material.ENCHANTED_BOOK)
                .setName("§7§l➛ §e§lPseudo Incorrect")
                .setLore("§7Durée: Définitif")
                .toItemStack(), (player, clickedItem) -> {
            new Ban(player, target, plugin, "Pseudo Incorrecte");
            return ButtonAction.CLOSE_GUI;
        });
        set(15, new ItemBuilder(Material.ENCHANTED_BOOK)
                .setName("§7§l➛ §e§lMenace DDOS/DOX")
                .setLore("§7Durée: Définitif (BL)")
                .toItemStack(), (player, clickedItem) -> {
            new Ban(player, target, plugin, "DDOS/DOX");
            return ButtonAction.CLOSE_GUI;
        });
        set(16, new ItemBuilder(Material.ENCHANTED_BOOK)
                .setName("§7§l➛ §e§lDDOS/DOX")
                .setLore("§7Durée: Définitif (BL)")
                .toItemStack(), (player, clickedItem) -> {
            return ButtonAction.CLOSE_GUI;
        });
        set(17, new ItemBuilder(Material.ENCHANTED_BOOK)
                .setName("§7§l➛ §e§lMenace (Mort) / Propos Racite...")
                .setLore("§7Durée: 7j")
                .toItemStack(), (player, clickedItem) -> {
            new Ban(player, target, plugin, "Menace (Mort) / Propos Racite...");
            return ButtonAction.CLOSE_GUI;
        });

    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public String getTitle() {
        return "§7§l➛ §e§lBannissement";
    }

    // While false, the inventory will not be allowed to close
    @Override
    public boolean canClose(Player player) {
        return true;
    }
}
