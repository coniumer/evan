package net.seinsturg.efac.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class EvansHud extends Screen {
    private final ChargeDisplay chargeDisplay;

    public EvansHud() {
        super(Component.empty());
        this.minecraft = Minecraft.getInstance();
        chargeDisplay = new ChargeDisplay(minecraft);
    }

    public void draw(GuiGraphics guiGraphics) {
        chargeDisplay.draw(guiGraphics, determineX(), determineY());
    }

    private int determineX() {
        int wScreen = 0;
        if (minecraft != null) wScreen = minecraft.getWindow().getGuiScaledWidth();
        return wScreen;
    }

    private int determineY() {
        int hScreen = 0;
        if (minecraft != null) hScreen = minecraft.getWindow().getGuiScaledHeight();
        return hScreen;
    }
}
