package com.marisa.tp.keyband;

import com.marisa.tp.MTp;
import com.marisa.tp.net.Networking;
import com.marisa.tp.net.pack.FriendsDataPack;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;


/**
 * 按键绑定
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class KeyBoardInput {

    private static final String KEY_BIND_TITLE = MTp.MOD_ID;

    public static final KeyMapping ONLINE_FRIENDS_KEY = new KeyMapping("mtp.keyBind.screen.friend",
            KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_P, KEY_BIND_TITLE);

    @SubscribeEvent
    public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        if (ONLINE_FRIENDS_KEY.consumeClick()) {
            Networking.FRIENDS_DATA.sendToServer(new FriendsDataPack("online.friends.screen.open"));
        }
    }
}
