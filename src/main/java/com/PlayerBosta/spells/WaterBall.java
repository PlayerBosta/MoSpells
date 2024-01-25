package com.PlayerBosta.spells;

import com.PlayerBosta.MoreSpells.MoreSpells;
import com.PlayerBosta.entity.spells.WaterBall.MagicWaterBall;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.AutoSpellConfig;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


import java.util.List;
import java.util.Optional;

@AutoSpellConfig
public class WaterBall extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(MoreSpells.MODID, "waterball");
    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", getRadius(spellLevel, caster))

        );
    }
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(25)
            .build();

    public WaterBall(){
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 40;
        this.baseManaCost = 60;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.empty();
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.empty();
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        Vec3 origin = entity.getEyePosition();

        MagicWaterBall waterball = new MagicWaterBall(world, entity);

        waterball.setDamage(getDamage(spellLevel, entity));
        waterball.setExplosionRadius(getRadius(spellLevel, entity));

        waterball.setPos(origin.add(entity.getForward()).subtract(0, waterball.getBbHeight() / 2, 0));
        waterball.shoot(entity.getLookAngle());

        world.addFreshEntity(waterball);
        super.onCast(world, spellLevel, entity, playerMagicData);
    }
    public float getDamage(int spellLevel, LivingEntity caster) {
        return 10 * getSpellPower(spellLevel, caster);
    }
    public int getRadius(int spellLevel, LivingEntity caster) {
        return (int) getSpellPower(spellLevel, caster);
    }
}


