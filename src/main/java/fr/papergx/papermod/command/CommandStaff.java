package fr.papergx.papermod.command;

import fr.papergx.papermod.Main;
import fr.papergx.papermod.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;

public class CommandStaff implements CommandExecutor {
    private final Main main;

    public CommandStaff(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        HashMap<Player, ItemStack[]> listStaff = main.getListStaff();
        HashMap<Player, ItemStack[]> staffInventory = main.getStaffInvetory();

        if (!(player.hasPermission("staff.mode"))) {
            player.sendMessage("§c[✘] Vous n'avez pas la permission !");
            return false;
        }
        PlayerInventory playerInventory = player.getInventory();
        if (listStaff.containsKey(player)) {
            player.sendMessage("§e[§c✘§e] Mode staff: §cOff");
            playerInventory.clear();
            player.getInventory().setContents(listStaff.get(player));
            player.getInventory().setArmorContents(staffInventory.get(player));
            listStaff.remove(player);
        } else {
            player.sendMessage("§e[§2✔§e] Mode staff: §2On");
            listStaff.put(player, playerInventory.getContents());
            staffInventory.put(player, playerInventory.getArmorContents());
            playerInventory.clear();
            playerInventory.setArmorContents(null);
            ItemStack invseeItem = new ItemBuilder(Material.CHEST, 1)
                    .setName("§7§l➛ §e§lInventaire du joueur")
                    .setLore("§7Clique droite pour ouvrir l'inventaire du joueur")
                    .toItemStack();
            ItemStack knockbackItem = new ItemBuilder(Material.WOOD_SWORD, 1)
                    .setUnbreakable(true)
                    .addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES)
                    .setName("§7§l➛ §e§lTesteur knockback [§25§e§l]")
                    .setLore(" ")
                    .addLoreLine("§7Tape un joueur pour tester son knockback")
                    .addLoreLine("§7Tu peux changer sa puissance (Clique gauche)")
                    .addEnchant(Enchantment.KNOCKBACK, 5)
                    .toItemStack();
            ItemStack playerItem = new ItemBuilder(Material.WATCH, 1)
                    .setName("§7§l➛ §e§lInformations/Sanctions joueurs")
                    .setLore("§7Clique gauche sur un joueur pour le sanctionner ou voir plus d'informations")
                    .toItemStack();
            ItemStack randomItem = new ItemBuilder(Material.COMPASS, 1)
                    .setName("§7§l➛ §e§lTéléportation aléatoire")
                    .setLore("§7Clique gauche pour téléporter aléatoirement sur un joueur")
                    .toItemStack();
            ItemStack vanishItem = new ItemBuilder(Material.INK_SACK, 1, 8)
                    .setName("§7§l➛ §e§lVanish [§cOff§e]")
                    .setLore("§7Clique droit pour te vanish/unvanish")
                    .toItemStack();
            ItemStack freezeItem = new ItemBuilder(Material.PACKED_ICE, 1)
                    .setName("§7§l➛ §e§lFreeze")
                    .setLore("§7Clique droit sur un joueur pour le freeze/unfreeze")
                    .toItemStack();
            playerInventory.setItem(0, invseeItem);
            playerInventory.setItem(1, knockbackItem);
            playerInventory.setItem(3, playerItem);
            playerInventory.setItem(5, randomItem);
            playerInventory.setItem(7, vanishItem);
            playerInventory.setItem(8, freezeItem);

        }
        main.setListStaff(listStaff);
        return false;
    }
}
