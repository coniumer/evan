package net.seinsturg.efac.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.Util;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.util.ClumbHelper;

import javax.annotation.Nullable;
import java.util.List;

public class EvansHud extends Screen {
    private static final ResourceLocation CROSSHAIR_SPRITE = EFAC.res("hud/crosshair");
    private static final ResourceLocation HOTBAR_SPRITE = EFAC.res("hud/hotbar");
    private static final ResourceLocation HUD_BASE_SPRITE = EFAC.res("hud/base");
    private static final ResourceLocation HUD_ATTACK_INDICATOR_EMPTY_SPRITE = EFAC.res("hud/attack_indicator_back");
    private static final ResourceLocation HUD_ATTACK_INDICATOR_FULL_SPRITE = EFAC.res("hud/attack_indicator_full");
    private static final ResourceLocation HUD_EXPERIENCE_EMPTY_SPRITE = EFAC.res("hud/experience_empty");
    private static final ResourceLocation HUD_EXPERIENCE_FULL_SPRITE = EFAC.res("hud/experience_full");
    private static final ResourceLocation HUD_HEALTH_FULL_SPRITE = EFAC.res("hud/health_full");
    private static final ResourceLocation HUD_HEALTH_HALF_SPRITE = EFAC.res("hud/health_half");
    private static final ResourceLocation HUD_HEALTH_EMPTY_SPRITE = EFAC.res("hud/health_empty");
    private static final ResourceLocation HUD_HEALTH_LOCKED_SPRITE = EFAC.res("hud/health_locked");
    private static final ResourceLocation HUD_CHARGE_CLUMBY_SPRITE = EFAC.res("hud/charge_clumby");
    private static final ResourceLocation HUD_CHARGE_ALBY_SPRITE = EFAC.res("hud/charge_alby");
    private static final ResourceLocation HUD_CHARGE_CITRY_SPRITE = EFAC.res("hud/charge_citry");
    private static final ResourceLocation HUD_CHARGE_RUBIED_SPRITE = EFAC.res("hud/charge_rubied");
    private static final ResourceLocation HUD_CHARGE_EMPTY_SPRITE = EFAC.res("hud/charge_empty");
    private static final ResourceLocation HUD_CHARGE_ALBY_CAPABLE_SPRITE = EFAC.res("hud/charge_alby_capable");
    private static final ResourceLocation HUD_CHARGE_CITRY_CAPABLE_SPRITE = EFAC.res("hud/charge_citry_capable");
    private static final ResourceLocation HUD_CHARGE_RUBIED_CAPABLE_SPRITE = EFAC.res("hud/charge_rubied_capable");
    private static final ResourceLocation HUD_CHARGE_LOCKED_SPRITE = EFAC.res("hud/charge_locked");
    private static final ResourceLocation HOTBAR_OFFHAND_SPRITE = EFAC.res("hud/hotbar_offhand");
    private static final ResourceLocation HOTBAR_SELECTION_SPRITE = EFAC.res("hud/hotbar_selection");

    private static final ResourceLocation BLOOD_0 = EFAC.res("hud/blood/blood_overlay_0");
    private static final ResourceLocation BLOOD_1 = EFAC.res("hud/blood/blood_overlay_1");
    private static final ResourceLocation BLOOD_2 = EFAC.res("hud/blood/blood_overlay_2");
    private static final ResourceLocation BLOOD_3 = EFAC.res("hud/blood/blood_overlay_3");
    private static final ResourceLocation BLOOD_4 = EFAC.res("hud/blood/blood_overlay_4");
    private static final ResourceLocation BLOOD_5 = EFAC.res("hud/blood/blood_overlay_5");
    private static final ResourceLocation BLOOD_6 = EFAC.res("hud/blood/blood_overlay_6");
    private static final ResourceLocation BLOOD_7 = EFAC.res("hud/blood/blood_overlay_7");
    private static final ResourceLocation BLOOD_8 = EFAC.res("hud/blood/blood_overlay_8");
    private static final ResourceLocation BLOOD_9 = EFAC.res("hud/blood/blood_overlay_9");
    private static final List<ResourceLocation> BLOOD_SPLATTERS = List.of(BLOOD_0, BLOOD_1, BLOOD_2, BLOOD_3, BLOOD_4, BLOOD_5, BLOOD_6, BLOOD_7, BLOOD_8, BLOOD_9);

    private int lastHealth;
    private long splatterTime = 0;
    private int MAX_SPLATTER_TIME = 200;
    private ResourceLocation splatterTex = BLOOD_0;

