package com.marisa.tp.data;

import java.util.HashMap;
import java.util.Map;

/**
 * 玩家信息管理
 */

public class PlayerDataManager {

    private static final Map<String, PlayerData> MAP = new HashMap<>(8);

    public static PlayerData get(String playerUUID) {
        return MAP.get(playerUUID);
    }

    public static void put(PlayerData data) {
        MAP.put(data.getPlayerUUID(), data);
    }

    public static void update(PlayerData data) {
        if (MAP.containsKey(data.getPlayerUUID())) {
            PlayerData playerData = MAP.get(data.getPlayerUUID());
            playerData.update(data);
        } else {
            MAP.put(data.getPlayerUUID(), data);
        }
    }

}
