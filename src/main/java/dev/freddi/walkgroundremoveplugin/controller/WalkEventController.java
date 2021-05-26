package dev.freddi.walkgroundremoveplugin.controller;

import dev.freddi.walkgroundremoveplugin.controller.FeetBlockRemover;
import dev.freddi.walkgroundremoveplugin.model.Const;
import dev.freddi.walkgroundremoveplugin.model.Flags;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

import static org.bukkit.Bukkit.getLogger;


public class WalkEventController implements Listener {

    // Playerごとに前にどこのBlockを消したかをメモしておく。これを基に判断しないと無限に落ちる・・・
    private HashMap<String, Location> handledPlayerPosition = new HashMap();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(Flags.COMMAND_ENABLED) {
            Player movedPlayer = e.getPlayer();
            getLogger().info( Const.LogHeaderDebug + movedPlayer.getDisplayName() + " の移動を検知");

            // 初Block消しの場合のときは必ず消す。それか座標でハンドルされるべきと判断された場合
            if(!handledPlayerPosition.containsKey(e.getPlayer().getDisplayName()) || shouldBeHandledCurrentPosition(movedPlayer)) {
                handledPlayerPosition.put(movedPlayer.getDisplayName(), movedPlayer.getLocation());
                FeetBlockRemover.handle(movedPlayer);
                getLogger().info(Const.LogHeaderDebug + movedPlayer.getDisplayName() + " の足元のBlockを削除");
            } else {
                getLogger().info(Const.LogHeaderDebug + movedPlayer.getDisplayName() + " 対象外");
            }
        }
    }

    public void reset() {
        handledPlayerPosition = new HashMap<>();
        getLogger().info(Const.LogHeaderDebug + "位置情報のReset");
    }

    private boolean shouldBeHandledCurrentPosition(Player player) {
        Location previousLocation = handledPlayerPosition.get(player.getDisplayName());

        // 念のため
        if(previousLocation == null) { return true; }

        // Blockを削除後に落ちた場所は移動しない限りBlockを消さない
        int previousX = previousLocation.getBlockX();
        int previousY = previousLocation.getBlockY();
        int previousZ = previousLocation.getBlockZ();

        int currentX = player.getLocation().getBlockX();
        int currentY = player.getLocation().getBlockY();
        int currentZ = player.getLocation().getBlockZ();

        if(previousX == currentX && previousY - 1 == currentY && previousZ == currentZ) {
            return false;
        }

        // すべてパスしたら許可
        return true;
    }
}
