package com.PlayerBosta.Registry;


import com.PlayerBosta.entity.spells.WaterBall.MagicWaterBall;
import com.PlayerBosta.MoreSpells.MoreSpells;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistro {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MoreSpells.MODID);

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
    public static final RegistryObject<EntityType<MagicWaterBall>> WATER_BALL =
            ENTITIES.register("waterball", () -> EntityType.Builder.<MagicWaterBall>of(MagicWaterBall::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(4)
                    .build(new ResourceLocation(MoreSpells.MODID, "waterball").toString()));
}
