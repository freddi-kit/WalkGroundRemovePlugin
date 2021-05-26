package dev.freddi.walkgroundremoveplugin.controller;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class FeetBlockRemover {
    static public void handle(Player player) {
        Location location = player.getLocation();

        // プレイヤーの足元にあるBlockをAIRに変えるだけ
        location.setY(location.getBlockY() -1);
        location.getBlock().setType(Material.AIR);
    }
}
