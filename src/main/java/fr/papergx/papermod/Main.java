package fr.papergx.papermod;

import dev.jcsoftware.minecraft.gui.GUIAPI;
import fr.papergx.papermod.command.CommandStaff;
import fr.papergx.papermod.command.CommandUnban;
import fr.papergx.papermod.listener.LoginEvent;
import fr.papergx.papermod.listener.RightClickEvent;
import fr.papergx.papermod.sql.RequestManager;
import fr.papergx.papermod.sql.SqlDataManager;
import fr.papergx.papermod.sql.enums.REQUEST_TYPE;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {
    private static GUIAPI<Main> guiAPI;
    public HashMap<Player, ItemStack[]> listStaff;
    public HashMap<Player, ItemStack[]> staffInvetory;

    public static HashMap<String, List<Object>> bans;

    public static HashMap<String, List<Object>> getBans() {
        return bans;
    }

    public static GUIAPI<Main> getGuiAPI() {
        return guiAPI;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        setupDataBase();
        this.bans = (HashMap<String, List<Object>>) RequestManager.getTable(REQUEST_TYPE.BAN.getName());
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
        SqlDataManager.shutdown();

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

    private void setupDataBase() {
        SqlDataManager.getConnection();
        SqlDataManager.createTable();
    }

    public static void setBans(HashMap<String, List<Object>> bans) {
        Main.bans = bans;
    }
}
