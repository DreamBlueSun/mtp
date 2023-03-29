package com.marisa.tp;

import com.marisa.tp.data.SaveEventHandler;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod("mtp")
public class MTp {
    
    public static final String MOD_ID = "mtp";
    
    public static final Logger LOG = LogUtils.getLogger();

    public MTp() {
        MinecraftForge.EVENT_BUS.register(new SaveEventHandler());
        MinecraftForge.EVENT_BUS.register(this);
    }
}
