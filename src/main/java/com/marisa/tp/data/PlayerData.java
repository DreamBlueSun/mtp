package com.marisa.tp.data;

import com.marisa.tp.MTp;
import com.marisa.tp.net.pack.FriendsDataPack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 玩家信息
 */

public class PlayerData implements Serializable {

    @Serial
    private static final long serialVersionUID = -5809782578272943999L;

    private final String playerUUID;
    private List<String> friendsUUID;

    public String getPlayerUUID() {
        return playerUUID;
    }

    public List<String> getFriendsUUID() {
        if (friendsUUID == null) {
            friendsUUID = new ArrayList<>();
        }
        return friendsUUID;
    }

    public PlayerData(String playerUUID) {
        this.playerUUID = playerUUID;
        this.friendsUUID = new ArrayList<>();
    }

    public PlayerData(FriendsDataPack pack) {
        this.playerUUID = pack.getPlayerUUID();
        this.friendsUUID = pack.getFriendsUUID();
    }

    public void update(PlayerData data) {
        if (data != null && this.playerUUID.equals(data.playerUUID)) {
            if (data.getFriendsUUID() != null && data.getFriendsUUID().size() > 0) {
                this.friendsUUID = data.getFriendsUUID();
            }
        }
    }

    /**
     * 获取默认
     *
     * @param playerUUID 玩家UUID
     */
    private static PlayerData defaultData(String playerUUID) {
        return new PlayerData(playerUUID);
    }

    //序列化
    public static void serialize(String playerDataPath, String playerUUID) {
        ObjectOutputStream stream = null;
        try {
            String filePath = file(playerDataPath, playerUUID);
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
            stream = new ObjectOutputStream(new FileOutputStream(filePath));
            PlayerData data = PlayerDataManager.get(playerUUID);
            if (data != null) {
                stream.writeObject(data);
            }
        } catch (IOException e) {
            MTp.LOG.error("PlayerData 序列化异常：" + playerUUID);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    MTp.LOG.error("PlayerData 序列化流关闭异常：" + playerUUID);
                }
            }
        }
    }

    //反序列化
    public static PlayerData deserialize(String playerDataPath, String playerUUID) {
        PlayerData data = PlayerDataManager.get(playerUUID);
        if (data != null) {
            return data;
        }
        try {
            String file = file(playerDataPath, playerUUID);
            if (new File(file).exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                data = (PlayerData) ois.readObject();
            }
        } catch (Exception e) {
            MTp.LOG.error("PlayerData 反序列化异常：" + playerUUID, e);
        }
        if (data == null) {
            data = defaultData(playerUUID);
        }
        PlayerDataManager.put(data);
        return data;
    }

    /**
     * 获取Story玩家数据保存目录
     *
     * @param playerDataPath MC玩家数据保存目录
     * @param playerUUID     玩家UUID
     */
    private static String file(String playerDataPath, String playerUUID) {
        return playerDataPath + "\\mtpfriendsdata\\" + playerUUID + ".dat";
    }

}
