package com.PlayerBosta.setup;

import io.redspace.ironsspellbooks.capabilities.magic.MagicEvents;
import io.redspace.ironsspellbooks.compat.CompatHandler;
import io.redspace.ironsspellbooks.player.CommonPlayerEvents;
import io.redspace.ironsspellbooks.setup.Messages;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class modsetup {
    public static void setup(){
        IEventBus bus = MinecraftForge.EVENT_BUS;

        bus.addGenericListener(Entity.class, MagicEvents::onAttachCapabilitiesPlayer);
        bus.addListener(MagicEvents::onRegisterCapabilities);
        bus.addListener(MagicEvents::onWorldTick);
        bus.addListener(CommonPlayerEvents::onPlayerRightClickItem);
        bus.addListener(CommonPlayerEvents::onUseItemStop);
    }

    public static void init(FMLCommonSetupEvent event){
       // Messages.register();

        CompatHandler.init();

    }
}
