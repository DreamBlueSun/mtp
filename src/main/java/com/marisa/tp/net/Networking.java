package com.marisa.tp.net;

import com.marisa.tp.MTp;
import com.marisa.tp.net.pack.FriendsDataPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * 数据通信
 */

public class Networking {

    private static int ID = 0;
    public static int nextID() {
        return ID++;
    }

    public static SimpleChannel PLAYER_DATA;
    public static SimpleChannel FRIENDS_DATA;

    public static void registerFriendsData() {
        FRIENDS_DATA = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(MTp.MOD_ID + ":networking_friends_data"),
                () -> "1.0", (s) -> true, (s) -> true);
        FRIENDS_DATA.registerMessage(nextID(), FriendsDataPack.class, FriendsDataPack::toBytes, FriendsDataPack::new, FriendsDataPack::handler);
    }

}
