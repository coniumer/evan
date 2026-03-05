package net.seinsturg.efac.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.data.EvansData;

import java.util.Random;

public class ChargeDisplay {
    private static final ResourceLocation CHARGE_EMPTY = EFAC.res("clumb/charge_empty");
    private static final ResourceLocation CHARGE_FILLABLE = EFAC.res("clumb/charge_fillable");
    private static final ResourceLocation CHARGE_CLUMBY = EFAC.res("clumb/charge_clumby");
    private static final ResourceLocation CHARGE_ALBEDIZED = EFAC.res("clumb/charge_albedized");
    private static final ResourceLocation CHARGE_CITRINIZED = EFAC.res("clumb/charge_citrinized");
    private static final ResourceLocation CHARGE_RUBEDIZED = EFAC.res("clumb/charge_rubedized");

    private final Minecraft mc;
    private final int SLOT_AMOUNT = 10;

    private final long seed = System.currentTimeMillis();
    Random random = new Random(seed);
    int[] mults = setMults(random, SLOT_AMOUNT * 2);

    public ChargeDisplay(Minecraft mc) {
        this.mc = mc;
    }

    public boolean shouldDraw() {
        assert mc.player != null;
        return !mc.player.isCreative() && !mc.player.isSpectator();
    }

    public void draw(GuiGraphics guiGraphics, int x, int y) {
        if (shouldDraw()) {
            drawSurvivalChargeDisplay(guiGraphics, x, y);
        }
    }

    public void drawSurvivalChargeDisplay(GuiGraphics guiGraphics, int x, int y) {
        for (int i = 0; i < SLOT_AMOUNT; i++) {
            int j = i + SLOT_AMOUNT;
            drawSlotOfTypeInPos(guiGraphics, CHARGE_EMPTY, x, y, i, j);
        }

        for (int i = 0; i < SLOT_AMOUNT; i++) {
            int j = i + SLOT_AMOUNT;
            assert mc.player != null;
            if (mc.player.getData(EvansData.MAX_CHARGES) > i) {
                drawSlotOfTypeInPos(guiGraphics, CHARGE_FILLABLE, x, y, i, j);
            } else {
                break;
            }
        }

        for (int i = 0; i < SLOT_AMOUNT; i++) {
            int j = i + SLOT_AMOUNT;
            assert mc.player != null;
            if (mc.player.getData(EvansData.CHARGES) > i) {
                drawSlotOfTypeInPos(guiGraphics, CHARGE_CLUMBY, x, y, i, j);
            } else {
                break;
            }
        }

        for (int i = 0; i < SLOT_AMOUNT; i++) {
            int j = i + SLOT_AMOUNT;
            assert mc.player != null;
            if (mc.player.getData(EvansData.CHARGES) > i + 10) {
                drawSlotOfTypeInPos(guiGraphics, CHARGE_ALBEDIZED, x, y, i, j);
            } else {
                break;
            }
        }

        for (int i = 0; i < SLOT_AMOUNT; i++) {
            int j = i + SLOT_AMOUNT;
            assert mc.player != null;
            if (mc.player.getData(EvansData.CHARGES) > i + 20) {
                drawSlotOfTypeInPos(guiGraphics, CHARGE_CITRINIZED, x, y, i, j);
            } else {
                break;
            }
        }

        for (int i = 0; i < SLOT_AMOUNT; i++) {
            int j = i + SLOT_AMOUNT;
            assert mc.player != null;
            if (mc.player.getData(EvansData.CHARGES) > i + 30) {
                drawSlotOfTypeInPos(guiGraphics, CHARGE_RUBEDIZED, x, y, i, j);
            } else {
                break;
            }
        }
    }

    private void drawSlotOfTypeInPos (GuiGraphics guiGraphics, ResourceLocation tex, int x, int y, int i, int j) {
        guiGraphics.blitSprite(tex,(x / 2) + 192 - (i * mults[i]), y - 16 - (i * mults[j]), 0, 10, 10);
    }

    private int[] setMults(Random random, int length) {
        int[] mults = new int[length];
        for (int i = 0; i < length; i++) {
            int randomDigit = (Math.abs(random.nextInt() % 10) + 1);
            mults[i] = randomDigit;
        }
        return mults;
    }
}