    public EvansHud() {
        super(Component.empty());
        this.minecraft = Minecraft.getInstance();
        this.lastHealth = 20;
    }

    public void draw(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        drawCrosshair(guiGraphics);
        if (this.minecraft.gameMode.getPlayerMode() != GameType.SPECTATOR) {
            drawOverlays(guiGraphics);
            drawBase(guiGraphics);
            drawHotbar(guiGraphics, deltaTracker);
            drawHealth(guiGraphics);
            drawExperience(guiGraphics);
            drawCharges(guiGraphics);
            drawStats(guiGraphics);
        }
    }

    private void drawOverlays(GuiGraphics guiGraphics) {
        Player player = this.getCameraPlayer();
        RenderSystem.enableBlend();

        int health = Mth.ceil(player.getHealth());

        if (health < this.lastHealth) {
            this.splatterTex = getSplatter(player);
            this.splatterTime = MAX_SPLATTER_TIME;
        } else {
            this.splatterTime = Mth.clamp(this.splatterTime - 1, 0, MAX_SPLATTER_TIME);
            System.out.println("splatterTime: " + this.splatterTime);
        }

        this.lastHealth = health;

        guiGraphics.setColor(1f, 1f, 1f, (float) this.splatterTime / MAX_SPLATTER_TIME);
        if (splatterTime > 0) {
            guiGraphics.blitSprite(this.splatterTex, 0, 0, 0, guiGraphics.guiWidth(), guiGraphics.guiHeight());
        }
        guiGraphics.setColor(1f, 1f, 1f, 1f);

        RenderSystem.disableBlend();
    }

    private ResourceLocation getSplatter(Player player) {
        int splatter = player.level().random.nextInt() % 10;
        return BLOOD_SPLATTERS.get(Mth.clamp(splatter, 0, 9));
    }

    private void drawStats(GuiGraphics guiGraphics) {
        Player player = this.getCameraPlayer();
        String def = "DEF: " + player.getArmorValue();
        float color = (player.getArmorValue() < 10) ? 1f : 0.2f;
        guiGraphics.setColor(1f, color, color, 1f);
        guiGraphics.drawString(this.minecraft.font, def, guiGraphics.guiWidth() - 240, guiGraphics.guiHeight() - 18, 9090900, true);
        guiGraphics.setColor(1f, 1f, 1f, 1f);
    }

    private void drawCharges(GuiGraphics guiGraphics) {
        Player player = this.getCameraPlayer();
        for (int i = 0; i < 10; i++) {
            ResourceLocation slotTex = getChargeSlotSpriteToDraw(i + 1, player);
            guiGraphics.blitSprite(slotTex, guiGraphics.guiWidth() - 104 - (i * 10), guiGraphics.guiHeight() - 19, 10, 10);

            if (ClumbHelper.getCharge(player) >= i + 1) {
                ResourceLocation chargeTex = getChargeSpriteToDraw(i, player);
                guiGraphics.blitSprite(chargeTex, guiGraphics.guiWidth() - 104 - (i * 10), guiGraphics.guiHeight() - 19, 10, 10);
            }
        }
    }

    private ResourceLocation getChargeSpriteToDraw(int slot, Player player) {
        int charges = ClumbHelper.getCharge(player);
        ResourceLocation tex = HUD_CHARGE_CLUMBY_SPRITE;

        if (charges <= 10) {
            return tex;
        } else if (charges <= 20) {
            return (charges >= slot + 11) ? HUD_CHARGE_ALBY_SPRITE : HUD_CHARGE_CLUMBY_SPRITE;
        } else if (charges <= 30) {
            return (charges >= slot + 21) ? HUD_CHARGE_CITRY_SPRITE : HUD_CHARGE_ALBY_SPRITE;
        } else if (charges <= 40) {
            return (charges >= slot + 31) ? HUD_CHARGE_RUBIED_SPRITE : HUD_CHARGE_CITRY_SPRITE;
        }

        return tex;
    }

