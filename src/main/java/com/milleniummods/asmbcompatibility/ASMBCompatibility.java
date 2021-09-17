package com.milleniummods.asmbcompatibility;

import com.milleniummods.asmbcompatibility.listeners.EventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ASMBCompatibility extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }
}
