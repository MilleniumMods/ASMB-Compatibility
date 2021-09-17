package com.milleniummods.asmbcompatibility.listeners;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import io.lumine.xikage.mythicmobs.drops.DropMetadata;
import io.lumine.xikage.mythicmobs.drops.DropTable;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class EventListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        try{
            // Mob DropTable drop
            ActiveMob activeMob = MythicMobs.inst().getMobManager().getMythicMobInstance(event.getEntity());
            DropMetadata dropMetadata = new DropMetadata(activeMob.getParent(), activeMob.getEntity().getTarget());
            AbstractLocation abstractLocation = activeMob.getLocation();
            MythicMob mythicMob = MythicMobs.inst().getMobManager().getMythicMob(activeMob.getMobType());
            try{
                mythicMob.getDropTable().generate(dropMetadata).drop(abstractLocation);
            }catch (Exception e){}
            try{
                // Add Entity Kill Statistic
                Player player = event.getEntity().getKiller();
                assert player != null;
                int oldKills = player.getStatistic(Statistic.KILL_ENTITY, event.getEntity().getType());
                player.setStatistic(Statistic.KILL_ENTITY, event.getEntity().getType(), oldKills+1);
            }catch (Exception e){}
        }catch (Exception e){}
    }
}