    private ResourceLocation getChargeSlotSpriteToDraw(int slot, Player player) {
        int maxCharges = ClumbHelper.getMaxCharge(player);
        ResourceLocation tex = HUD_CHARGE_LOCKED_SPRITE;

        if (maxCharges < slot) {
            return tex;
        } else if (maxCharges <= 10) {
            return HUD_CHARGE_EMPTY_SPRITE;
        } else if (maxCharges <= 20) {
            return (maxCharges >= slot + 10) ? HUD_CHARGE_ALBY_CAPABLE_SPRITE : HUD_CHARGE_EMPTY_SPRITE;
        } else if (maxCharges <= 30) {
            return (maxCharges >= slot + 20) ? HUD_CHARGE_CITRY_CAPABLE_SPRITE : HUD_CHARGE_ALBY_CAPABLE_SPRITE;
        } else if (maxCharges <= 40) {
            return (maxCharges >= slot + 30) ? HUD_CHARGE_RUBIED_CAPABLE_SPRITE : HUD_CHARGE_CITRY_CAPABLE_SPRITE;
        }

        return tex;
    }

    private void drawExperience(GuiGraphics guiGraphics) {
        int nextLevelXp = this.minecraft.player.getXpNeededForNextLevel();
        int level = this.minecraft.player.experienceLevel;

        float red = 0;
        float green = 0;
        float blue = 0;
        if (minecraft.player.experienceLevel > 100) {
            red = 1f;
            green = 0.3f;
            blue = 0.1f;
        } else {
            for (int i = 0; i < 100; i++) {
                red = 0.5f + ((float) level / 200);
                green = 0.3f + ((100 - (float) level) / 300);
                blue = 0.1f + ((100 - (float) level) / 400);
            }
        }
        guiGraphics.setColor(red, green, blue, 1);

        if (nextLevelXp > 0) {
            int k = (int)(this.minecraft.player.experienceProgress * 202.0F);
            RenderSystem.enableBlend();
            guiGraphics.blitSprite(HUD_EXPERIENCE_EMPTY_SPRITE, guiGraphics.guiWidth() - 294, guiGraphics.guiHeight() - 8, 200, 3);
            if (k > 0) {
                guiGraphics.blitSprite(HUD_EXPERIENCE_FULL_SPRITE, guiGraphics.guiWidth() - 294, guiGraphics.guiHeight() - 8, 200, 3);
                guiGraphics.blitSprite(HUD_EXPERIENCE_EMPTY_SPRITE, 200, 3, 0, 0, guiGraphics.guiWidth() - 294, guiGraphics.guiHeight() - 8, 200 - k, 3);

            }
            RenderSystem.disableBlend();
        }
        String lvText = "LV: " + level;
        guiGraphics.drawString(this.minecraft.font, lvText, guiGraphics.guiWidth() - 294, guiGraphics.guiHeight() - 18, 9999999, true);

        guiGraphics.setColor(1,1,1,1);
    }

    private void drawHealth(GuiGraphics guiGraphics) {
        Player player = this.getCameraPlayer();
        for (int i = 0; i < 20; i++) {
            if (canFillSlot(player, i)) {
                drawRightSlot(guiGraphics, player, i);
            } else {
                guiGraphics.blitSprite(HUD_HEALTH_LOCKED_SPRITE, guiGraphics.guiWidth() - 104 - (i * 10), guiGraphics.guiHeight() - 5, 10, 5);
            }
        }
    }

    private void drawRightSlot(GuiGraphics guiGraphics, Player player, float slot) {
        float health = player.getHealth();
        if ((health / 2) > slot) {
            if (slot > (health / 2) - 1) {
                ResourceLocation rightSprite = (health % 2 == 0) ? HUD_HEALTH_FULL_SPRITE : HUD_HEALTH_HALF_SPRITE;
                guiGraphics.blitSprite(rightSprite, guiGraphics.guiWidth() - 104 - ((int) slot * 10), guiGraphics.guiHeight() - 5, 10, 5);
            } else {
                guiGraphics.blitSprite(HUD_HEALTH_FULL_SPRITE, guiGraphics.guiWidth() - 104 - ((int) slot * 10), guiGraphics.guiHeight() - 5, 10, 5);
            }
        } else {
            guiGraphics.blitSprite(HUD_HEALTH_EMPTY_SPRITE, guiGraphics.guiWidth() - 104 - ((int) slot * 10), guiGraphics.guiHeight() - 5, 10, 5);
        }
    }

    private boolean canFillSlot(Player player, float slot) {
        return (player.getMaxHealth() / 2) > slot;
    }

