package com.PlayerBosta.MoreSpells;

import com.PlayerBosta.Registry.EntityRegistro;
import com.PlayerBosta.Registry.ItemsRegistro;
import com.PlayerBosta.Registry.SpellRegistro;
import com.PlayerBosta.setup.modsetup;
import com.mojang.logging.LogUtils;
import io.redspace.ironsspellbooks.api.magic.MagicHelper;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.config.ClientConfigs;
import io.redspace.ironsspellbooks.config.ServerConfigs;
import io.redspace.ironsspellbooks.registries.BlockRegistry;
import io.redspace.ironsspellbooks.registries.MenuRegistry;
import io.redspace.ironsspellbooks.registries.OverlayRegistry;
import io.redspace.ironsspellbooks.setup.ModSetup;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.jline.utils.Log;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.stream.Collectors;

import static io.redspace.ironsspellbooks.compat.Curios.registerCurioSlot;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(MoreSpells.MODID)
public class MoreSpells {
    public static final String MODID = "mospells";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static MagicManager MAGIC_MANAGER;


    public MoreSpells(){
        modsetup.setup();

        MAGIC_MANAGER = new MagicManager();
        MagicHelper.MAGIC_MANAGER = MAGIC_MANAGER;

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(modsetup::init);

        modEventBus.addListener(OverlayRegistry::onRegisterOverlays);

        EntityRegistro.register(modEventBus);
        ItemsRegistro.register(modEventBus);
        SpellRegistro.register(modEventBus);

        modEventBus.addListener(this::ClientSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfigs.SPEC, String.format("%s-client.toml", MoreSpells.MODID));
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfigs.SPEC, String.format("%s-server.toml", MoreSpells.MODID));

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SuppressWarnings("removal")
    private void ClientSetup(final FMLClientSetupEvent e) {
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.INSCRIPTION_TABLE_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.ARMOR_PILE_BLOCK.get(), RenderType.translucent());
    }


    private void enqueueIMC(final InterModEnqueueEvent event) {
        // Some example code to dispatch IMC to another mod
//        InterModComms.sendTo("examplemod", "helloworld", () -> {
//            LOGGER.info("Hello world from the MDK");
//            return "Hello world";
//        });
        registerCurioSlot("ring", 2, false, null);
        registerCurioSlot("necklace", 1, false, null);
    }
    private void processIMC(final InterModProcessEvent event) {
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.messageSupplier().get()).
                collect(Collectors.toList()));
    }


   /* public MoreSpells() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        SpellRegistro.register(bus);
        EntityRegistro.register(bus);
        ItemsRegistro.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }*/
    public static ResourceLocation id(@NotNull String path) {
        return new ResourceLocation(MoreSpells.MODID, path);
    }
}
