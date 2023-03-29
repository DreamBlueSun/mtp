package com.marisa.tp.pojo;

import net.minecraft.server.level.ServerPlayer;

/**
 * 列表玩家信息VO
 */

public class PlayerInfo {

    private final String uuid;
    private final String name;

    public PlayerInfo(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public static PlayerInfo of(ServerPlayer player) {
        return new PlayerInfo(player.getStringUUID(), player.getDisplayName().getString());
    }
}
