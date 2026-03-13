package net.seinsturg.efac.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.seinsturg.efac.client.EvansHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class HudOverhaulMixin {
    private static final EvansHud HUD = new EvansHud();

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V"))
    public void hudOverhaul(Gui instance, GuiGraphics guiGraphics, DeltaTracker deltaTracker, Operation<Void> original) {
        HUD.draw(guiGraphics, deltaTracker);
    }
}
