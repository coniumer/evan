package net.seinsturg.efac.mixin;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.seinsturg.efac.client.EvansHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class HudMixin {
    private static final EvansHud HUD = new EvansHud();

    @Inject(method = "render", at = @At("RETURN"))
    private void tick(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HUD.draw(guiGraphics);
    }
}