    private void drawBase(GuiGraphics guiGraphics) {
        Player player = this.getCameraPlayer();
        if (player != null) {
            float progress = player.getAttackStrengthScale(0.0F);
            int scaledProgress = (int) (progress * 66);
            guiGraphics.blitSprite(HUD_ATTACK_INDICATOR_FULL_SPRITE, guiGraphics.guiWidth() - 344, guiGraphics.guiHeight() - 66, 322, 66);
            guiGraphics.blitSprite(HUD_ATTACK_INDICATOR_EMPTY_SPRITE, 322, 66, 0, 0, guiGraphics.guiWidth() - 344, guiGraphics.guiHeight() - 66, 322,  scaledProgress * -1 + 66);
            guiGraphics.blitSprite(HUD_BASE_SPRITE, guiGraphics.guiWidth() - 344, guiGraphics.guiHeight() - 66, 322, 66);
            InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, guiGraphics.guiWidth() - 64, guiGraphics.guiHeight() - 48, guiGraphics.guiWidth() - 32, guiGraphics.guiHeight() + 30, 30, 0, guiGraphics.guiWidth() - 78, guiGraphics.guiHeight(), player);
        }
    }

    private void drawHotbar(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        this.drawItemHotbar(guiGraphics, deltaTracker);
    }

    private void drawItemHotbar(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Player player = this.getCameraPlayer();
        if (player != null) {
            ItemStack itemstack = player.getOffhandItem();

            RenderSystem.enableBlend();
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0.0F, 0.0F, -90.0F);

            guiGraphics.blitSprite(HOTBAR_SPRITE, guiGraphics.guiWidth() - 44,  guiGraphics.guiHeight() - 34 - 182, 44, 182);
            guiGraphics.blitSprite(HOTBAR_OFFHAND_SPRITE, guiGraphics.guiWidth() - 22, guiGraphics.guiHeight() - 22, 22, 22);

            int l = 1;

            for (int i1 = 0; i1 < 9; i1++) {
                int j1 = guiGraphics.guiWidth() - 20;
                int k1 = guiGraphics.guiHeight() - 52 - i1 * 20 - 1;
                this.renderSlot(guiGraphics, j1, k1, deltaTracker, player, player.getInventory().items.get(i1), l++);
            }

            if (!itemstack.isEmpty()) {
                this.renderSlot(guiGraphics, guiGraphics.guiWidth() - 20, guiGraphics.guiHeight() - 20, deltaTracker, player, itemstack, l++);
            }

            guiGraphics.pose().translate(0.0F, 0.0F, -90.0F);
            renderSelectedSlot(guiGraphics, guiGraphics.guiWidth() - 28, guiGraphics.guiHeight() - 56 - player.getInventory().selected * 20);

            RenderSystem.disableBlend();
        }
    }

    private void renderSelectedSlot(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blitSprite(HOTBAR_SELECTION_SPRITE, x, y, 22, 22);
    }

    private void renderSlot(GuiGraphics guiGraphics, int x, int y, DeltaTracker deltaTracker, Player player, ItemStack stack, int seed) {
        if (!stack.isEmpty()) {
            float f = (float)stack.getPopTime() - deltaTracker.getGameTimeDeltaPartialTick(false);
            if (f > 0.0F) {
                float f1 = 1.0F + f / 5.0F;
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate((float)(x + 8), (float)(y + 12), 0.0F);
                guiGraphics.pose().scale(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
                guiGraphics.pose().translate((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
            }

            guiGraphics.renderItem(player, stack, x, y, seed);
            if (f > 0.0F) {
                guiGraphics.pose().popPose();
            }

            guiGraphics.renderItemDecorations(this.minecraft.font, stack, x, y);
        }
    }

    @Nullable
    private Player getCameraPlayer() {
        return this.minecraft.getCameraEntity() instanceof Player player ? player : null;
    }

    public void drawCrosshair(GuiGraphics guiGraphics) {
        assert this.minecraft != null;
        Options options = this.minecraft.options;
        if (options.getCameraType().isFirstPerson()) {
            if (this.minecraft.gameMode.getPlayerMode() != GameType.SPECTATOR || this.canRenderCrosshairForSpectator(this.minecraft.hitResult)) {
                guiGraphics.blitSprite(CROSSHAIR_SPRITE, (guiGraphics.guiWidth() - 15) / 2, (guiGraphics.guiHeight() - 15) / 2, 15, 15);
            }
        }
    }

    private boolean canRenderCrosshairForSpectator(@Nullable HitResult rayTrace) {
        if (rayTrace == null) {
            return false;
        } else if (rayTrace.getType() == HitResult.Type.ENTITY) {
            return ((EntityHitResult)rayTrace).getEntity() instanceof MenuProvider;
        } else if (rayTrace.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult)rayTrace).getBlockPos();
            Level level = this.minecraft.level;
            return level.getBlockState(blockpos).getMenuProvider(level, blockpos) != null;
        } else {
            return false;
        }
    }
}
