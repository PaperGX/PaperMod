package fr.papergx.papermod;

import dev.jcsoftware.minecraft.gui.GUIAPI;
import fr.papergx.papermod.command.CommandStaff;
import fr.papergx.papermod.command.CommandUnban;
import fr.papergx.papermod.listener.LoginEvent;
import fr.papergx.papermod.listener.RightClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin {
    private static GUIAPI<Main> guiAPI;
    public HashMap<Player, ItemStack[]> listStaff;
    public HashMap<Player, ItemStack[]> staffInvetory;

    public static GUIAPI<Main> getGuiAPI() {
        return guiAPI;
    }

    @Override
    public void onEnable() {
        guiAPI = new GUIAPI<>(this);
        this.listStaff = new HashMap<>();
        this.staffInvetory = new HashMap<>();
        getCommand("staff").setExecutor(new CommandStaff(this));
        getCommand("unban").setExecutor(new CommandUnban(this));
        getServer().getPluginManager().registerEvents(new RightClickEvent(this), this);
        getServer().getPluginManager().registerEvents(new LoginEvent(this), this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public HashMap<Player, ItemStack[]> getListStaff() {
        return listStaff;
    }

    public void setListStaff(HashMap<Player, ItemStack[]> listStaff) {
        this.listStaff = listStaff;
    }

    public HashMap<Player, ItemStack[]> getStaffInvetory() {
        return staffInvetory;
    }
}
