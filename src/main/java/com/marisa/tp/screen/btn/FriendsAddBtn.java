package com.marisa.tp.screen.btn;

import com.marisa.tp.data.PlayerData;
import com.marisa.tp.data.PlayerDataManager;
import com.marisa.tp.net.Networking;
import com.marisa.tp.net.pack.FriendsDataPack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;

import java.util.List;

/**
 * 添加好友按钮
 */

public class FriendsAddBtn extends Button {

    public FriendsAddBtn(int x, int y, int width, int height, Component component, String targetUUID) {
        super(x, y, width, height, component, (btn) -> {
            if (canAdd(targetUUID)) {
                LocalPlayer player = Minecraft.getInstance().player;
                if (player != null) {
                    PlayerData playerData = PlayerDataManager.get(player.getStringUUID());
                    if (!playerData.getFriendsUUID().contains(targetUUID)) {
                        playerData.getFriendsUUID().add(targetUUID);
                    }
                    btn.active = false;
                }
                Networking.FRIENDS_DATA.sendToServer(new FriendsDataPack("online.players.friends.add", targetUUID));
            }
        });
        this.active = canAdd(targetUUID);
    }

    private static boolean canAdd(String targetUUID) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            if (player.getStringUUID().equals(targetUUID)) {
                return false;
            } else {
                List<String> friendsUUID = PlayerDataManager.get(player.getStringUUID()).getFriendsUUID();
                return friendsUUID == null || !friendsUUID.contains(targetUUID);
            }
        }
        return true;
    }
}
