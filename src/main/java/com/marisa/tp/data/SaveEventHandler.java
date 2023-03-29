package com.marisa.tp.data;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 监听事件处理器
 */

public class SaveEventHandler {

    @SubscribeEvent
    public void SaveToPlayerFile(PlayerEvent.SaveToFile event) {
        //玩家保存数据时，MTp的玩家相关数据也保存
        PlayerData.serialize(event.getPlayerDirectory().getPath(), event.getPlayerUUID());
    }

    @SubscribeEvent
    public void LoadFromPlayerFile(PlayerEvent.LoadFromFile event) {
        //玩家加载数据时，MTp的玩家相关数据也加载
        PlayerData.deserialize(event.getPlayerDirectory().getPath(), event.getPlayerUUID());
    }

}
