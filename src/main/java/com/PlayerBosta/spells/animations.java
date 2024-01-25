package com.PlayerBosta.spells;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.builder.ILoopType;

public class animations {
    public static ResourceLocation ANIMATION_RESOURCE = new ResourceLocation(IronsSpellbooks.MODID, "animation");

    public static final AnimationHolder ANIMATION_LONG_CAST = new AnimationHolder("long_cast", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    public static final AnimationHolder ANIMATION_LONG_CAST_FINISH = new AnimationHolder("long_cast_finish", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
}
